package org.alwyn.minispring.beans.factory.support;

import org.alwyn.minispring.beans.PropertyValue;
import org.alwyn.minispring.beans.PropertyValues;
import org.alwyn.minispring.bean.UserDAO;
import org.alwyn.minispring.bean.UserService;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;
import org.alwyn.minispring.beans.factory.config.BeanReference;
import org.junit.Test;

public class DefaultListableBeanFactoryTest {

    @Test
    public void test_registerBeanDefinition() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("userDAO", new BeanDefinition(UserDAO.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userID", "001"));
        propertyValues.addPropertyValue(new PropertyValue("userDAO", new BeanReference("userDAO")));

        BeanDefinition UserServiceBeanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", UserServiceBeanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService.queryUserName());
    }


}
