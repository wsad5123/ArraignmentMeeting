package com.qiaosong.arraignmentmeeting.http;


import com.qiaosong.arraignmentmeeting.bean.BeginMeetBean;
import com.qiaosong.arraignmentmeeting.bean.CardIdRoomIdBean;
import com.qiaosong.arraignmentmeeting.bean.CityBean;
import com.qiaosong.arraignmentmeeting.bean.DeviceInfoBean;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.bean.ProvinceBean;
import com.qiaosong.arraignmentmeeting.bean.PrisonBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorTypeBean;
import com.qiaosong.arraignmentmeeting.bean.api.ApiDeviceInfoBean;
import com.qiaosong.arraignmentmeeting.bean.api.ApiMeetBean;
import com.qiaosong.arraignmentmeeting.bean.api.ApiRegulatorBean;
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
        Observable<ApiResultBean<ApiMeetBean>> requestIsbeginMeetting(@Body MultipartBody body);

        //获取是否开始会见
        @POST("webManager/endMeetting")
        Observable<ApiResultBean<Object>> requestEndMeeting(@Body MultipartBody body);

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
        @POST("prisonunitinfo/getUnitListByType")
        Observable<ApiResultBean<ApiRegulatorBean>> requestAllRegulator(@Body MultipartBody body);

        //设备信息保存
        @POST("deviceinfo/createOrUpdate")
        Observable<ApiResultBean<DeviceInfoBean>> requestDeviceInfoSave(@Body MultipartBody body);

        //获取设备信息
        @POST("deviceinfo/detail")
        Observable<ApiResultBean<ApiDeviceInfoBean>> requestDeviceInfo(@Body MultipartBody body);

        //标记家属端登录
        @POST("seatlock/markfamonline")
        Observable<ApiResultBean<Object>> requestMarkFamonLine(@Body MultipartBody body);

        //标记罪犯端登录
        @POST("seatlock/markcrmonline")
        Observable<ApiResultBean<Object>> requestMarkCrmonLine(@Body MultipartBody body);


    }
}
