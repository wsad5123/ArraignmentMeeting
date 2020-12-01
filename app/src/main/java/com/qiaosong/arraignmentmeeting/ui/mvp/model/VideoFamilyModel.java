package com.qiaosong.arraignmentmeeting.ui.mvp.model;

import android.content.Context;

import com.qiaosong.arraignmentmeeting.AppCacheManager;
import com.qiaosong.arraignmentmeeting.bean.BeginMeetBean;
import com.qiaosong.arraignmentmeeting.bean.MeetingBean;
import com.qiaosong.arraignmentmeeting.bean.api.ApiMeetBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.http.ApiObserver;
import com.qiaosong.arraignmentmeeting.http.RetrofitHttpParams;
import com.qiaosong.arraignmentmeeting.http.subscribe.AppSubscribe;
import com.qiaosong.arraignmentmeeting.ui.base.BaseModel;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.VideoFamilyContacts;

import okhttp3.MultipartBody;

public class VideoFamilyModel extends BaseModel implements VideoFamilyContacts.IVideoFamilyModel {
    private MeetingBean meetingBean;
    private long timeStamp;
    private String restTime;

    public VideoFamilyModel(Context mContext) {
        super(mContext);
    }

    /**
     * 获取是否开始会见
     */
    @Override
    public void getHttpIsBeginMeeting(MvpDataCallBack<ApiMeetBean> callBack) {
        MultipartBody.Builder builder = new RetrofitHttpParams(mContext).getRequestMultipartBody();
        builder.addFormDataPart("meettingCode", AppCacheManager.getInstance().getLoginTokenBean().getGroupId());
        builder.addFormDataPart("timestamp", String.valueOf(System.currentTimeMillis()));
        new AppSubscribe(mContext).requestIsbeginMeetting(builder.build(), new ApiObserver<ApiMeetBean>(mContext) {
            @Override
            public void onSuccess(ApiMeetBean data) {
                if (data != null) {
                    if (data.getTimestamp() > timeStamp) {
                        timeStamp = data.getTimestamp();
                        restTime = data.getResttime();
                        meetingBean = data.getSeatinfo();
                        callBack.onData(data);
                    }
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }

    @Override
    public String getRestTime() {
        return restTime;
    }

    @Override
    public MeetingBean getMeetingBean() {
        return meetingBean;
    }

}
