package com.qiaosong.arraignmentmeeting.ui.mvp.contacts;

import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.ui.base.IPresenter;
import com.qiaosong.arraignmentmeeting.ui.base.IView;

public class VideoFamilyContacts {
    public interface IVideoFamilyView extends IView {
        void onIsBeginVideo();
    }

    public interface IVideoFamilyPresenter extends IPresenter {
        void getIsBeginMeeting();
    }

    public interface IVideoFamilyModel {
        void getHttpIsBeginMeeting(MvpDataCallBack<Boolean> callBack);

        boolean isBegin();
    }
}
