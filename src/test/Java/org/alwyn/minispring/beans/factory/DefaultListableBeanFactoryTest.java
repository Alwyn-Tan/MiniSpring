package org.alwyn.minispring.beans.factory;

import org.alwyn.minispring.beans.PropertyValue;
import org.alwyn.minispring.beans.PropertyValues;
import org.alwyn.minispring.beans.factory.bean.UserDAO;
import org.alwyn.minispring.beans.factory.bean.UserService;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;
import org.alwyn.minispring.beans.factory.config.BeanReference;
import org.alwyn.minispring.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class DefaultListableBeanFactoryTest {

    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("userDAO", new BeanDefinition(UserDAO.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userID", "001"));
        propertyValues.addPropertyValue(new PropertyValue("userDAO", new BeanReference("userDAO")));

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService.queryUserName());
    }
}
