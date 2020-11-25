package com.qiaosong.arraignmentmeeting.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.qiaosong.arraignmentmeeting.AppCacheManager;
import com.qiaosong.arraignmentmeeting.BuildConfig;
import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.event.EventConstant;
import com.qiaosong.arraignmentmeeting.event.TagValueEvent;
import com.qiaosong.arraignmentmeeting.event.bean.JoinGroupEventBean;
import com.qiaosong.arraignmentmeeting.event.bean.LoginResultEventBean;
import com.qiaosong.arraignmentmeeting.fsp.FspEngineManager;
import com.qiaosong.arraignmentmeeting.ui.activity.family.MainFamilyActivity;
import com.qiaosong.arraignmentmeeting.ui.activity.family.VideoFamilyActivity;
import com.qiaosong.arraignmentmeeting.ui.activity.prisoner.MainPrisonerActivity;
import com.qiaosong.arraignmentmeeting.ui.activity.prisoner.VideoPrisonerActivity;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.base.IPresenter;
import com.qiaosong.arraignmentmeeting.ui.viewholder.TitleViewHolder;

import org.greenrobot.eventbus.Subscribe;

import javax.annotation.Nullable;

import butterknife.BindView;

public class VideoWaitActivity extends BaseActivity {

    @BindView(R.id.rl_parent)
    RelativeLayout rlParent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rlParent.addView(new TitleViewHolder(mContext, rlParent).getView());
        rlParent.postDelayed(new Runnable() {
            @Override
            public void run() {
                FspEngineManager.getInstance().joinGroup(AppCacheManager.getInstance().getLoginTokenBean().getGroupId());
            }
        }, 10000);
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
    public int getLayoutId() {
        return R.layout.activity_video_wait;
    }

    @Override
    public IPresenter bindPresenter() {
        return null;
    }

    @Subscribe
    public void onEvent(TagValueEvent event) {
        if (EventConstant.JOIN_GROUP_EVENT.equals(event.getTag())) {
            if (event.getValue() instanceof JoinGroupEventBean) {
                JoinGroupEventBean bean = (JoinGroupEventBean) event.getValue();
                if (bean.isOk()) {
                    if (BuildConfig.APPLICATION_ID.equals("com.qiaosong.arraignmentmeeting.family")) {
                        startActivity(new Intent(mContext, VideoFamilyActivity.class));
                    } else {
                        startActivity(new Intent(mContext, VideoPrisonerActivity.class));
                    }
                    finish();
                }
            }
        }
    }


}
