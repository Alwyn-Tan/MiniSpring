package org.alwyn.minispring.beans.factory.config;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {
    Object applyBeanPostProcessorsBeforeInitialization( String beanName, Object existingBean) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(String beanName, Object existingBean) throws BeansException;
}
