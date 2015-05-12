package com.ohn;

import java.util.ArrayList;

/**
 * Created by c4q-george on 3/22/15.
 */
public class User {
    private String username = "";
    private static ArrayList<String> users = new ArrayList<String>();
    

    public User (String username){
        this.username = username;
        users.add(username);
    }

}
