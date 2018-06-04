package com.gradtest;

import android.graphics.Bitmap;
import android.os.Handler;

import java.io.File;
import java.io.FileOutputStream;


/**
 * Created by sm-pc on 2018-05-28.
 */

public class BitmapLib {
    public static final String TAG = BitmapLib.class.getSimpleName();
    private volatile static BitmapLib instance;

    public static BitmapLib getInstance(){
        if(instance==null){
            synchronized (BitmapLib.class){
                if(instance==null){
                    instance=new BitmapLib();
                }
            }
        }
        return instance;
    }

    public void saveBitmapToFileThread(final Handler handler, final File file, final Bitmap bitmap){
        new Thread(){
            @Override
            public void run() {
                saveBitmapToFile(file,bitmap);
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    private boolean saveBitmapToFile(File file, Bitmap bitmap){
        if(bitmap==null)return false;

        boolean save = false;

        FileOutputStream out = null;
        try{
            out = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.PNG,90,out);
            save=true;
        }catch(Exception e){
            save = false;
        }finally {
            try{
                out.close();
            }catch(Exception e){}
        }
        return save;
    }
}
