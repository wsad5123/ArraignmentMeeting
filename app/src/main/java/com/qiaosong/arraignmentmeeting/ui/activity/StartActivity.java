package com.qiaosong.arraignmentmeeting.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.hst.fsp.FspEngine;
import com.qiaosong.arraignmentmeeting.AppApplication;
import com.qiaosong.arraignmentmeeting.BuildConfig;
import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.fsp.FspEngineManager;
import com.qiaosong.arraignmentmeeting.ui.activity.family.MainFamilyActivity;
import com.qiaosong.arraignmentmeeting.ui.activity.family.VideoFamilyActivity;
import com.qiaosong.arraignmentmeeting.ui.activity.prisoner.MainPrisonerActivity;
import com.qiaosong.arraignmentmeeting.ui.activity.prisoner.VideoPrisonerActivity;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.base.IPresenter;
import com.qiaosong.baselibrary.utils.PermissionsUtils;

import javax.annotation.Nullable;

import io.reactivex.functions.Consumer;

public class StartActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    public IPresenter bindPresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.APPLICATION_ID.equals("com.qiaosong.arraignmentmeeting.family")) {

            startActivity(new Intent(mContext, MainFamilyActivity.class));

        } else {
            startActivity(new Intent(mContext, MainPrisonerActivity.class));

        }
    }
}
