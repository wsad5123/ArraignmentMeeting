package com.qiaosong.arraignmentmeeting.ui.mvp.model;

import android.content.Context;

import com.qiaosong.arraignmentmeeting.AppCacheManager;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.http.ApiObserver;
import com.qiaosong.arraignmentmeeting.http.RetrofitHttpParams;
import com.qiaosong.arraignmentmeeting.http.subscribe.AppSubscribe;
import com.qiaosong.arraignmentmeeting.ui.base.BaseModel;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainFamilyContacts;
import com.qiaosong.baselibrary.bean.ApiNormalBean;

import okhttp3.MultipartBody;

public class MainFamilyModel extends BaseModel implements MainFamilyContacts.IMainFamilyModel {

    public MainFamilyModel(Context mContext) {
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
        builder.addFormDataPart("deviceUUID", AppCacheManager.getInstance().getDeviceUuid());
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
}
