
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

import java.util.ArrayList;
import java.util.List;

public class FspEngineManager implements IFspEngineEventHandler {
    private static volatile FspEngineManager instance = null;
    private static String APP_ID = "";
    private static String APP_SECRETKEY = "";
    private static FspEngine fspEngine;
    private boolean isInit;
    private boolean isNeedSaveVideoInfo;
    private List<RemoteVideoEventBean> userVideoList;


    private FspEngineManager() {
        userVideoList = new ArrayList<>();
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

    public boolean init(Context context, String serverAddr, String appid, String appsecretkey) {
        if (isInit)
            return true;
        APP_ID = appid;
        APP_SECRETKEY = appsecretkey;
        FspEngineConfigure configure = new FspEngineConfigure();
        configure.serverAddr = serverAddr;
        configure.hardwareEncNumber = 1;
        configure.hardwareDecNumber = 0;
        fspEngine = FspEngine.create(context, APP_ID, configure, this);
        isInit = fspEngine.init() == FspEngine.ERR_OK;
//        VideoProfile profile = new VideoProfile(1920, 1080, 15);
//        fspEngine.setVideoProfile(profile);
        return isInit;
    }

    public int login(String userId, String token) {
        return fspEngine.login(token, userId, true, userId);
    }

    public int joinGroup(String groupId) {
        return fspEngine.joinGroup(groupId);
    }

    public int startPreviewVideo(SurfaceView surfaceView) {
        return fspEngine.startPreviewVideo(surfaceView);
    }

    public int stopPreviewVideo() {
        return fspEngine.stopPreviewVideo();
    }


    public void setRemoteVideoRender(String userId, String videoId, SurfaceView renderView, int renderMode) {
        fspEngine.setRemoteVideoRender(userId, videoId, renderView, renderMode);
    }

    public void startPublishVideo() {
        fspEngine.startPublishVideo();
    }

    public void stopPublishVideo() {
        fspEngine.stopPublishVideo();
    }

    public void startPublishAudio() {
        fspEngine.startPublishAudio();
    }

    public void stopPublishAudio() {
        fspEngine.stopPublishAudio();
    }

    public int logout() {
        return fspEngine.loginOut();
    }

    public void onDestroy() {
        if (fspEngine != null)
            fspEngine.destroy();
        fspEngine = null;
        isInit = false;
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
        if (result == FspEngine.ERR_OK) {
            userVideoList.clear();
            setNeedSaveVideoInfo(true);
        }
        EventBus.getDefault().post(new TagValueEvent(EventConstant.JOIN_GROUP_EVENT, new JoinGroupEventBean(result)));

    }

    @Override
    public void onLeaveGroupResult(int i) {
        LogUtils.d("zxy", "onLeaveGroupResult:" + i);
    }

    @Override
    public void onFspEvent(int i, int i1) {
        LogUtils.d("zxy", "onFspEvent:" + i + "," + i1);
    }

    @Override
    public void onRemoteVideoEvent(String userId, String videoId, int eventType) {
        LogUtils.d("zxy", "onRemoteVideoEvent:" + userId + "," + videoId + "," + eventType);
        if (isNeedSaveVideoInfo()) {
            userVideoList.add(new RemoteVideoEventBean(userId, videoId, eventType));
        } else {
            EventBus.getDefault().post(new TagValueEvent(EventConstant.REMOTE_VIDEO_EVENT, new RemoteVideoEventBean(userId, videoId, eventType)));
        }
    }

    @Override
    public void onRemoteAudioEvent(String s, String s1, int i) {
        LogUtils.d("zxy", "onRemoteAudioEvent:" + s + "," + s1 + "," + i);
    }


    @Override
    public void onGroupUsersRefreshed(String[] strings) {
        LogUtils.d("zxy", "onGroupUsersRefreshed:" + strings);
    }

    @Override
    public void onRemoteUserEvent(String s, int i) {
        LogUtils.d("zxy", "onRemoteUserEvent:" + s + "," + i);
    }

    public boolean isNeedSaveVideoInfo() {
        return isNeedSaveVideoInfo;
    }

    public List<RemoteVideoEventBean> getUserVideoList() {
        return userVideoList;
    }

    public void setUserVideoList(List<RemoteVideoEventBean> userVideoList) {
        this.userVideoList = userVideoList;
    }

    public void setNeedSaveVideoInfo(boolean needSaveVideoInfo) {
        isNeedSaveVideoInfo = needSaveVideoInfo;
    }
}
