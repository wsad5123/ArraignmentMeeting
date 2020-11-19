package com.qiaosong.arraignmentmeeting.ui.activity.family;

import android.os.Bundle;
import android.view.SurfaceView;

import com.qiaosong.arraignmentmeeting.AppApplication;
import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.fsp.FspEngineManager;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.VideoFamilyContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.presenter.VideoFamilyPresenter;

import butterknife.BindView;

public class VideoFamilyActivity extends BaseActivity<VideoFamilyPresenter> implements VideoFamilyContacts.IVideoFamilyView {
    @BindView(R.id.sv)
    SurfaceView sv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_family;
    }

    @Override
    public VideoFamilyPresenter bindPresenter() {
        return new VideoFamilyPresenter(this);
    }

    @Override
    public boolean isShowActionBar() {
        return false;
    }

    @Override
    public boolean isShowStatusTitle() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FspEngineManager.getInstance().init(AppApplication.getInstance());
//        FspEngineManager.getInstance().login();
//        FspEngineManager.getInstance().joinGroup("12345");
//        FspEngineManager.getInstance().startPreviewVideo(sv);
    }
}
