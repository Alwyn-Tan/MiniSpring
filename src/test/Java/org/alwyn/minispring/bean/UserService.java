package org.alwyn.minispring.bean;

public class UserService {

    private String userID;

    private UserDAO userDAO;

    public String queryUserName(){return userDAO.queryUserName(userID);}

    public String getUserID(){return userID;}

    public void setUserID(String userID){this.userID = userID;}

    public UserDAO getUserDAO(){return userDAO;}

    public void setUserDAO(UserDAO userDAO){this.userDAO = userDAO;}
}
