package com.qiaosong.arraignmentmeeting.ui.mvp.model;

import android.content.Context;

import com.qiaosong.arraignmentmeeting.ui.base.BaseModel;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainFamilyContacts;

public class MainFamilyModel extends BaseModel implements MainFamilyContacts.IMainFamilyModel {

    public MainFamilyModel(Context mContext) {
        super(mContext);
    }
}
