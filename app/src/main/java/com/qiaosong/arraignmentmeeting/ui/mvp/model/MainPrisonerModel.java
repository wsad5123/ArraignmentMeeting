package com.qiaosong.arraignmentmeeting.ui.mvp.model;

import android.content.Context;
import android.text.TextUtils;

import com.qiaosong.arraignmentmeeting.AppCacheManager;
import com.qiaosong.arraignmentmeeting.bean.CardIdRoomIdBean;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.http.ApiObserver;
import com.qiaosong.arraignmentmeeting.http.RetrofitHttpParams;
import com.qiaosong.arraignmentmeeting.http.subscribe.AppSubscribe;
import com.qiaosong.arraignmentmeeting.ui.base.BaseModel;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainPrisonerContacts;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainPrisonerModel extends BaseModel implements MainPrisonerContacts.IMainPrisonerModel {

    public MainPrisonerModel(Context mContext) {
        super(mContext);
    }


    /**
     * 获取token
     *
     * @param code
     */
    @Override
    public void httpGetToken(String code, MvpDataCallBack<LoginTokenBean> callBack) {
        MultipartBody.Builder builder = new RetrofitHttpParams(mContext).getRequestMultipartBody();
        builder.addFormDataPart("mettingCode", code);
        new AppSubscribe(mContext).requestValidateToken(builder.build(), new ApiObserver<LoginTokenBean>(mContext, true) {
            @Override
            public void onSuccess(LoginTokenBean data) {
                if (data != null) {
                    AppCacheManager.getInstance().setLoginTokenBean(data);
                    callBack.onData(data);
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }


    /**
     * 获取token
     *
     * @param id
     */
    @Override
    public void httpGetOrderCodeByCrimanalsCardId(String id, MvpDataCallBack<String> callBack) {
        MultipartBody.Builder builder = new RetrofitHttpParams(mContext).getRequestMultipartBody();
        builder.addFormDataPart("crminalscardid", id);
        new AppSubscribe(mContext).requestOrderCodeByCrimanalsCardId(builder.build(), new ApiObserver<CardIdRoomIdBean>(mContext, true) {
            @Override
            public void onSuccess(CardIdRoomIdBean data) {
                if (data != null && !TextUtils.isEmpty(data.getRoomId()))
                    callBack.onData(data.getRoomId());
            }

            @Override
            public void onFinish() {
            }
        });
    }
}
