package com.qiaosong.arraignmentmeeting;

import com.hst.fsp.FspEngine;

/**
 * APP全局数据缓存类
 */
public class AppCacheManager {
    private static volatile AppCacheManager instance = null;


    private AppCacheManager() {
    }

    public static AppCacheManager getInstance() {
        if (instance == null) {
            synchronized (AppCacheManager.class) {
                if (instance == null) {
                    instance = new AppCacheManager();
                }
            }
        }
        return instance;
    }

}
