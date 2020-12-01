package com.qiaosong.arraignmentmeeting.bean;

public class MeetingBean {

    private String id;
    private String pseatuuid;
    private String fseatuuid;
    private String addtime;
    private String roomid;
    private String orderuuid;
    private String crminalscardid;
    private String isbegin;
    private String plustime;
    private String beginmeettingtime;
    private String endmeettingtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPseatuuid() {
        return pseatuuid;
    }

    public void setPseatuuid(String pseatuuid) {
        this.pseatuuid = pseatuuid;
    }

    public String getFseatuuid() {
        return fseatuuid;
    }

    public void setFseatuuid(String fseatuuid) {
        this.fseatuuid = fseatuuid;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getOrderuuid() {
        return orderuuid;
    }

    public void setOrderuuid(String orderuuid) {
        this.orderuuid = orderuuid;
    }

    public String getCrminalscardid() {
        return crminalscardid;
    }

    public void setCrminalscardid(String crminalscardid) {
        this.crminalscardid = crminalscardid;
    }

    public String getIsbegin() {
        return isbegin;
    }

    public void setIsbegin(String isbegin) {
        this.isbegin = isbegin;
    }

    public String getPlustime() {
        return plustime;
    }

    public void setPlustime(String plustime) {
        this.plustime = plustime;
    }

    public String getBeginmeettingtime() {
        return beginmeettingtime;
    }

    public void setBeginmeettingtime(String beginmeettingtime) {
        this.beginmeettingtime = beginmeettingtime;
    }

    public String getEndmeettingtime() {
        return endmeettingtime;
    }

    public void setEndmeettingtime(String endmeettingtime) {
        this.endmeettingtime = endmeettingtime;
    }

    public boolean isBegin() {
        if ("begin".equals(isbegin)) {
            return true;
        }
        return false;
    }

    public boolean isWait() {
        if ("waitBegin".equals(isbegin)) {
            return true;
        }
        return false;
    }

    public boolean isEnd() {
        if ("end".equals(isbegin)) {
            return true;
        }
        return false;
    }
}
