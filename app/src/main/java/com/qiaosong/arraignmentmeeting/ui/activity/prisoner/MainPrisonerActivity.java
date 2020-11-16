package com.qiaosong.arraignmentmeeting.ui.activity.prisoner;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainPrisonerContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.presenter.MainPrisonerPresenter;

public class MainPrisonerActivity extends BaseActivity<MainPrisonerPresenter> implements MainPrisonerContacts.IMainPrisonerView {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main_prisoner;
    }

    @Override
    public MainPrisonerPresenter bindPresenter() {
        return new MainPrisonerPresenter(this);
    }
}
