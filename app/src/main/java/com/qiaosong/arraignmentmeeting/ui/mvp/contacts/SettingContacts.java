package com.qiaosong.arraignmentmeeting.ui.mvp.contacts;

import com.qiaosong.arraignmentmeeting.bean.CityBean;
import com.qiaosong.arraignmentmeeting.bean.ProvinceBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorTypeBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.ui.base.IPresenter;
import com.qiaosong.arraignmentmeeting.ui.base.IView;

import java.util.List;

public class SettingContacts {

    public interface ISettingView extends IView {

        void onGetAllProvince(List<ProvinceBean> data);

        void onGetAllCity(List<CityBean> data);

        void onGetAllRegulatorType(List<RegulatorTypeBean> data);

        void onGetAllRegulator(List<RegulatorBean> data);
    }

    public interface ISettingPresenter extends IPresenter {
        void getAllProvince();

        void getCityByCode();

        void getAllRegulatorType();

        void getAllRegulator();

        void getDeviceInfo();

        void postDeviceInfoSave(String serviceIp, String servicePort, String deviceName);

        void setProvinceBean(ProvinceBean provinceBean);

        void setCityBean(CityBean cityBean);

        void setRegulatorTypeBean(RegulatorTypeBean regulatorTypeBean);

        void setRegulatorBean(RegulatorBean regulatorBean);

        void removeCityData();

        void removeRegulatorData();
    }

    public interface ISettingModel {
        List<ProvinceBean> getAllProvince();

        List<CityBean> getAllCity();

        List<RegulatorTypeBean> getAllRegulatorType();

        List<RegulatorBean> getAllRegulator();

        void httpGetAllProvince(MvpDataCallBack<List<ProvinceBean>> callBack);

        void httpGetCityByCode(MvpDataCallBack<List<CityBean>> callBack);

        void httpGetRegulatorsType(MvpDataCallBack<List<RegulatorTypeBean>> callBack);

        void httpGetRegulators(MvpDataCallBack<List<RegulatorBean>> callBack);

        void httpPostDeviceInfoSave(String serviceIp, String servicePort, String deviceName, MvpDataCallBack<Boolean> callBack);

        void httpGetDeviceInfo();

        void setProvinceBean(ProvinceBean provinceBean);

        void setCityBean(CityBean cityBean);

        void setRegulatorTypeBean(RegulatorTypeBean regulatorBean);

        void setRegulatorBean(RegulatorBean regulatorBean);

        void removeCityData();

        void removeRegulatorData();
    }

}
