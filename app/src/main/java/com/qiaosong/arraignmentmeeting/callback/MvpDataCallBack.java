package com.qiaosong.arraignmentmeeting.callback;


import com.qiaosong.arraignmentmeeting.http.exception.ApiException;

public abstract class MvpDataCallBack<T> {
    public abstract void onData(T data);

    public void onError(ApiException exception) {
    }

}
