package com.qiaosong.arraignmentmeeting;

import android.content.Context;

import com.hst.fsp.FspEngine;
import com.hst.fsp.IFspEngineEventHandler;

public class FspEngineManager implements IFspEngineEventHandler {
    private static volatile FspEngineManager instance = null;

    private static FspEngine fspEngine;

    private FspEngineManager(Context context) {
        fspEngine = FspEngine.create(context, "123", null, this);
        fspEngine.init();
    }

    public static FspEngineManager getInstance(Context context) {
        if (instance == null) {
            synchronized (FspEngineManager.class) {
                if (instance == null) {
                    instance = new FspEngineManager(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onLoginResult(int i) {

    }

    @Override
    public void onJoinGroupResult(int i) {

    }

    @Override
    public void onLeaveGroupResult(int i) {

    }

    @Override
    public void onFspEvent(int i, int i1) {

    }

    @Override
    public void onRemoteVideoEvent(String s, String s1, int i) {

    }

    @Override
    public void onRemoteAudioEvent(String s, int i) {

    }

    @Override
    public void onGroupUsersRefreshed(String[] strings) {

    }

    @Override
    public void onRemoteUserEvent(String s, int i) {

    }
}
