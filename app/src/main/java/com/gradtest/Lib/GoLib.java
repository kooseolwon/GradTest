package com.gradtest.Lib;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

import com.gradtest.Activity.PhotoActivity;

/**
 * Created by sm-pc on 2018-05-28.
 */

public class GoLib {
    public final String TAG = GoLib.class.getSimpleName();
    private volatile static GoLib instance;

    public static GoLib getInstance(){
        if(instance==null){
            synchronized (GoLib.class){
                if(instance == null){
                    instance = new GoLib();
                }
            }
        }
        return instance;
    }

    public void goFragment(FragmentManager fragmentManager, int containerViewId, android.support.v4.app.Fragment fragment){
        fragmentManager.beginTransaction().replace(containerViewId, fragment).commit();
    }

    public void goFragmentBack(FragmentManager fragmentManager, int containerViewId, Fragment fragment){
        fragmentManager.beginTransaction().replace(containerViewId, fragment).addToBackStack(null).commit();
    }

    public void goBackFragment(FragmentManager fragmentManager){
        fragmentManager.popBackStack();
    }

    public void goPhotoActivity(Context context, int infoSeq){
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra(PhotoActivity.INFO_SEQ, infoSeq);
        context.startActivity(intent);
    }
}
