
package com.qiaosong.arraignmentmeeting.fsp;

import android.content.Context;
import android.view.SurfaceView;

import com.hst.fsp.FspEngine;
import com.hst.fsp.IFspEngineEventHandler;
import com.qiaosong.arraignmentmeeting.utils.LogUtils;

public class FspEngineManager implements IFspEngineEventHandler {
    private static volatile FspEngineManager instance = null;
    private static final String APP_ID = "925aa51ebf829d49fc98b2fca5d963bc";
    private static final String APP_SECRETKEY = "d52be60bb810d17e";
    private static final String USER_ID = "333222";
    private static FspEngine fspEngine;

    private FspEngineManager() {

    }

    public static FspEngineManager getInstance() {
        if (instance == null) {
            synchronized (FspEngineManager.class) {
                if (instance == null) {
                    instance = new FspEngineManager();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        fspEngine = FspEngine.create(context, APP_ID, null, this);
//        fspEngine.init();
    }

    public void login() {
        fspEngine.login(getToken(), USER_ID);
    }

    public void joinGroup(String groupId) {
        fspEngine.joinGroup(groupId);
    }

    public void startPreviewVideo(SurfaceView surfaceView) {
        fspEngine.startPreviewVideo(surfaceView);
    }

    public String getToken() {
        FspToken tokenBuilder = new FspToken();
        tokenBuilder.setAppId(APP_ID);
        tokenBuilder.setSecretKey(APP_SECRETKEY);
        tokenBuilder.setUserId(USER_ID);
        return tokenBuilder.build();
    }

    @Override
    public void onLoginResult(int i) {
        LogUtils.d("onLoginResult:" + i);
    }

    @Override
    public void onJoinGroupResult(int i) {
        LogUtils.d("onJoinGroupResult:" + i);
    }

    @Override
    public void onLeaveGroupResult(int i) {
        LogUtils.d("onLeaveGroupResult:" + i);
    }

    @Override
    public void onFspEvent(int i) {
        LogUtils.d("onFspEvent:" + i);
    }

    @Override
    public void onRemoteVideoEvent(String s, String s1, int i) {
        LogUtils.d("onRemoteVideoEvent:" + s + "," + s1 + "," + i);
    }

    @Override
    public void onRemoteAudioEvent(String s, int i) {
        LogUtils.d("onRemoteAudioEvent:" + s + "," + i);
    }

    @Override
    public void onGroupUsersRefreshed(String[] strings) {
        LogUtils.d("onGroupUsersRefreshed:" + strings);
    }

    @Override
    public void onRemoteUserEvent(String s, int i) {
        LogUtils.d("onRemoteUserEvent:" + s + "," + i);
    }
}
