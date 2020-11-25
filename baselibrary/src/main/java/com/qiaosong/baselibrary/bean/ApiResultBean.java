package com.qiaosong.baselibrary.bean;

import com.google.gson.annotations.SerializedName;

public class ApiResultBean<T>  {
    @SerializedName("status")
    private int errCode;
    @SerializedName("msg")
    private String errMsg;
    private T data;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errcode) {
        this.errCode = errcode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errmsg) {
        this.errMsg = errmsg;
    }

    @Override
    public String toString() {
        return "HttpResponseModel{" +
                "errCode=" + errCode +
                ", data=" + data +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}