package com.qiaosong.arraignmentmeeting.ui.activity.family;

import android.Manifest;
import android.os.Bundle;
import android.view.SurfaceView;

import com.hst.fsp.FspEngine;
import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.event.EventConstant;
import com.qiaosong.arraignmentmeeting.event.TagValueEvent;
import com.qiaosong.arraignmentmeeting.event.bean.JoinGroupEventBean;
import com.qiaosong.arraignmentmeeting.event.bean.RemoteVideoEventBean;
import com.qiaosong.arraignmentmeeting.fsp.FspEngineManager;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.VideoFamilyContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.presenter.VideoFamilyPresenter;
import com.qiaosong.arraignmentmeeting.ui.provider.SurfaceViewOutlineProvider;
import com.qiaosong.arraignmentmeeting.ui.widget.RoundSurfaceView;
import com.qiaosong.baselibrary.utils.PxUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

public class VideoFamilyActivity extends BaseActivity<VideoFamilyPresenter> implements VideoFamilyContacts.IVideoFamilyView {
    @BindView(R.id.sv)
    SurfaceView sv;
    @BindView(R.id.sv_self)
    SurfaceView svSelf;

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
        svSelf.setZOrderOnTop(true);
        svSelf.setOutlineProvider(new SurfaceViewOutlineProvider(PxUtils.dip2px(5)));
        svSelf.setClipToOutline(true);
        FspEngineManager.getInstance().joinGroup("5556");
        FspEngineManager.getInstance().startPreviewVideo(svSelf);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FspEngineManager.getInstance().onDestroy();
    }

    @Subscribe
    public void onEvent(TagValueEvent event) {
        if (EventConstant.REMOTE_VIDEO_EVENT.equals(event.getTag())) {
            if (event.getValue() instanceof RemoteVideoEventBean) {
                RemoteVideoEventBean bean = (RemoteVideoEventBean) event.getValue();
                if (bean.getEventType() == FspEngine.REMOTE_VIDEO_PUBLISH_STARTED) {
                    FspEngineManager.getInstance().setRemoteVideoRender(bean.getUserId(), bean.getVideoId(), sv, FspEngine.RENDER_MODE_SCALE_FILL);
                } else if (bean.getEventType() == FspEngine.REMOTE_VIDEO_PUBLISH_STOPED) {
                    FspEngineManager.getInstance().setRemoteVideoRender(bean.getUserId(), bean.getVideoId(), null, FspEngine.RENDER_MODE_SCALE_FILL);
                }
            }
        } else if (EventConstant.JOIN_GROUP_EVENT.equals(event.getTag())) {
            if (event.getValue() instanceof JoinGroupEventBean) {
                JoinGroupEventBean bean = (JoinGroupEventBean) event.getValue();
                if (bean.isOk()) {
                    FspEngineManager.getInstance().startPublishVideo();
                    FspEngineManager.getInstance().startPublishAudio();
                }
            }
        }
    }
}
