package org.alwyn.minispring.beans.factory.config;

import org.alwyn.minispring.beans.BeansException;

/*
    Allows for custom modification before the final instantiation of a bean
 */

public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(String beanName, Object bean) throws BeansException;

    Object postProcessAfterInitialization(String beanName, Object bean) throws BeansException;

}
