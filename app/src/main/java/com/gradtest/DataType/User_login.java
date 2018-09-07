package com.gradtest.DataType;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by sm-pc on 2018-05-31.
 */

public class User_login {
    public User_login(){}

    public User_login(String user_id, String user_pw){
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_index = user_index;

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }


    String user_id;
    String user_pw;

    public int getUser_index() {
        return user_index;
    }

    public void setUser_index(int user_index) {
        this.user_index = user_index;
    }

   int user_index;
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
