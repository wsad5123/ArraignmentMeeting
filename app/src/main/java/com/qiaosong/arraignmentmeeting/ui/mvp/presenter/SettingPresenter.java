package com.qiaosong.arraignmentmeeting.ui.mvp.presenter;

import android.text.TextUtils;

import com.qiaosong.arraignmentmeeting.AppCacheManager;
import com.qiaosong.arraignmentmeeting.bean.CityBean;
import com.qiaosong.arraignmentmeeting.bean.FunitBean;
import com.qiaosong.arraignmentmeeting.bean.HttpAddressBean;
import com.qiaosong.arraignmentmeeting.bean.ProvinceBean;
import com.qiaosong.arraignmentmeeting.bean.PrisonBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorTypeBean;
import com.qiaosong.arraignmentmeeting.bean.api.ApiDeviceInfoBean;
import com.qiaosong.arraignmentmeeting.bean.api.ApiRegulatorBean;
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
                mModel.httpGetRegulators(new MvpDataCallBack<ApiRegulatorBean>() {
                    @Override
                    public void onData(ApiRegulatorBean data) {
                        mvpReference.get().onGetAllRegulator(data);
                    }
                });
        }
    }

    /**
     * 获取设备信息回显数据
     */
    @Override
    public void getDeviceInfo(String serviceIp, String servicePort) {
        if (isViewAttach()) {
            if (TextUtils.isEmpty(serviceIp)) {
                ToastUtils.show(mvpReference.get(), "请输入服务器Ip");
                return;
            }
            if (TextUtils.isEmpty(servicePort)) {
                ToastUtils.show(mvpReference.get(), "请输入服务器端口");
                return;
            }
            AppCacheManager.getInstance().setHttpAddressBean(new HttpAddressBean(serviceIp, servicePort));
            mModel.httpGetDeviceInfo(new MvpDataCallBack<ApiDeviceInfoBean>() {
                @Override
                public void onData(ApiDeviceInfoBean data) {
                    mvpReference.get().onDeviceInfoData(data);
                }
            });
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
    public void setPrisonBean(PrisonBean regulatorBean) {
        if (isViewAttach()) {
            mModel.setPrisonBean(regulatorBean);
        }
    }

    @Override
    public void setFunitBean(FunitBean unitBean) {
        if (isViewAttach()) {
            mModel.setFunitBean(unitBean);
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
