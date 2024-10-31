package org.alwyn.minispring.bean;

import org.alwyn.minispring.beans.factory.DisposableBean;
import org.alwyn.minispring.beans.factory.InitializingBean;

public class UserService implements InitializingBean, DisposableBean {

    private String userID;

    private String userName;

    private String location;

    private UserDAO userDAO;

    @Override
    public void destroy() throws Exception {
        System.out.println("UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserService.afterPropertiesSet");
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
}
