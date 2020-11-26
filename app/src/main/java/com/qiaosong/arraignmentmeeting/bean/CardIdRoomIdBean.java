package com.qiaosong.arraignmentmeeting.bean;

import com.google.gson.annotations.SerializedName;

public class CardIdRoomIdBean {
    @SerializedName("roomid")
    private String roomId;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
