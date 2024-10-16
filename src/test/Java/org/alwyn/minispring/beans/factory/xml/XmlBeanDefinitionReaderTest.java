package org.alwyn.minispring.beans.factory.xml;

import org.alwyn.minispring.bean.UserService;
import org.alwyn.minispring.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Before;
import org.junit.Test;

public class XmlBeanDefinitionReaderTest {

    DefaultListableBeanFactory beanFactory;

    XmlBeanDefinitionReader beanDefinitionReader;

    @Before
    public void setUp(){
        beanFactory = new DefaultListableBeanFactory();
        beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
    }

    @Test
    public void test_loadBeanDefinition() throws Exception{
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        UserService userService = beanFactory.getBean("userService", UserService.class);
        System.out.println(userService.queryUserName());
    }
}