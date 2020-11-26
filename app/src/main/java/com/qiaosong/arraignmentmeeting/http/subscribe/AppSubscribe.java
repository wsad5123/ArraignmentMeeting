package com.qiaosong.arraignmentmeeting.http.subscribe;

import android.content.Context;

import com.qiaosong.arraignmentmeeting.bean.BeginMeetBean;
import com.qiaosong.arraignmentmeeting.bean.CardIdRoomIdBean;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.http.Api;
import com.qiaosong.baselibrary.bean.ApiNormalBean;
import com.qiaosong.baselibrary.bean.ApiResultBean;
import com.qiaosong.arraignmentmeeting.http.ApiObserver;
import com.qiaosong.arraignmentmeeting.http.ServiceGenerator;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

public class AppSubscribe extends BaseSubscribe {
    public AppSubscribe(Context context) {
        super(context);
    }

    /**
     * 获取token
     *
     * @param mObservable
     */
    public void requestValidateToken(MultipartBody params, ApiObserver<LoginTokenBean> mObservable) {
        Api.AppInterface apiService = ServiceGenerator.createService(Api.AppInterface.class);
        Observable<ApiResultBean<LoginTokenBean>> observable = apiService.requestValidateToken(params);
        subscribe(observable, mObservable);
    }

    /**
     * 根据身份证号获取会见码
     *
     * @param mObservable
     */
    public void requestOrderCodeByCrimanalsCardId(MultipartBody params, ApiObserver<CardIdRoomIdBean> mObservable) {
        Api.AppInterface apiService = ServiceGenerator.createService(Api.AppInterface.class);
        Observable<ApiResultBean<CardIdRoomIdBean>> observable = apiService.requestOrderCodeByCrimanalsCardId(params);
        subscribe(observable, mObservable);
    }


    /**
     * 获取是否开始会见
     *
     * @param mObservable
     */
    public void requestIsbeginMeetting(MultipartBody params, ApiObserver<BeginMeetBean> mObservable) {
        Api.AppInterface apiService = ServiceGenerator.createService(Api.AppInterface.class);
        Observable<ApiResultBean<BeginMeetBean>> observable = apiService.requestIsbeginMeetting(params);
        subscribe(observable, mObservable);
    }



}
