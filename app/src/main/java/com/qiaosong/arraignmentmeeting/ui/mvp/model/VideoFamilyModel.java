package com.qiaosong.arraignmentmeeting.ui.mvp.model;

import android.content.Context;

import com.qiaosong.arraignmentmeeting.ui.base.BaseModel;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.VideoFamilyContacts;

public class VideoFamilyModel extends BaseModel implements VideoFamilyContacts.IVideoFamilyModel {

    public VideoFamilyModel(Context mContext) {
        super(mContext);
    }
}
