package com.qiaosong.arraignmentmeeting.ui.mvp.presenter;

import com.qiaosong.arraignmentmeeting.ui.activity.family.MainFamilyActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainFamilyContacts;
import com.qiaosong.arraignmentmeeting.ui.base.BasePresenter;
import com.qiaosong.arraignmentmeeting.ui.mvp.model.MainFamilyModel;

public class MainFamilyPresenter extends BasePresenter<MainFamilyActivity> implements MainFamilyContacts.IMainFamilyPresenter {
    private MainFamilyModel mModel;

    public MainFamilyPresenter(MainFamilyActivity view) {
        super(view);
        mModel = new MainFamilyModel(mvpReference.get());
    }
}
