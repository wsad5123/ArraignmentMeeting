package com.qiaosong.arraignmentmeeting.http.exception;

import com.qiaosong.baselibrary.bean.ApiResultBean;

/**
 * 请求返回错误异常
 */
public class HttpResponseException extends Exception {
    private int errorCode;
    private String errorMessage;

    public HttpResponseException(ApiResultBean apiResultBean) {
        this.errorCode = apiResultBean.getErrCode();
        this.errorMessage = apiResultBean.getErrMsg();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
