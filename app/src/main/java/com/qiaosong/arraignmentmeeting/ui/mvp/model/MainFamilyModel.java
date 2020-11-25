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
        RetrofitHttpParams params = new RetrofitHttpParams(mContext);
        params.put("mettingCode", Integer.parseInt(code));
        new AppSubscribe(mContext).requestValidateToken(params.getRequestParams(), new ApiObserver<LoginTokenBean>(mContext, true) {
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
