package org.alwyn.minispring.beans.factory;

import org.alwyn.minispring.beans.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
