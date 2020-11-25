package com.qiaosong.arraignmentmeeting.ui.mvp.contacts;

import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.ui.base.IPresenter;
import com.qiaosong.arraignmentmeeting.ui.base.IView;

public class MainPrisonerContacts {
    public interface IMainPrisonerView extends IView {

        void onLoginToken(LoginTokenBean bean);

    }

    public interface IMainPrisonerPresenter extends IPresenter {
        void getToken(String code);

        void getOrderCodeByCrimanalsCardId(String id);

    }

    public interface IMainPrisonerModel {
        void httpGetToken(String code, MvpDataCallBack<LoginTokenBean> callBack);

        void httpGetOrderCodeByCrimanalsCardId(String id);
    }
}
