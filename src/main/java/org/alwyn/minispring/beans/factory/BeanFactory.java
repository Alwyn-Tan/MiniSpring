package org.alwyn.minispring.beans.factory;

import org.alwyn.minispring.beans.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object...args) throws BeansException;

    <T> T getBean(String name, Class<T> type) throws BeansException;
}
