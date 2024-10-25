package org.alwyn.minispring.beans.factory.config;

import org.alwyn.minispring.beans.factory.HierarchicalBeanFactory;

/*
 * Configuration interface to be implemented by most bean factories. Provides
 * BeanPostProcessor handling
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry{
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
