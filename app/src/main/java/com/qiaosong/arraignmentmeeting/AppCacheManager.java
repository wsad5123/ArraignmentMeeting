package com.qiaosong.arraignmentmeeting;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.qiaosong.arraignmentmeeting.bean.HttpAddressBean;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.utils.SharedPreferencesUtils;
import com.qiaosong.baselibrary.manager.UIManager;

/**
 * APP全局数据缓存类
 */
public class AppCacheManager {
    private static volatile AppCacheManager instance = null;
    private LoginTokenBean loginTokenBean;
    private HttpAddressBean httpAddressBean;

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

    public HttpAddressBean getHttpAddressBean() {
        if (httpAddressBean == null) {
            String jsonData = SharedPreferencesUtils.getHttpAddressBeanJson(UIManager.getInstance().getBaseContext());
            if (!TextUtils.isEmpty(jsonData)) {
                httpAddressBean = new Gson().fromJson(jsonData, HttpAddressBean.class);
            }
        }
        return httpAddressBean;
    }

    public void setHttpAddressBean(HttpAddressBean httpAddressBean) {
        this.httpAddressBean = httpAddressBean;
        SharedPreferencesUtils.setHttpAddressBeanJson(UIManager.getInstance().getBaseContext(), httpAddressBean == null ? "" : new Gson().toJson(httpAddressBean));
    }
}
