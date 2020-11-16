package com.qiaosong.arraignmentmeeting;

import android.app.Application;
import android.content.Context;

import com.qiaosong.baselibrary.Library;


public class AppApplication extends Application {//兼容21之前的Android版本dex分包

    private static AppApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        Library.init(this);
    }

    public static AppApplication getInstance() {
        return mApplication;
    }

    public static Context getContext() {
        return mApplication.getBaseContext();
    }

}
