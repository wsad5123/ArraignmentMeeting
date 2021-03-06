package com.qiaosong.arraignmentmeeting.bean;

import com.google.gson.annotations.SerializedName;

public class LoginTokenBean {
    @SerializedName("groupid")
    private String groupId;
    @SerializedName("useruuid")
    private String userUuid;
    private String token;
    private String serverAddr;
    private String appsecretkey;
    private String appid;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public String getAppsecretkey() {
        return appsecretkey;
    }

    public void setAppsecretkey(String appsecretkey) {
        this.appsecretkey = appsecretkey;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
