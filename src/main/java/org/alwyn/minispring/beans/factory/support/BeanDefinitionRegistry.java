package org.alwyn.minispring.beans.factory.support;

import org.alwyn.minispring.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
