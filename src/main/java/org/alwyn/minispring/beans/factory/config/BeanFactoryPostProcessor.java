package org.alwyn.minispring.beans.factory.config;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    /*
        Before the instantiation of Beans, after the load of BeanDefinition, provides the opportunity to modify the BeanDefinition
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
