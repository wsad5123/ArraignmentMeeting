package com.qiaosong.arraignmentmeeting.http;


import com.qiaosong.arraignmentmeeting.bean.BeginMeetBean;
import com.qiaosong.arraignmentmeeting.bean.CardIdRoomIdBean;
import com.qiaosong.arraignmentmeeting.bean.CityBean;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.bean.ProvinceBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorTypeBean;
import com.qiaosong.baselibrary.bean.ApiResultBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class Api {

    public interface AppInterface {
        //获取token
        @POST("seatlock/validateToken")
        Observable<ApiResultBean<LoginTokenBean>> requestValidateToken(@Body MultipartBody body);

        //获取更具身份证号获取会见码
        @POST("seatlock/getOrderCodeByCrimanalsCardId")
        Observable<ApiResultBean<CardIdRoomIdBean>> requestOrderCodeByCrimanalsCardId(@Body MultipartBody body);

        //获取是否开始会见
        @POST("seatlock/isbeginMeetting")
        Observable<ApiResultBean<BeginMeetBean>> requestIsbeginMeetting(@Body MultipartBody body);

        //获取所有省份信息
        @POST("area/getallprovince")
        Observable<ApiResultBean<List<ProvinceBean>>> requestAllProvince(@Body MultipartBody body);

        //获取城市信息
        @POST("area/getcinfo")
        Observable<ApiResultBean<List<CityBean>>> requestCityByProvinceCode(@Body MultipartBody body);

        //获取所有监管机构类型
        @POST("prisonunittype/getprisonunittypeinfo")
        Observable<ApiResultBean<List<RegulatorTypeBean>>> requestAllRegulatorType(@Body MultipartBody body);

        //获取所有监管机构类型
        @POST("prisonunitinfo/getPrisonListByType")
        Observable<ApiResultBean<List<RegulatorBean>>> requestAllRegulator(@Body MultipartBody body);

        //设备信息保存
        @POST("deviceinfo/createOrUpdate")
        Observable<ApiResultBean<Object>> requestDeviceInfoSave(@Body MultipartBody body);

        //获取设备信息
        @POST("deviceinfo/detail")
        Observable<ApiResultBean<Object>> requestDeviceInfo(@Body MultipartBody body);

    }
}
