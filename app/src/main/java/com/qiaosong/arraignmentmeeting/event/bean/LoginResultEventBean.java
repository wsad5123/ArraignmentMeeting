package com.qiaosong.arraignmentmeeting.event.bean;

import com.hst.fsp.FspEngine;

public class LoginResultEventBean {
    private int result;

    public LoginResultEventBean(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isOk() {
        return result == FspEngine.ERR_OK;
    }
}
