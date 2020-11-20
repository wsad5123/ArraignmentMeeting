package com.qiaosong.arraignmentmeeting.ui.activity.prisoner;

import android.os.Bundle;
import android.view.SurfaceView;

import com.hst.fsp.FspEngine;
import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.event.EventConstant;
import com.qiaosong.arraignmentmeeting.event.TagValueEvent;
import com.qiaosong.arraignmentmeeting.event.bean.RemoteVideoEventBean;
import com.qiaosong.arraignmentmeeting.fsp.FspEngineManager;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.VideoPrisonerContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.presenter.VideoPrisonerPresenter;
import com.qiaosong.arraignmentmeeting.ui.provider.SurfaceViewOutlineProvider;
import com.qiaosong.baselibrary.utils.PxUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

public class VideoPrisonerActivity extends BaseActivity<VideoPrisonerPresenter> implements VideoPrisonerContacts.IVideoPrisonerView {
    @BindView(R.id.sv)
    SurfaceView sv;
    @BindView(R.id.sv_self)
    SurfaceView svSelf;

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_prisoner;
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
    public VideoPrisonerPresenter bindPresenter() {
        return new VideoPrisonerPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        svSelf.setZOrderOnTop(true);
        svSelf.setOutlineProvider(new SurfaceViewOutlineProvider(PxUtils.dip2px(5)));
        svSelf.setClipToOutline(true);
        FspEngineManager.getInstance().joinGroup("258963");
        FspEngineManager.getInstance().startPreviewVideo(svSelf);
        FspEngineManager.getInstance().startPublishVideo();
        FspEngineManager.getInstance().startPublishAudio();
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
        }
    }
}
