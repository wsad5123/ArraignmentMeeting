package com.qiaosong.arraignmentmeeting.ui.mvp.presenter;

import android.text.TextUtils;

import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.ui.activity.prisoner.MainPrisonerActivity;
import com.qiaosong.arraignmentmeeting.ui.base.BasePresenter;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainPrisonerContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.model.MainPrisonerModel;

public class MainPrisonerPresenter extends BasePresenter<MainPrisonerActivity> implements MainPrisonerContacts.IMainPrisonerPresenter {
    private MainPrisonerModel mModel;

    public MainPrisonerPresenter(MainPrisonerActivity view) {
        super(view);
        mModel = new MainPrisonerModel(mvpReference.get());
    }

    /**
     * 获取token
     *
     * @param code
     */
    @Override
    public void getToken(String code) {
        if (isViewAttach() && !TextUtils.isEmpty(code)) {
            mModel.httpGetToken(code, new MvpDataCallBack<LoginTokenBean>() {
                @Override
                public void onData(LoginTokenBean data) {
                    mvpReference.get().onLoginToken(data);
                }
            });
        }
    }

    /**
     * 根据身份证获取会见码
     *
     * @param id
     */
    @Override
    public void getOrderCodeByCrimanalsCardId(String id) {
        if (isViewAttach() && !TextUtils.isEmpty(id)) {
            mModel.httpGetOrderCodeByCrimanalsCardId(id, new MvpDataCallBack<String>() {
                @Override
                public void onData(String data) {
                    if (!TextUtils.isEmpty(data)) {
                        getToken(data);
                    }
                }
            });
        }
    }
}
