package com.hairui;

import java.util.ResourceBundle;

public class GitHubUser {
        String username;
        String password;
        ResourceBundle resource = ResourceBundle.getBundle("GitHubUser");
        public String getUsername(){
            return  username = resource.getString("username");
        }
        public String getPassword(){
            return  password = resource.getString("password");
        }


}
