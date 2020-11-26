package com.qiaosong.arraignmentmeeting.http;


import com.qiaosong.arraignmentmeeting.bean.BeginMeetBean;
import com.qiaosong.arraignmentmeeting.bean.CardIdRoomIdBean;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.baselibrary.bean.ApiNormalBean;
import com.qiaosong.baselibrary.bean.ApiResultBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

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
    }
}
