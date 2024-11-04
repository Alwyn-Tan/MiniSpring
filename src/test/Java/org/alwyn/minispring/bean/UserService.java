package org.alwyn.minispring.bean;

import org.alwyn.minispring.beans.factory.BeanClassLoaderAware;
import org.alwyn.minispring.beans.factory.BeanFactory;
import org.alwyn.minispring.beans.factory.BeanFactoryAware;
import org.alwyn.minispring.beans.factory.BeanNameAware;
import org.alwyn.minispring.context.ApplicationContext;
import org.alwyn.minispring.context.ApplicationContextAware;

public class UserService implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private String userID;

    private String userName;

    private String location;

    private UserDAO userDAO;

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("setBeanClassLoader" + classLoader);
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("setBeanName" + beanName);
    }

    public String queryUserDAO() {
        return userDAO.queryUserName(userID);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


}
