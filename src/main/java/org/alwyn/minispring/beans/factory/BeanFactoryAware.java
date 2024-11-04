package org.alwyn.minispring.beans.factory;

import org.alwyn.minispring.beans.BeansException;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
