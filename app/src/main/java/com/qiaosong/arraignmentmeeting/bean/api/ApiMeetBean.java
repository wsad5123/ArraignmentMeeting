package com.qiaosong.arraignmentmeeting.bean.api;

import com.qiaosong.arraignmentmeeting.bean.MeetingBean;

public class ApiMeetBean {
    private String resttime;
    private MeetingBean seatinfo;
    private long timestamp;

    public String getResttime() {
        return resttime;
    }

    public void setResttime(String resttime) {
        this.resttime = resttime;
    }

    public MeetingBean getSeatinfo() {
        return seatinfo;
    }

    public void setSeatinfo(MeetingBean seatinfo) {
        this.seatinfo = seatinfo;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
