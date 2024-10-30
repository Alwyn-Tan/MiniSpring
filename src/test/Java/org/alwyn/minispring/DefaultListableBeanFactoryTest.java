package org.alwyn.minispring;

import org.alwyn.minispring.bean.UserDAO;
import org.alwyn.minispring.bean.UserService;
import org.alwyn.minispring.beans.PropertyValue;
import org.alwyn.minispring.beans.PropertyValues;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;
import org.alwyn.minispring.beans.factory.config.BeanReference;
import org.alwyn.minispring.beans.factory.support.DefaultListableBeanFactory;
import org.alwyn.minispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.alwyn.minispring.processor.TestBeanFactoryPostProcessor;
import org.alwyn.minispring.processor.TestBeanPostProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultListableBeanFactoryTest {

    DefaultListableBeanFactory beanFactory;

    XmlBeanDefinitionReader beanDefinitionReader;

    TestBeanFactoryPostProcessor testBeanFactoryPostProcessor;

    TestBeanPostProcessor testBeanPostProcessor;

    @Before
    public void setUp() {
        beanFactory = new DefaultListableBeanFactory();

        beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        testBeanFactoryPostProcessor = new TestBeanFactoryPostProcessor();

        testBeanPostProcessor = new TestBeanPostProcessor();

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
        System.out.println(userService.getUserName());
    }

    /**
     * BeanFactoryPostProcessor can change the properties of Bean before Bean Initialization
     */
    @Test
    public void testBeanFactoryPostProcessor_beforeInitialization() {
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        testBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);

        Assert.assertEquals("Irene", userService.getUserName());

    }

    /**
     * After the Bean is initialized, BeanFactoryPostProcessor can not change the properties of Bean
     */
    @Test
    public void testBeanFactoryPostProcessor_afterInitialization() {
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);

        testBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Assert.assertEquals("Alwyn", userService.getUserName());
    }

}
