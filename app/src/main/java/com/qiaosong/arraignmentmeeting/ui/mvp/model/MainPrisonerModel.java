package com.qiaosong.arraignmentmeeting.ui.mvp.model;

import android.content.Context;

import com.qiaosong.arraignmentmeeting.ui.base.BaseModel;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainPrisonerContacts;

public class MainPrisonerModel extends BaseModel implements MainPrisonerContacts.IMainPrisonerModel {

    public MainPrisonerModel(Context mContext) {
        super(mContext);
    }
}
