package com.qiaosong.arraignmentmeeting.http;


import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.baselibrary.bean.ApiNormalBean;
import com.qiaosong.baselibrary.bean.ApiResultBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public class Api {

    public interface AppInterface {
        //获取token
        @Multipart
        @POST("seatlock/validateToken")
        Observable<ApiResultBean<LoginTokenBean>> requestValidateToken(@PartMap Map<String, Object> args);

        //获取更具身份证号获取会见码
        @Multipart
        @POST("seatlock/getOrderCodeByCrimanalsCardId")
        Observable<ApiResultBean<Boolean>> requestOrderCodeByCrimanalsCardId(@PartMap Map<String, Object> args);

    }
}
