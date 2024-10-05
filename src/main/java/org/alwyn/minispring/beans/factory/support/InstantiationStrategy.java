package org.alwyn.minispring.beans.factory.support;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
