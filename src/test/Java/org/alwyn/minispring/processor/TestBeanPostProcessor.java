package org.alwyn.minispring.processor;

import org.alwyn.minispring.bean.UserService;
import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.config.BeanPostProcessor;

public class TestBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) throws BeansException {
        if("userService".equals(beanName)){
            UserService userService = (UserService) bean;
            userService.setLocation("America");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) throws BeansException {
        return bean;
    }
}
