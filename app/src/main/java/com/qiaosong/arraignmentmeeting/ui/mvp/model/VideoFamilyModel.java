package com.qiaosong.arraignmentmeeting.ui.mvp.model;

import android.content.Context;

import com.qiaosong.arraignmentmeeting.AppCacheManager;
import com.qiaosong.arraignmentmeeting.bean.BeginMeetBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.http.ApiObserver;
import com.qiaosong.arraignmentmeeting.http.RetrofitHttpParams;
import com.qiaosong.arraignmentmeeting.http.subscribe.AppSubscribe;
import com.qiaosong.arraignmentmeeting.ui.base.BaseModel;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.VideoFamilyContacts;

import okhttp3.MultipartBody;

public class VideoFamilyModel extends BaseModel implements VideoFamilyContacts.IVideoFamilyModel {
    private boolean isBegin;

    public VideoFamilyModel(Context mContext) {
        super(mContext);
    }

    /**
     * 获取是否开始会见
     */
    @Override
    public void getHttpIsBeginMeeting(MvpDataCallBack<Boolean> callBack) {
        MultipartBody.Builder builder = new RetrofitHttpParams(mContext).getRequestMultipartBody();
        builder.addFormDataPart("meettingCode", AppCacheManager.getInstance().getLoginTokenBean().getGroupId());
        new AppSubscribe(mContext).requestIsbeginMeetting(builder.build(), new ApiObserver<BeginMeetBean>(mContext) {
            @Override
            public void onSuccess(BeginMeetBean data) {
                if (data != null && data.getIsbegin()) {
                    isBegin = true;
                    callBack.onData(data.getIsbegin());
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }

    /**
     * 是否开始视频
     *
     * @return
     */
    @Override
    public boolean isBegin() {
        return isBegin;
    }
}
