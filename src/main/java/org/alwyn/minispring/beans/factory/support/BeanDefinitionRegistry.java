package org.alwyn.minispring.beans.factory.support;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;

/*
    This is the only interface in Spring's bean factory packages that encapsulates registration of bean definitions.
 */

public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    boolean containsBeanDefinition(String beanName);

}
