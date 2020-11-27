package com.qiaosong.arraignmentmeeting.bean;

import com.google.gson.annotations.SerializedName;

public class CityBean {
    @SerializedName("c_code")
    private String code;
    @SerializedName("c_name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
