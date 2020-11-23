package com.qiaosong.arraignmentmeeting.ui.activity.family;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hst.fsp.FspEngine;
import com.qiaosong.arraignmentmeeting.AppApplication;
import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.event.EventConstant;
import com.qiaosong.arraignmentmeeting.event.TagValueEvent;
import com.qiaosong.arraignmentmeeting.event.bean.LoginResultEventBean;
import com.qiaosong.arraignmentmeeting.event.bean.RemoteVideoEventBean;
import com.qiaosong.arraignmentmeeting.fsp.FspEngineManager;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainFamilyContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.presenter.MainFamilyPresenter;
import com.qiaosong.baselibrary.utils.PermissionsUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainFamilyActivity extends BaseActivity<MainFamilyPresenter> implements MainFamilyContacts.IMainFamilyView {

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.v_one)
    View vOne;
    @BindView(R.id.rl_one)
    RelativeLayout rlOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.v_two)
    View vTwo;
    @BindView(R.id.rl_two)
    RelativeLayout rlTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.v_three)
    View vThree;
    @BindView(R.id.rl_three)
    RelativeLayout rlThree;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.v_four)
    View vFour;
    @BindView(R.id.rl_four)
    RelativeLayout rlFour;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.v_setting)
    View vSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return R.layout.activity_main_family;
    }

    @Override
    public MainFamilyPresenter bindPresenter() {
        return new MainFamilyPresenter(this);
    }

    @OnClick(R.id.btn_sure)
    public void onClick(View view) {
        PermissionsUtils.requestPermissions((BaseActivity) mContext, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                if (FspEngineManager.getInstance().init(AppApplication.getInstance()) == FspEngine.ERR_OK) {
                    FspEngineManager.getInstance().login(new Random().nextInt(10) + "" + new Random().nextInt(10) + "" + new Random().nextInt(10));
                }
            }
        }, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_WIFI_STATE});
    }

    @Subscribe
    public void onEvent(TagValueEvent event) {
        if (EventConstant.LOGIN_RESULT_EVENT.equals(event.getTag())) {
            if (event.getValue() instanceof LoginResultEventBean) {
                LoginResultEventBean bean = (LoginResultEventBean) event.getValue();
                if (bean.isOk()) {
                    startActivity(new Intent(mContext, VideoFamilyActivity.class));
                }
            }
        }
    }

}