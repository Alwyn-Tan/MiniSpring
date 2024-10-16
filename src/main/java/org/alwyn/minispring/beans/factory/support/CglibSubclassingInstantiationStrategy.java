package org.alwyn.minispring.beans.factory.support;

import net.sf.cglib.proxy.NoOp;
import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Constructor;

public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if ( null == ctor) {return enhancer.create();}
        else{
            return enhancer.create(ctor.getParameterTypes(), args);
        }
    }
}
