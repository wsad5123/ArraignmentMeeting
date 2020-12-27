package com.qiaosong.arraignmentmeeting.ui.mvp.contacts;

import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.ui.base.IPresenter;
import com.qiaosong.arraignmentmeeting.ui.base.IView;

public class MainFamilyContacts {
    public interface IMainFamilyView extends IView {
        void onLoginToken(LoginTokenBean bean);
    }

    public interface IMainFamilyPresenter extends IPresenter {
        void getToken(String code);

    }

    public interface IMainFamilyModel {
        void httpGetToken(String code, MvpDataCallBack<LoginTokenBean> callBack);

        void httpMarkFamonLine(String code);
    }
}
