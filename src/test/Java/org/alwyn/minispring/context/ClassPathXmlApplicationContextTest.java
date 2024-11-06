package org.alwyn.minispring.context;

import org.alwyn.minispring.bean.UserService;
import org.alwyn.minispring.context.support.ClassPathXmlApplicationContext;
import org.junit.Before;
import org.junit.Test;

public class ClassPathXmlApplicationContextTest {
    ClassPathXmlApplicationContext applicationContext;

    String configLocation = "classpath:spring.xml";

    @Before
    public void setApplicationContext() {
        applicationContext = new ClassPathXmlApplicationContext(configLocation);
    }

    @Test
    public void test_ClassPathXmlApplicationContext() {
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.getUserName());
    }

    @Test
    public void test_RegisterShutdownHook() {
        applicationContext.registerShutdownHook();
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.getUserName());
    }

    @Test
    public void test_GetApplicationContext() {
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.getApplicationContext());
    }

    @Test
    public void test_GetBeanFactory() {
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.getBeanFactory());
    }


}
