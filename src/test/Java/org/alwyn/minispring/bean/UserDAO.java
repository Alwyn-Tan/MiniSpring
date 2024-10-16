package org.alwyn.minispring.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("001", "User001");
        hashMap.put("002", "User002");
        hashMap.put("003", "User003");
    }

    public String queryUserName(String userID) {return hashMap.get(userID);}
}
