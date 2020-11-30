package com.qiaosong.arraignmentmeeting.ui.mvp.presenter;

import android.text.TextUtils;

import com.qiaosong.arraignmentmeeting.AppCacheManager;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.ui.activity.prisoner.MainPrisonerActivity;
import com.qiaosong.arraignmentmeeting.ui.base.BasePresenter;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainPrisonerContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.model.MainPrisonerModel;
import com.qiaosong.baselibrary.utils.ToastUtils;

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
        if (isViewAttach()) {
            if (TextUtils.isEmpty(AppCacheManager.getInstance().getDeviceUuid())) {
                ToastUtils.show(mvpReference.get(), "请先在设置中配置设备信息");
                return;
            }
            if (TextUtils.isEmpty(id) || id.length() != 18) {
                ToastUtils.show(mvpReference.get(), "请输入18位身份证号");
                return;
            }
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
