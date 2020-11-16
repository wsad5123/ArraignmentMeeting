package com.qiaosong.baselibrary.event;

public class BaseEvent {
    private String data;

    public BaseEvent(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
