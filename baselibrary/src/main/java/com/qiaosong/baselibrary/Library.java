package com.qiaosong.baselibrary;

import android.app.Application;
import android.content.Context;

import com.qiaosong.baselibrary.manager.UIManager;

public class Library {
    public static void init(Application application) {
        UIManager.getInstance().setBaseContext(application.getBaseContext());
    }
}
