package com.qiaosong.arraignmentmeeting.ui.activity;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.base.IPresenter;

public class AdminPasswordActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_admin_password;
    }

    @Override
    public IPresenter bindPresenter() {
        return null;
    }

    @Override
    public boolean isShowActionBar() {
        return false;
    }

    @Override
    public boolean isShowStatusTitle() {
        return false;
    }
}
