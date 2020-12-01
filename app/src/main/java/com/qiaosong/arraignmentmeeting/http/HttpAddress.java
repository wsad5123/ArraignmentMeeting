package com.qiaosong.arraignmentmeeting.http;

import android.text.TextUtils;

import com.qiaosong.arraignmentmeeting.AppCacheManager;
import com.qiaosong.arraignmentmeeting.BuildConfig;

/**
 * Created by zhangxinyu on 2019/3/7.
 */
public class HttpAddress {
    /**
     * 根据环境获取不同的服务端地址，服务端地址配置在server_url.properties中
     *
     * @return
     */
    public static String getServerAddress() {
        if (AppCacheManager.getInstance().getHttpAddressBean() != null && !TextUtils.isEmpty(AppCacheManager.getInstance().getHttpAddressBean().getIp()) && !TextUtils.isEmpty(AppCacheManager.getInstance().getHttpAddressBean().getPort())) {
            return "http://" + AppCacheManager.getInstance().getHttpAddressBean().getIp() + ":" + AppCacheManager.getInstance().getHttpAddressBean().getPort() + "/";
        }//Todo
        return BuildConfig.baseApiUrl;
    }
}

