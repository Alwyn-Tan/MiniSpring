package org.alwyn.minispring.beans.factory.support;

import org.alwyn.minispring.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry  implements SingletonBeanRegistry {
    private final Map<String, Object> singletonObjectsMap = new ConcurrentHashMap<>();
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjectsMap.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject){
        singletonObjectsMap.put(beanName, singletonObject);
    }
}
