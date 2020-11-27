package com.qiaosong.arraignmentmeeting.http.subscribe;

import android.content.Context;

import com.qiaosong.arraignmentmeeting.bean.BeginMeetBean;
import com.qiaosong.arraignmentmeeting.bean.CardIdRoomIdBean;
import com.qiaosong.arraignmentmeeting.bean.CityBean;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.bean.ProvinceBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorTypeBean;
import com.qiaosong.arraignmentmeeting.bean.api.ApiDeviceInfoBean;
import com.qiaosong.arraignmentmeeting.http.Api;
import com.qiaosong.baselibrary.bean.ApiResultBean;
import com.qiaosong.arraignmentmeeting.http.ApiObserver;
import com.qiaosong.arraignmentmeeting.http.ServiceGenerator;

import java.util.List;

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


    /**
     * 获取所有省份信息
     *
     * @param mObservable
     */
    public void requestAllProvince(MultipartBody params, ApiObserver<List<ProvinceBean>> mObservable) {
        Api.AppInterface apiService = ServiceGenerator.createService(Api.AppInterface.class);
        Observable<ApiResultBean<List<ProvinceBean>>> observable = apiService.requestAllProvince(params);
        subscribe(observable, mObservable);
    }

    /**
     * 获取所有省份信息
     *
     * @param mObservable
     */
    public void requestCityByProvinceCode(MultipartBody params, ApiObserver<List<CityBean>> mObservable) {
        Api.AppInterface apiService = ServiceGenerator.createService(Api.AppInterface.class);
        Observable<ApiResultBean<List<CityBean>>> observable = apiService.requestCityByProvinceCode(params);
        subscribe(observable, mObservable);
    }

    /**
     * 获取所有监管机构类型
     *
     * @param mObservable
     */
    public void requestAllRegulatorType(MultipartBody params, ApiObserver<List<RegulatorTypeBean>> mObservable) {
        Api.AppInterface apiService = ServiceGenerator.createService(Api.AppInterface.class);
        Observable<ApiResultBean<List<RegulatorTypeBean>>> observable = apiService.requestAllRegulatorType(params);
        subscribe(observable, mObservable);
    }

    /**
     * 获取所有监管机构
     *
     * @param mObservable
     */
    public void requestAllRegulator(MultipartBody params, ApiObserver<List<RegulatorBean>> mObservable) {
        Api.AppInterface apiService = ServiceGenerator.createService(Api.AppInterface.class);
        Observable<ApiResultBean<List<RegulatorBean>>> observable = apiService.requestAllRegulator(params);
        subscribe(observable, mObservable);
    }

    /**
     * 保存设备信息
     *
     * @param mObservable
     */
    public void requestDeviceInfoSave(MultipartBody params, ApiObserver<Object> mObservable) {
        Api.AppInterface apiService = ServiceGenerator.createService(Api.AppInterface.class);
        Observable<ApiResultBean<Object>> observable = apiService.requestDeviceInfoSave(params);
        subscribe(observable, mObservable);
    }

    /**
     * 获取设备信息
     *
     * @param mObservable
     */
    public void requestDeviceInfo(MultipartBody params, ApiObserver<ApiDeviceInfoBean> mObservable) {
        Api.AppInterface apiService = ServiceGenerator.createService(Api.AppInterface.class);
        Observable<ApiResultBean<ApiDeviceInfoBean>> observable = apiService.requestDeviceInfo(params);
        subscribe(observable, mObservable);
    }

}
