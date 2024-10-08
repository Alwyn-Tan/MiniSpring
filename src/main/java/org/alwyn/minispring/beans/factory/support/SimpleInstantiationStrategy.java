package org.alwyn.minispring.beans.factory.support;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException{
        Class beanClass = beanDefinition.getBeanClass();
        try{
            if ( ctor != null){
                return beanClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else{
                return beanClass.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
