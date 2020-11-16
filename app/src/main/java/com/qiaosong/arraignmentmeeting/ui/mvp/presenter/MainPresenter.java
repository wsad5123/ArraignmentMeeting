package com.qiaosong.arraignmentmeeting.ui.mvp.presenter;

import com.qiaosong.arraignmentmeeting.ui.activity.MainActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainContacts;
import com.qiaosong.baselibrary.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainActivity> implements MainContacts.IMainPresenter {
    public MainPresenter(MainActivity view) {
        super(view);
    }
}
