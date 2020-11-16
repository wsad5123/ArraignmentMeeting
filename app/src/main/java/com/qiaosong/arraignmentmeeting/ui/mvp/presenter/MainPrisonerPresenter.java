package com.qiaosong.arraignmentmeeting.ui.mvp.presenter;

import com.qiaosong.arraignmentmeeting.ui.activity.prisoner.MainPrisonerActivity;
import com.qiaosong.arraignmentmeeting.ui.base.BasePresenter;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainPrisonerContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.model.MainPrisonerModel;

public class MainPrisonerPresenter extends BasePresenter<MainPrisonerActivity> implements MainPrisonerContacts.IMainPrisonerPresenter {
    private MainPrisonerModel mModel;

    public MainPrisonerPresenter(MainPrisonerActivity view) {
        super(view);
        mModel = new MainPrisonerModel(mvpReference.get());
    }
}
