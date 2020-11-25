
package com.qiaosong.arraignmentmeeting.fsp;

import android.content.Context;
import android.view.SurfaceView;

import com.hst.fsp.FspEngine;
import com.hst.fsp.FspEngineConfigure;
import com.hst.fsp.IFspEngineEventHandler;
import com.hst.fsp.VideoProfile;
import com.qiaosong.arraignmentmeeting.event.EventConstant;
import com.qiaosong.arraignmentmeeting.event.TagValueEvent;
import com.qiaosong.arraignmentmeeting.event.bean.JoinGroupEventBean;
import com.qiaosong.arraignmentmeeting.event.bean.LoginResultEventBean;
import com.qiaosong.arraignmentmeeting.event.bean.RemoteVideoEventBean;
import com.qiaosong.arraignmentmeeting.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

public class FspEngineManager implements IFspEngineEventHandler {
    private static volatile FspEngineManager instance = null;
    private static final String APP_ID = "7fb488ff2eb5887cc615c183a1e61097";
    private static final String APP_SECRETKEY = "25422ccb1bed9cca";
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

    public int init(Context context) {

        FspEngineConfigure configure = new FspEngineConfigure();
        configure.serverAddr = "";
        configure.hardwareEncNumber = 1;
        configure.hardwareDecNumber = 0;
        fspEngine = FspEngine.create(context, APP_ID, configure, this);
//        VideoProfile profile = new VideoProfile(1920, 1080, 15);
//        fspEngine.setVideoProfile(profile);
        return fspEngine.init();
    }

    public int login(String userId, String token) {
        return fspEngine.login(token, userId);
    }

    public int joinGroup(String groupId) {
        return fspEngine.joinGroup(groupId);
    }

    public void startPreviewVideo(SurfaceView surfaceView) {
        fspEngine.startPreviewVideo(surfaceView);
    }

    public void startPublishVideo() {
        fspEngine.startPublishVideo();
    }

    public void setRemoteVideoRender(String userId, String videoId, SurfaceView renderView, int renderMode) {
        fspEngine.setRemoteVideoRender(userId, videoId, renderView, renderMode);
    }

    public void startPublishAudio() {
        fspEngine.startPublishAudio();
    }

    public void stopPublishAudio() {
        fspEngine.stopPublishAudio();
    }

    public void onDestroy() {
        if (fspEngine != null)
            fspEngine.destroy();
        fspEngine = null;
    }

    public String getToken(String userId) {
        FspToken tokenBuilder = new FspToken();
        tokenBuilder.setAppId(APP_ID);
        tokenBuilder.setSecretKey(APP_SECRETKEY);
        tokenBuilder.setUserId(userId);
        return tokenBuilder.build();
    }

    @Override
    public void onLoginResult(int result) {
        LogUtils.d("zxy", "onLoginResult:" + result);
        EventBus.getDefault().post(new TagValueEvent(EventConstant.LOGIN_RESULT_EVENT, new LoginResultEventBean(result)));

    }

    @Override
    public void onJoinGroupResult(int result) {
        LogUtils.d("zxy", "onJoinGroupResult:" + result);
        EventBus.getDefault().post(new TagValueEvent(EventConstant.JOIN_GROUP_EVENT, new JoinGroupEventBean(result)));

    }

    @Override
    public void onLeaveGroupResult(int i) {
        LogUtils.d("zxy", "onLeaveGroupResult:" + i);
    }

    @Override
    public void onFspEvent(int i) {
        LogUtils.d("zxy", "onFspEvent:" + i);
    }

    @Override
    public void onRemoteVideoEvent(String userId, String videoId, int eventType) {
        LogUtils.d("zxy", "onRemoteVideoEvent:" + userId + "," + videoId + "," + eventType);
        EventBus.getDefault().post(new TagValueEvent(EventConstant.REMOTE_VIDEO_EVENT, new RemoteVideoEventBean(userId, videoId, eventType)));
    }

    @Override
    public void onRemoteAudioEvent(String s, int i) {
        LogUtils.d("zxy", "onRemoteAudioEvent:" + s + "," + i);
    }

    @Override
    public void onGroupUsersRefreshed(String[] strings) {
        LogUtils.d("zxy", "onGroupUsersRefreshed:" + strings);
    }

    @Override
    public void onRemoteUserEvent(String s, int i) {
        LogUtils.d("zxy", "onRemoteUserEvent:" + s + "," + i);
    }
}
