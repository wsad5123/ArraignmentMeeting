package com.qiaosong.arraignmentmeeting.ui.activity.family;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.VideoFamilyContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.presenter.VideoFamilyPresenter;

public class VideoFamilyActivity extends BaseActivity<VideoFamilyPresenter> implements VideoFamilyContacts.IVideoFamilyView {
    @Override
    public int getLayoutId() {
        return R.layout.activity_video_family;
    }

    @Override
    public VideoFamilyPresenter bindPresenter() {
        return new VideoFamilyPresenter(this);
    }
}
