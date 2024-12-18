package org.alwyn.minispring.beans.factory.config;

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void destroySingletons();
}
