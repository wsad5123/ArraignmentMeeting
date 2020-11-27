package com.qiaosong.arraignmentmeeting.bean;

public class BaseInformationBean {
    private String serviceIp;
    private String servicePort;
    private ProvinceBean provinceBean;
    private CityBean cityBean;
    private RegulatorTypeBean regulatorTypeBean;
    private RegulatorBean regulatorBean;
    private String deviceName;

    public BaseInformationBean() {
    }

    public BaseInformationBean(String serviceIp, String servicePort, ProvinceBean provinceBean, CityBean cityBean, RegulatorTypeBean regulatorTypeBean, RegulatorBean regulatorBean, String deviceName) {
        this.serviceIp = serviceIp;
        this.servicePort = servicePort;
        this.provinceBean = provinceBean;
        this.cityBean = cityBean;
        this.regulatorTypeBean = regulatorTypeBean;
        this.regulatorBean = regulatorBean;
        this.deviceName = deviceName;
    }

    public String getServiceIp() {
        return serviceIp;
    }

    public void setServiceIp(String serviceIp) {
        this.serviceIp = serviceIp;
    }

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }

    public ProvinceBean getProvinceBean() {
        return provinceBean;
    }

    public void setProvinceBean(ProvinceBean provinceBean) {
        this.provinceBean = provinceBean;
    }

    public CityBean getCityBean() {
        return cityBean;
    }

    public void setCityBean(CityBean cityBean) {
        this.cityBean = cityBean;
    }

    public RegulatorTypeBean getRegulatorTypeBean() {
        return regulatorTypeBean;
    }

    public void setRegulatorTypeBean(RegulatorTypeBean regulatorTypeBean) {
        this.regulatorTypeBean = regulatorTypeBean;
    }

    public RegulatorBean getRegulatorBean() {
        return regulatorBean;
    }

    public void setRegulatorBean(RegulatorBean regulatorBean) {
        this.regulatorBean = regulatorBean;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
