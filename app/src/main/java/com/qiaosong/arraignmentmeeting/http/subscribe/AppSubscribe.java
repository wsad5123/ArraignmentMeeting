package com.qiaosong.arraignmentmeeting.http.subscribe;

import android.content.Context;

import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.http.Api;
import com.qiaosong.baselibrary.bean.ApiNormalBean;
import com.qiaosong.baselibrary.bean.ApiResultBean;
import com.qiaosong.arraignmentmeeting.http.ApiObserver;
import com.qiaosong.arraignmentmeeting.http.ServiceGenerator;

import java.util.Map;

import io.reactivex.Observable;

public class AppSubscribe extends BaseSubscribe {
    public AppSubscribe(Context context) {
        super(context);
    }

    /**
     * 获取token
     *
     * @param mObservable
     */
    public void requestValidateToken(Map<String, Object> params, ApiObserver<LoginTokenBean> mObservable) {
        Api.AppInterface apiService = ServiceGenerator.createService(Api.AppInterface.class);
        Observable<ApiResultBean<LoginTokenBean>> observable = apiService.requestValidateToken(params);
        subscribe(observable, mObservable);
    }

    /**
     * 根据身份证号获取会见码
     *
     * @param mObservable
     */
    public void requestOrderCodeByCrimanalsCardId(Map<String, Object> params, ApiObserver<Boolean> mObservable) {
        Api.AppInterface apiService = ServiceGenerator.createService(Api.AppInterface.class);
        Observable<ApiResultBean<Boolean>> observable = apiService.requestOrderCodeByCrimanalsCardId(params);
        subscribe(observable, mObservable);
    }


}
