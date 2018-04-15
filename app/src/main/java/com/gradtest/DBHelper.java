package com.gradtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;

/**
 * Created by sm-pc on 2018-04-15.
 */

public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE BOARD (_id INTEGER PRIMARY KEY AUTOINCREMENT, create_at TEXT, id TEXT, title TEXT, item TEXT, p_photo BLOB)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }

    public void insert(String create_at, String id, String title, String item, Blob p_photo){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO BOARD VALUES(null, '" + create_at + "', " + id + ", '" + title + "', " + item + ", " + p_photo + ");");
        db.close();
    }

    /*public String getResult(){
        SQLiteDatabase db = getReadableDatabase();
        String result =
    */


}
