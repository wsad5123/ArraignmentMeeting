package com.qiaosong.arraignmentmeeting.event.bean;

public class RemoteVideoEventBean {
    private String userId;
    private String videoId;
    private int eventType;

    public RemoteVideoEventBean(String userId, String videoId, int eventType) {
        this.userId = userId;
        this.videoId = videoId;
        this.eventType = eventType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
