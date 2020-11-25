package com.qiaosong.arraignmentmeeting.http;

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
        return BuildConfig.baseApiUrl;
    }
}

