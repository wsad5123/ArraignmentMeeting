package com.qiaosong.baselibrary.manager;

import android.content.Context;

public class UIManager {
    private static UIManager INSTANCE;
    private Context mBaseContext;

    private UIManager() {
    }

    public synchronized static UIManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UIManager();
        }
        return INSTANCE;
    }

    public Context getBaseContext() {
        return mBaseContext;
    }

    public void setBaseContext(Context context) {
        mBaseContext = context;
    }

}
