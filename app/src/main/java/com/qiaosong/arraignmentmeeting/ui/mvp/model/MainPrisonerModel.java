package com.qiaosong.arraignmentmeeting.ui.mvp.model;

import android.content.Context;

import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.http.ApiObserver;
import com.qiaosong.arraignmentmeeting.http.RetrofitHttpParams;
import com.qiaosong.arraignmentmeeting.http.subscribe.AppSubscribe;
import com.qiaosong.arraignmentmeeting.ui.base.BaseModel;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainPrisonerContacts;

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
        RetrofitHttpParams params = new RetrofitHttpParams(mContext);
        params.put("mettingCode", code);
        new AppSubscribe(mContext).requestValidateToken(params.getRequestParams(), new ApiObserver<LoginTokenBean>(mContext, true) {
            @Override
            public void onSuccess(LoginTokenBean data) {
                if (data != null) {
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
    public void httpGetOrderCodeByCrimanalsCardId(String id) {
        RetrofitHttpParams params = new RetrofitHttpParams(mContext);
        params.put("mettingCode", id);
        new AppSubscribe(mContext).requestOrderCodeByCrimanalsCardId(params.getRequestParams(), new ApiObserver<Boolean>(mContext, true) {
            @Override
            public void onSuccess(Boolean data) {
            }

            @Override
            public void onFinish() {
            }
        });
    }
}
