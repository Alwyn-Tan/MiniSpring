package org.alwyn.minispring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.PropertyValue;
import org.alwyn.minispring.beans.PropertyValues;
import org.alwyn.minispring.beans.factory.*;
import org.alwyn.minispring.beans.factory.config.AutowireCapableBeanFactory;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;
import org.alwyn.minispring.beans.factory.config.BeanPostProcessor;
import org.alwyn.minispring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/*
    Manage the life cycle of bean, such as instantiation, post processing, property setting, and initialization.
 */

public abstract class AbstracAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object beanObject;
        try {
            beanObject = createBeanInstance(beanName, beanDefinition, args);
            applyPropertyValues(beanName, beanObject, beanDefinition);
            beanObject = initializeBean(beanName, beanObject, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        registerDisposableBeanIfNecessary(beanName, beanObject, beanDefinition);

        addSingleton(beanName, beanObject);
        return beanObject;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor constructor = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (args != null && ctor.getParameterTypes().length == args.length) {
                constructor = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructor, args);
    }

    protected void applyPropertyValues(String beanName, Object beanObject, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                BeanUtil.setFieldValue(beanObject, name, value);
            }
        } catch (BeansException e) {
            throw new BeansException("Could not apply property values:" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object beanObject, BeanDefinition beanDefinition) {
        if (beanObject instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(beanObject, beanName, beanDefinition));
        }
    }

    private Object initializeBean(String beanName, Object beanObject, BeanDefinition beanDefinition) throws BeansException {

        if(beanObject instanceof Aware){
            if(beanObject instanceof BeanFactoryAware){
                ((BeanFactoryAware) beanObject).setBeanFactory(this);
            }
            if(beanObject instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) beanObject).setBeanClassLoader(getBeanClassLoader());
            }
            if(beanObject instanceof BeanNameAware){
                ((BeanNameAware) beanObject).setBeanName(beanName);
            }
        }

        Object wrappedBeanObject = applyBeanPostProcessorsBeforeInitialization(beanName, beanObject);
        try {
            invokeInitMethods(beanName, wrappedBeanObject, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Initialization of bean failed", e);
        }
        wrappedBeanObject = applyBeanPostProcessorsAfterInitialization(beanName, wrappedBeanObject);
        return wrappedBeanObject;
    }

    private void invokeInitMethods(String beanName, Object wrappedBeanObject, BeanDefinition beanDefinition) throws Exception {
        if (wrappedBeanObject instanceof InitializingBean) {
            ((InitializingBean) wrappedBeanObject).afterPropertiesSet();
        }

        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName) && !(wrappedBeanObject instanceof InitializingBean)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            initMethod.invoke(wrappedBeanObject);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(String beanName, Object beanObject) throws BeansException {
        Object processedBeanObject = beanObject;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object currentBeanObject = processor.postProcessBeforeInitialization(beanName, beanObject);
            if (null == currentBeanObject) {
                return beanObject;
            } else {
                processedBeanObject = currentBeanObject;
            }
        }
        return processedBeanObject;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(String beanName, Object beanObject) throws BeansException {
        Object processedBeanObject = beanObject;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object currentBeanObject = processor.postProcessAfterInitialization(beanName, beanObject);
            if (null == currentBeanObject) {
                return beanObject;
            } else {
                processedBeanObject = currentBeanObject;
            }
        }
        return processedBeanObject;
    }
}
