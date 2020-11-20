package com.qiaosong.arraignmentmeeting.ui.mvp.model;

import android.content.Context;

import com.qiaosong.arraignmentmeeting.ui.base.BaseModel;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.VideoPrisonerContacts;

public class VideoPrisonerModel extends BaseModel implements VideoPrisonerContacts.IVideoPrisonerModel {

    public VideoPrisonerModel(Context mContext) {
        super(mContext);
    }
}
