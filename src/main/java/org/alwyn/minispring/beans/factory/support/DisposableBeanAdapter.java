package org.alwyn.minispring.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.DisposableBean;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
            if(bean instanceof DisposableBean){
                ((DisposableBean)bean).destroy();
            }

            if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))){
                Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
                if(null == destroyMethod){
                    throw new BeansException("Could not find a destroy method named " + destroyMethodName + ", bean: " + beanName);
                }
                destroyMethod.invoke(bean);
            }
    }
}
