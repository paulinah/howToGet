package com.example.user.myapplication;

import android.app.Activity;
import android.app.Application;

/**
 * Created by Paula on 2017-05-08.
 */

public class MyApp extends Application {
    private Activity mCurrentActivity = null;

    public void onCreate() {
        super.onCreate();
    }

    public Activity getCurrentActivity(){
        return mCurrentActivity;
    }

    public void setCurrentActivity(Activity mCurrentActivity){
        this.mCurrentActivity = mCurrentActivity;
    }
}
