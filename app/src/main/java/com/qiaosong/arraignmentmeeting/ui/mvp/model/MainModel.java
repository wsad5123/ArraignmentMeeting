package com.qiaosong.arraignmentmeeting.ui.mvp.model;

import android.content.Context;

import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainContacts;
import com.qiaosong.baselibrary.ui.base.BaseModel;

public class MainModel extends BaseModel implements MainContacts.IMainModel {

    public MainModel(Context mContext) {
        super(mContext);
    }
}
