package com.gradtest.ETC;

import android.util.Log;

import com.gradtest.BuildConfig;

/**
 * Created by sm-pc on 2018-05-28.
 */

public class MyLog {
    private static boolean enabled = BuildConfig.DEBUG;

    public static void d(String tag, String text){
        if(!enabled) return;

        Log.d(tag,text);
    }

    public static void d(String text){
        if(!enabled) return;

        Log.d("tag",text);
    }

    public static void d(String tag, Class<?> cls, String text){
        if(!enabled) return;

        Log.d(tag, cls.getName() + "." + text);
    }

    public static void e(String tag, String text){
        if(!enabled) return;

        Log.e(tag,text);
    }
}
