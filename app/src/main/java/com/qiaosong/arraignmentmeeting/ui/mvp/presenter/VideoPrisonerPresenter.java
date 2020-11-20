package com.qiaosong.arraignmentmeeting.ui.mvp.presenter;

import com.qiaosong.arraignmentmeeting.ui.activity.prisoner.VideoPrisonerActivity;
import com.qiaosong.arraignmentmeeting.ui.base.BasePresenter;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.VideoPrisonerContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.model.VideoPrisonerModel;

public class VideoPrisonerPresenter extends BasePresenter<VideoPrisonerActivity> implements VideoPrisonerContacts.IVideoPrisonerPresenter {
    private VideoPrisonerModel mModel;

    public VideoPrisonerPresenter(VideoPrisonerActivity view) {
        super(view);
        mModel = new VideoPrisonerModel(mvpReference.get());
    }
}
