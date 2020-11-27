package com.qiaosong.arraignmentmeeting.bean.api;

import com.qiaosong.arraignmentmeeting.bean.AddressBean;
import com.qiaosong.arraignmentmeeting.bean.DeviceInfoBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorTypeBean;

public class ApiDeviceInfoBean {
    private AddressBean address;
    private RegulatorBean prisonunitinfo;
    private DeviceInfoBean deviceinfo;
    private RegulatorTypeBean typename;

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public RegulatorBean getPrisonunitinfo() {
        return prisonunitinfo;
    }

    public void setPrisonunitinfo(RegulatorBean prisonunitinfo) {
        this.prisonunitinfo = prisonunitinfo;
    }

    public DeviceInfoBean getDeviceinfo() {
        return deviceinfo;
    }

    public void setDeviceinfo(DeviceInfoBean deviceinfo) {
        this.deviceinfo = deviceinfo;
    }

    public RegulatorTypeBean getTypename() {
        return typename;
    }

    public void setTypename(RegulatorTypeBean typename) {
        this.typename = typename;
    }
}
