package org.alwyn.minispring.beans.factory;

import org.alwyn.minispring.beans.factory.config.BeanDefinition;
import org.alwyn.minispring.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeanFactoryTest {
    @Test
    public void testBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(TestBean.class);
        beanFactory.registerBeanDefinition("testBean", beanDefinition);

        TestBean testBean = (TestBean) beanFactory.getBean("testBean");
        testBean.service();

        TestBean testBeanSingleton = (TestBean) beanFactory.getSingleton("testBean");
        testBeanSingleton.service();

        TestBean testBean2 = (TestBean) beanFactory.getBean("testBean");
        testBean2.service();
    }
}