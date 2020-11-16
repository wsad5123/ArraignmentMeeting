package com.qiaosong.arraignmentmeeting.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.qiaosong.arraignmentmeeting.BuildConfig;
import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.ui.activity.family.MainFamilyActivity;
import com.qiaosong.arraignmentmeeting.ui.activity.prisoner.MainPrisonerActivity;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.base.IPresenter;

import javax.annotation.Nullable;

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
