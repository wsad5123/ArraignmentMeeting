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
    private String deviceUuid;
    private String provinceName;

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

    public String getDeviceUuid() {
        if (TextUtils.isEmpty(deviceUuid)) {
            deviceUuid = SharedPreferencesUtils.getDeviceUuid(UIManager.getInstance().getBaseContext());
        }
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
        SharedPreferencesUtils.setDeviceUuid(UIManager.getInstance().getBaseContext(), TextUtils.isEmpty(deviceUuid) ? "" : deviceUuid);
    }

    public String getProvinceName() {
        if (TextUtils.isEmpty(provinceName)) {
            provinceName = SharedPreferencesUtils.getProvinceName(UIManager.getInstance().getBaseContext());
        }
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
        SharedPreferencesUtils.setProvinceName(UIManager.getInstance().getBaseContext(), TextUtils.isEmpty(provinceName) ? "" : provinceName);
    }
}


