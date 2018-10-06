package com.gradtest.DataType;

import java.io.File;

/**
 * Created by sm-pc on 2018-05-31.
 */

public class User {

    public User(){}

    public User(String user_id, String user_pw, String user_name, int user_area, String deviceToken){
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.user_area=user_area;
        this.deviceToken=deviceToken;

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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    String user_id;
    String user_pw;
    String user_name;
    String deviceToken;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    int user_area;

    public int getUser_area() {
        return user_area;
    }

    public void setUser_area(int user_area) {
        this.user_area = user_area;
    }
}
