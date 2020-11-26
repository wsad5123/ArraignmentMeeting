package com.qiaosong.arraignmentmeeting.ui.mvp.contacts;

import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.ui.base.IPresenter;
import com.qiaosong.arraignmentmeeting.ui.base.IView;

public class VideoPrisonerContacts {
    public interface IVideoPrisonerView extends IView {

        void onIsBeginVideo();

    }

    public interface IVideoPrisonerPresenter extends IPresenter {
        void getIsBeginMeeting();

    }

    public interface IVideoPrisonerModel {
        void getHttpIsBeginMeeting(MvpDataCallBack<Boolean> callBack);

        boolean isBegin();
    }
}
