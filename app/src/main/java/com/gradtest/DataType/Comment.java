package com.gradtest.DataType;

import java.util.ArrayList;

/**
 * Created by sm-pc on 2018-09-14.
 */

public class Comment {

    public Comment(){}

    public Comment(String comment_content, int comment_index, String comment_time, String user_name){
        this.comment_content = comment_content;
        this.comment_index = comment_index;
        this.comment_time = comment_time;
        this.user_name = user_name;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    String comment_content;

    public int getComment_index() {
        return comment_index;
    }

    public void setComment_index(int comment_index) {
        this.comment_index = comment_index;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }

    int comment_index;
    String comment_time;
    String user_name;
    ArrayList data;

}
