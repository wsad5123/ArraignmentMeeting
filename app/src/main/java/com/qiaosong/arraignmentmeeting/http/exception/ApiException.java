package com.qiaosong.arraignmentmeeting.http.exception;

import com.google.gson.JsonParseException;
import com.qiaosong.arraignmentmeeting.http.ApiCode;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;

public class ApiException extends IOException {

    private final String errorCode;
    private String errorMessage;
    private boolean isNeedShowError = true;

    public ApiException(Throwable throwable, String errorCode) {
        super(throwable);
        this.errorCode = errorCode;
    }


    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, ApiCode.Request.HTTP_ERROR);
            switch (httpException.code()) {
                case ApiCode.Http.UNAUTHORIZED:
                    ex.errorMessage = "用户未登录！";
                    break;
                case ApiCode.Http.FORBIDDEN:
                    ex.errorMessage = "不需要更新";
                    break;
                case ApiCode.Http.NOT_FOUND:
                    ex.errorMessage = "找不到相关接口";
                    break;
                case ApiCode.Http.REQUEST_TIMEOUT:
                    ex.errorMessage = "请求超时";
                    break;
                case ApiCode.Http.GATEWAY_TIMEOUT:
                    ex.errorMessage = "请求超时";
                    break;
                case ApiCode.Http.INTERNAL_SERVER_ERROR:
                    ex.errorMessage = "服务器异常";
                    break;
                case ApiCode.Http.BAD_GATEWAY:
                    ex.errorMessage = "无效网关";
                    break;
                case ApiCode.Http.SERVICE_UNAVAILABLE:
                    ex.errorMessage = "服务器异常";
                    break;
                default:
                    ex.errorMessage = "未知异常";
                    break;
            }
            return ex;
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            ex = new ApiException(e, ApiCode.Request.PARSE_ERROR);
            ex.errorMessage = "数据解析异常";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, ApiCode.Request.NETWORK_ERROR);
            ex.errorMessage = "网络错误";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ApiException(e, ApiCode.Request.SSL_ERROR);
            ex.errorMessage = "证书出错";
            return ex;
        } else if (e instanceof SocketTimeoutException) {
            ex = new ApiException(e, ApiCode.Request.TIMEOUT_ERROR);
            ex.errorMessage = "请求超时";
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex = new ApiException(e, ApiCode.Request.UNKNOW_HOST_ERROR);
            ex.errorMessage = "服务器连接异常";
            return ex;
        } else if (e instanceof HttpResponseException) {//请求错误返回异常
            ex = disposeHttpResponseException((HttpResponseException) e);
            return ex;
        } else {
            ex = new ApiException(e, ApiCode.Request.UNKNOWN);
            ex.errorMessage = "未知异常";
            return ex;
        }
    }

    /**
     * 全局处理http错误code异常，如果是某个具体页面或者功能需要做出逻辑操作可以在请求回调中重写OnError处理
     */
    private static ApiException disposeHttpResponseException(HttpResponseException e) {
        ApiException ex = new ApiException(e, e.getErrorCode() + "");
        ex.errorMessage = e.getErrorMessage();
        if (e.getErrorCode() == ApiCode.Response.CARD_NEED_BINDING) {//未绑定 需要绑定手机号
            ex.isNeedShowError = false;
        }
//
//        else if (e.getErrorCode() == ApiCode.Response.HTTP_NO_RESUME) {//简历不存在需要填写简历
//            ex.isNeedShowError = false;
//        } else if (e.getErrorCode() == ApiCode.Response.HTTP_TOKEN_FAILED || e.getErrorCode() == ApiCode.Response.HTTP_USER_UNLOGIN) {//token失效需要重新登录
//            ex.isNeedShowError = false;
//        } else if (e.getErrorCode() == ApiCode.Response.HTTP_DOUBLE_RESUME) {//存在多个简历需要删选
//            ex.isNeedShowError = false;
//        } else if (e.getErrorCode() == ApiCode.Response.HTTP_USER_CANT_LOGIN) {//用户已经注销或者拉黑不允许登录
//            ex.isNeedShowError = false;
//        }
        return ex;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }

    public boolean isNeedShowError() {
        return isNeedShowError;
    }

    public void setNeedShowError(boolean needShowError) {
        isNeedShowError = needShowError;
    }
}
