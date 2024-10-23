package org.alwyn.minispring.beans.factory.support;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.DisposableBean;
import org.alwyn.minispring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry  implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjectsMap = new ConcurrentHashMap<>();

    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjectsMap.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject){
        singletonObjectsMap.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean disposableBean){disposableBeanMap.put(beanName
    , disposableBean);}

    public void destroySingletons(){
        Set<String> keySet  = this.disposableBeanMap.keySet();
        Object[] disposableBeanNames = this.disposableBeanMap.keySet().toArray();

        for(int i = disposableBeanNames.length-1; i >=0; i--){
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);

            try{
                disposableBean.destroy();
            }catch (Exception e){
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
