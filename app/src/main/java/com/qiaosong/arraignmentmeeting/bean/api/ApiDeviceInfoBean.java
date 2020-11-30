package com.qiaosong.arraignmentmeeting.bean.api;

import com.qiaosong.arraignmentmeeting.bean.AddressBean;
import com.qiaosong.arraignmentmeeting.bean.ApiPrisonBean;
import com.qiaosong.arraignmentmeeting.bean.DeviceInfoBean;
import com.qiaosong.arraignmentmeeting.bean.FunitBean;
import com.qiaosong.arraignmentmeeting.bean.PrisonBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorTypeBean;

public class ApiDeviceInfoBean {
    private AddressBean address;
    private ApiPrisonBean prisonunitinfo;
    private PrisonBean prisonBean;
    private FunitBean unitinfo;
    private DeviceInfoBean deviceinfo;
    private RegulatorTypeBean typename;

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public PrisonBean getPrisonBean() {
        if (prisonBean == null && prisonunitinfo != null) {
            prisonBean = new PrisonBean();
            prisonBean.setPrisonAddress(prisonunitinfo.getPrisonaddress());
            prisonBean.setPrisonCcode(prisonunitinfo.getPrisonccode());
            prisonBean.setPrisonCucode(prisonunitinfo.getPrisoncucode());
            prisonBean.setPrisonId(prisonunitinfo.getPrisonid());
            prisonBean.setPrisonName(prisonunitinfo.getPrisonname());
            prisonBean.setPrisonPcode(prisonunitinfo.getPrisonpcode());
            prisonBean.setPrisonTypeUUID(prisonunitinfo.getPrisontypeuuid());
            prisonBean.setPrisonUUID(prisonunitinfo.getPrisonuuid());
        }
        return prisonBean;
    }

    public void setPrisonBean(PrisonBean prisonBean) {
        this.prisonBean = prisonBean;
    }

    public void setPrisonunitinfo(ApiPrisonBean prisonunitinfo) {
        this.prisonunitinfo = prisonunitinfo;
    }

    public FunitBean getUnitinfo() {
        return unitinfo;
    }

    public void setUnitinfo(FunitBean unitinfo) {
        this.unitinfo = unitinfo;
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
