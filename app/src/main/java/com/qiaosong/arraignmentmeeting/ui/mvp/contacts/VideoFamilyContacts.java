package com.qiaosong.arraignmentmeeting.ui.mvp.contacts;

import com.qiaosong.arraignmentmeeting.bean.MeetingBean;
import com.qiaosong.arraignmentmeeting.bean.api.ApiMeetBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.ui.base.IPresenter;
import com.qiaosong.arraignmentmeeting.ui.base.IView;

public class VideoFamilyContacts {
    public interface IVideoFamilyView extends IView {
        void onIsBeginVideo(MeetingBean meetingBean, String restTime);
    }

    public interface IVideoFamilyPresenter extends IPresenter {
        void getIsBeginMeeting();

        void finishMeeting();
    }

    public interface IVideoFamilyModel {
        void getHttpIsBeginMeeting(MvpDataCallBack<ApiMeetBean> callBack);

        void httpFinishMeeting(MvpDataCallBack<Boolean> callBack);

        String getRestTime();

        MeetingBean getMeetingBean();
    }
}
