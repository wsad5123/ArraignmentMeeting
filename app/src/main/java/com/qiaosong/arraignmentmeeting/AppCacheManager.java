package com.qiaosong.arraignmentmeeting;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.hst.fsp.FspEngine;
import com.qiaosong.arraignmentmeeting.bean.BaseInformationBean;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.utils.SharedPreferencesUtils;
import com.qiaosong.baselibrary.manager.UIManager;

/**
 * APP全局数据缓存类
 */
public class AppCacheManager {
    private static volatile AppCacheManager instance = null;
    private LoginTokenBean loginTokenBean;
    private BaseInformationBean baseInformationBean;


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

    public BaseInformationBean getBaseInformationBean() {
        if (baseInformationBean == null) {
            String jsonData = SharedPreferencesUtils.getBaseInformationBeanJson(UIManager.getInstance().getBaseContext());
            if (!TextUtils.isEmpty(jsonData)) {
                baseInformationBean = new Gson().fromJson(jsonData, BaseInformationBean.class);
            }
        }
        return baseInformationBean;
    }

    public void setBaseInformationBean(BaseInformationBean baseInformationBean) {
        this.baseInformationBean = baseInformationBean;
        SharedPreferencesUtils.setBaseInformationBeanJson(UIManager.getInstance().getBaseContext(), new Gson().toJson(baseInformationBean));
    }
}
