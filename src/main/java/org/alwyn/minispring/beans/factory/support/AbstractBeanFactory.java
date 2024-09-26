package org.alwyn.minispring.beans.factory.support;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.BeanFactory;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        } else {
            BeanDefinition beanDefinition = getBeanDefinition(name);
            return doCreateBean(name, beanDefinition);
        }
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object doCreateBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
