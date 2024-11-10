package org.alwyn.minispring.beans.factory.support;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.BeanFactory;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;
import org.alwyn.minispring.beans.factory.config.BeanPostProcessor;
import org.alwyn.minispring.beans.factory.config.ConfigurableBeanFactory;
import org.alwyn.minispring.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    private final ClassLoader beanCLassLoader = ClassUtils.getDefaultClassLoader();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws  BeansException{
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> type) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args){
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        } else {
            BeanDefinition beanDefinition = getBeanDefinition(name);
            return (T) doCreateBean(name, beanDefinition, args);
        }
    }
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {return beanPostProcessors;}

    public ClassLoader getBeanClassLoader(){
        return this.beanCLassLoader;
    }
}
