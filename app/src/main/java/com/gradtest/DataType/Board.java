package com.gradtest.DataType;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by sm-pc on 2018-05-31.
 */

public class Board {

    public Board(){}

    public Board(String board_title, String board_content, int board_category){
        this.board_title = board_title;
        this.board_content = board_content;
        this.board_category = board_category;
        this.board_photos = board_photos;
        this.user_index=user_index;
    }



    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }


    public int getUser_index() {
        return user_index;
    }

    public void setUser_index(int user_index) {
        this.user_index = user_index;
    }

    public int getBoard_category() {
        return board_category;
    }

    public void setBoard_category(int board_category) {
        this.board_category = board_category;
    }

    String board_title;
    String board_content;
    ArrayList data;

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }

    int user_index;
    int board_category;
    File board_photos;

    public File getBoard_photos() {
        return board_photos;
    }

    public void setBoard_photos(File board_photos) {
        this.board_photos = board_photos;
    }
}
