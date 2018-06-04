package com.gradtest.Lib;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by sm-pc on 2018-05-28.
 */

public class FileLib {
    public static final String TAG = FileLib.class.getSimpleName();
    private volatile static FileLib instance;

    public static FileLib getInstance(){
        if(instance == null){
            synchronized(FileLib.class){
                if(instance==null){
                    instance = new FileLib();
                }
            }
        }
        return instance;
    }

    private File getFileDir(Context context){
        String state = Environment.getExternalStorageState();
        File filesDir;

        if(Environment.MEDIA_MOUNTED.equals(state)){
            filesDir = context.getExternalFilesDir(null);
        }else{
            filesDir = context.getFilesDir();
        }
        return filesDir;
    }

    public File getImageFile(Context context, String name){
        return new File(FileLib.getInstance().getFileDir(context),name+".png");
    }
}
