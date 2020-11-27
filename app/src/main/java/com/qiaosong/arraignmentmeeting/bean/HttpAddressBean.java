package com.qiaosong.arraignmentmeeting.bean;

public class HttpAddressBean {
    private String ip;
    private String port;

    public HttpAddressBean() {

    }

    public HttpAddressBean(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
