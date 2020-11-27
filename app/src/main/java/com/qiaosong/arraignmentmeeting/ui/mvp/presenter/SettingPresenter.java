package com.qiaosong.arraignmentmeeting.ui.mvp.presenter;

import android.text.TextUtils;

import com.qiaosong.arraignmentmeeting.bean.CityBean;
import com.qiaosong.arraignmentmeeting.bean.ProvinceBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorTypeBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.ui.activity.SettingActivity;
import com.qiaosong.arraignmentmeeting.ui.base.BasePresenter;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.SettingContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.model.SettingModel;
import com.qiaosong.baselibrary.utils.ToastUtils;

import java.util.List;

public class SettingPresenter extends BasePresenter<SettingActivity> implements SettingContacts.ISettingPresenter {
    private SettingModel mModel;

    public SettingPresenter(SettingActivity view) {
        super(view);
        mModel = new SettingModel(mvpReference.get());
    }

    /**
     * 获取所有省份数据
     */
    @Override
    public void getAllProvince() {
        if (isViewAttach()) {
            if (mModel.getAllProvince() != null && !mModel.getAllProvince().isEmpty())
                mvpReference.get().onGetAllProvince(mModel.getAllProvince());
            else
                mModel.httpGetAllProvince(new MvpDataCallBack<List<ProvinceBean>>() {
                    @Override
                    public void onData(List<ProvinceBean> data) {
                        mvpReference.get().onGetAllProvince(data);
                    }
                });
        }
    }

    @Override
    public void getCityByCode() {
        if (isViewAttach()) {
            if (mModel.getAllCity() != null && !mModel.getAllCity().isEmpty())
                mvpReference.get().onGetAllCity(mModel.getAllCity());
            else
                mModel.httpGetCityByCode(new MvpDataCallBack<List<CityBean>>() {
                    @Override
                    public void onData(List<CityBean> data) {
                        mvpReference.get().onGetAllCity(data);
                    }
                });
        }
    }

    @Override
    public void getAllRegulatorType() {
        if (isViewAttach()) {
            if (mModel.getAllRegulatorType() != null && !mModel.getAllRegulatorType().isEmpty())
                mvpReference.get().onGetAllRegulatorType(mModel.getAllRegulatorType());
            else
                mModel.httpGetRegulatorsType(new MvpDataCallBack<List<RegulatorTypeBean>>() {
                    @Override
                    public void onData(List<RegulatorTypeBean> data) {
                        mvpReference.get().onGetAllRegulatorType(data);
                    }
                });
        }
    }

    @Override
    public void getAllRegulator() {
        if (isViewAttach()) {
            if (mModel.getAllRegulator() != null && !mModel.getAllRegulator().isEmpty())
                mvpReference.get().onGetAllRegulator(mModel.getAllRegulator());
            else
                mModel.httpGetRegulators(new MvpDataCallBack<List<RegulatorBean>>() {
                    @Override
                    public void onData(List<RegulatorBean> data) {
                        mvpReference.get().onGetAllRegulator(data);
                    }
                });
        }
    }

    @Override
    public void getDeviceInfo() {
        if (isViewAttach()) {
            mModel.httpGetDeviceInfo();
        }
    }

    @Override
    public void postDeviceInfoSave(String serviceIp, String servicePort, String deviceName) {
        if (isViewAttach()) {
            if (TextUtils.isEmpty(serviceIp)) {
                ToastUtils.show(mvpReference.get(), "请输入服务器Ip");
                return;
            }
            if (TextUtils.isEmpty(servicePort)) {
                ToastUtils.show(mvpReference.get(), "请输入服务器端口");
                return;
            }
            if (TextUtils.isEmpty(deviceName)) {
                ToastUtils.show(mvpReference.get(), "请输入设备名称");
                return;
            }
            mModel.httpPostDeviceInfoSave(serviceIp, servicePort, deviceName, new MvpDataCallBack<Boolean>() {
                @Override
                public void onData(Boolean data) {
                    ToastUtils.show(mvpReference.get(), "保存成功");
                    mvpReference.get().finish();
                }
            });
        }
    }

    /**
     * 设置当前选中的省份
     *
     * @param provinceBean
     */
    @Override
    public void setProvinceBean(ProvinceBean provinceBean) {
        if (isViewAttach()) {
            mModel.setProvinceBean(provinceBean);
        }
    }

    @Override
    public void setCityBean(CityBean cityBean) {
        if (isViewAttach()) {
            mModel.setCityBean(cityBean);
        }
    }

    @Override
    public void setRegulatorTypeBean(RegulatorTypeBean regulatorTypeBean) {
        if (isViewAttach()) {
            mModel.setRegulatorTypeBean(regulatorTypeBean);
        }
    }

    @Override
    public void setRegulatorBean(RegulatorBean regulatorBean) {
        if (isViewAttach()) {
            mModel.setRegulatorBean(regulatorBean);
        }
    }

    @Override
    public void removeCityData() {
        if (isViewAttach()) {
            mModel.removeCityData();
        }
    }

    @Override
    public void removeRegulatorData() {
        if (isViewAttach()) {
            mModel.removeRegulatorData();
        }
    }
}
