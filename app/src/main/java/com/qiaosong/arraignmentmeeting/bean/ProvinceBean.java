package com.qiaosong.arraignmentmeeting.bean;

import com.google.gson.annotations.SerializedName;

public class ProvinceBean {
    @SerializedName("p_code")
    private String code;
    @SerializedName("p_name")
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
