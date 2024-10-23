package org.alwyn.minispring.bean;

public class UserService {

    private String userID;

    private String name;

    private String location;

    private UserDAO userDAO;

    public String queryUserName(){return userDAO.queryUserName(userID);}

    public void setUserID(String userID){this.userID = userID;}

    public void setUserDAO(UserDAO userDAO){this.userDAO = userDAO;}

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserID(){return userID;}

    public UserDAO getUserDAO(){return userDAO;}

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
