package com.qiaosong.arraignmentmeeting.ui.mvp.presenter;

import com.qiaosong.arraignmentmeeting.ui.activity.family.VideoFamilyActivity;
import com.qiaosong.arraignmentmeeting.ui.base.BasePresenter;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.VideoFamilyContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.model.VideoFamilyModel;

public class VideoFamilyPresenter extends BasePresenter<VideoFamilyActivity> implements VideoFamilyContacts.IVideoFamilyPresenter {
    private VideoFamilyModel mModel;

    public VideoFamilyPresenter(VideoFamilyActivity view) {
        super(view);
        mModel = new VideoFamilyModel(mvpReference.get());
    }
}
