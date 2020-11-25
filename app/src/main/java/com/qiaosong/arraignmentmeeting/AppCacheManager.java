package com.qiaosong.arraignmentmeeting;

import com.hst.fsp.FspEngine;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;

/**
 * APP全局数据缓存类
 */
public class AppCacheManager {
    private static volatile AppCacheManager instance = null;
    private LoginTokenBean loginTokenBean;


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

    public LoginTokenBean getLoginTokenBean() {
        return loginTokenBean;
    }

    public void setLoginTokenBean(LoginTokenBean loginTokenBean) {
        this.loginTokenBean = loginTokenBean;
    }

}
