package com.qiaosong.arraignmentmeeting.ui.mvp.presenter;

import android.text.TextUtils;

import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.ui.activity.family.MainFamilyActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainFamilyContacts;
import com.qiaosong.arraignmentmeeting.ui.base.BasePresenter;
import com.qiaosong.arraignmentmeeting.ui.mvp.model.MainFamilyModel;

public class MainFamilyPresenter extends BasePresenter<MainFamilyActivity> implements MainFamilyContacts.IMainFamilyPresenter {
    private MainFamilyModel mModel;

    public MainFamilyPresenter(MainFamilyActivity view) {
        super(view);
        mModel = new MainFamilyModel(mvpReference.get());
    }

    /**
     * 获取token等数据
     *
     * @param code
     */
    @Override
    public void getToken(String code) {
        if (isViewAttach() && !TextUtils.isEmpty(code) && code.length() == 4) {
            mModel.httpGetToken(code, new MvpDataCallBack<LoginTokenBean>() {
                @Override
                public void onData(LoginTokenBean data) {
                    mvpReference.get().onLoginToken(data);
                }
            });
        }
    }
}
