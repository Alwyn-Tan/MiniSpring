package org.alwyn.minispring;

import org.alwyn.minispring.beans.PropertyValue;
import org.alwyn.minispring.beans.PropertyValues;

import org.alwyn.minispring.bean.UserDAO;
import org.alwyn.minispring.bean.UserService;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;
import org.alwyn.minispring.beans.factory.config.BeanReference;
import org.alwyn.minispring.beans.factory.support.DefaultListableBeanFactory;
import org.alwyn.minispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.alwyn.minispring.processor.TestBeanFactoryPostProcessor;
import org.junit.Before;
import org.junit.Test;

public class DefaultListableBeanFactoryTest {

    DefaultListableBeanFactory beanFactory;

    XmlBeanDefinitionReader beanDefinitionReader;

    TestBeanFactoryPostProcessor testBeanFactoryPostProcessor;
    @Before
    public void setUp(){
        beanFactory = new DefaultListableBeanFactory();

        beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        testBeanFactoryPostProcessor = new TestBeanFactoryPostProcessor();
    }

    @Test
    public void test_registerBeanDefinition() {

        beanFactory.registerBeanDefinition("userDAO", new BeanDefinition(UserDAO.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userID", "001"));
        propertyValues.addPropertyValue(new PropertyValue("userDAO", new BeanReference("userDAO")));

        BeanDefinition UserServiceBeanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", UserServiceBeanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService.queryUserName());
    }

    @Test
    public void testBeanFactoryPostProcessor() {
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        testBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
    }

}
