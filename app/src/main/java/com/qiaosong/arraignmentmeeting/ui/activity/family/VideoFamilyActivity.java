package com.qiaosong.arraignmentmeeting.ui.activity.family;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hst.fsp.FspEngine;
import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.bean.MeetingBean;
import com.qiaosong.arraignmentmeeting.event.EventConstant;
import com.qiaosong.arraignmentmeeting.event.TagValueEvent;
import com.qiaosong.arraignmentmeeting.event.bean.RemoteVideoEventBean;
import com.qiaosong.arraignmentmeeting.fsp.FspEngineManager;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.VideoFamilyContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.presenter.VideoFamilyPresenter;
import com.qiaosong.arraignmentmeeting.ui.provider.SurfaceViewOutlineProvider;
import com.qiaosong.arraignmentmeeting.ui.viewholder.TitleViewHolder;
import com.qiaosong.baselibrary.utils.PxUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

public class VideoFamilyActivity extends BaseActivity<VideoFamilyPresenter> implements VideoFamilyContacts.IVideoFamilyView {
    @BindView(R.id.sv)
    SurfaceView sv;
    @BindView(R.id.sv_self)
    SurfaceView svSelf;
    @BindView(R.id.rl_parent)
    RelativeLayout rlParent;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.tv_count)
    TextView tvCount;

    boolean isPublish;

    TitleViewHolder titleViewHolder;


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
        titleViewHolder = new TitleViewHolder(mContext, rlParent);
        titleViewHolder.initVideoView(0, "");
        rlParent.addView(titleViewHolder.getView());
        svSelf.setZOrderMediaOverlay(true);
        svSelf.setOutlineProvider(new SurfaceViewOutlineProvider(PxUtils.dip2px(5)));
        svSelf.setClipToOutline(true);
        FspEngineManager.getInstance().startPreviewVideo(svSelf);
        mvpPresenter.getIsBeginMeeting();
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

    /**
     * 开始会见回调
     *
     * @param meetingBean
     * @param restTime
     */
    @Override
    public void onIsBeginVideo(MeetingBean meetingBean, String restTime) {
        titleViewHolder.initVideoView(meetingBean.isWait() ? 0 : meetingBean.isBegin() ? 1 : 2, restTime);
        if (meetingBean.isBegin()) {//开始会见才会推送流
            if (!TextUtils.isEmpty(restTime)) {
                long time = Long.parseLong(restTime);
                if (time < 11 && time > 0) {
                    tvCount.setVisibility(View.VISIBLE);
                    tvCount.setText(time + "");
                } else {
                    tvCount.setVisibility(View.GONE);
                }
            } else {
                tvCount.setVisibility(View.GONE);
            }
            if (!isPublish) {
                isPublish = true;
                FspEngineManager.getInstance().startPublishVideo();
                FspEngineManager.getInstance().startPublishAudio();
            }
        } else {
            tvCount.setVisibility(View.GONE);
            if (isPublish) {
                isPublish = false;
                FspEngineManager.getInstance().stopPublishVideo();
                FspEngineManager.getInstance().stopPublishAudio();
            }
        }

        if (meetingBean.isWait()) {//等待中出现
            tvTips.setVisibility(View.VISIBLE);
        } else {
            tvTips.setVisibility(View.GONE);
        }

        if (meetingBean.isEnd()) {
            tvCount.postDelayed(new Runnable() {
                @Override
                public void run() {
                    VideoFamilyActivity.this.finish();
                }
            }, 1000);
        }
    }
}
