package com.twu.biblioteca;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by esiow on 16/01/2015.
 */
public class UserDatabase {
    private static Map<String, User> userList;

    public UserDatabase(Collection<User> userList) {
        this.userList = new HashMap<String, User>();
        for(User u : userList) {
            this.userList.put(u.libraryNum, u);
        }
    }

    public User findByLibraryNum(String libraryNum) {
        return this.userList.get(libraryNum);
    }

}
