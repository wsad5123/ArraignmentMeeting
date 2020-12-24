package com.qiaosong.arraignmentmeeting.ui.mvp.model;

import android.content.Context;

import com.qiaosong.arraignmentmeeting.AppCacheManager;
import com.qiaosong.arraignmentmeeting.bean.CityBean;
import com.qiaosong.arraignmentmeeting.bean.DeviceInfoBean;
import com.qiaosong.arraignmentmeeting.bean.FunitBean;
import com.qiaosong.arraignmentmeeting.bean.ProvinceBean;
import com.qiaosong.arraignmentmeeting.bean.PrisonBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorTypeBean;
import com.qiaosong.arraignmentmeeting.bean.api.ApiDeviceInfoBean;
import com.qiaosong.arraignmentmeeting.bean.api.ApiRegulatorBean;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.event.EventConstant;
import com.qiaosong.arraignmentmeeting.event.TagValueEvent;
import com.qiaosong.arraignmentmeeting.http.ApiObserver;
import com.qiaosong.arraignmentmeeting.http.RetrofitHttpParams;
import com.qiaosong.arraignmentmeeting.http.subscribe.AppSubscribe;
import com.qiaosong.arraignmentmeeting.ui.base.BaseModel;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.SettingContacts;
import com.qiaosong.arraignmentmeeting.utils.DeviceUtils;
import com.qiaosong.arraignmentmeeting.utils.PhoneUtils;
import com.qiaosong.baselibrary.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.MultipartBody;

public class SettingModel extends BaseModel implements SettingContacts.ISettingModel {

    private List<ProvinceBean> mProvinceList;
    private List<CityBean> mCityList;
    private List<RegulatorTypeBean> mRegulatorTypeList;
    private ApiRegulatorBean mRegulatorBean;
    private ProvinceBean mProvinceBean;
    private CityBean mCityBean;
    private RegulatorTypeBean mRegulatorTypeBean;
    private PrisonBean mPrisonBean;
    private FunitBean mFunitBean;

    public SettingModel(Context mContext) {
        super(mContext);
    }

    /**
     * 获取已获得的省份信息
     *
     * @return
     */
    @Override
    public List<ProvinceBean> getAllProvince() {
        return mProvinceList;
    }

    @Override
    public List<CityBean> getAllCity() {
        return mCityList;
    }

    @Override
    public List<RegulatorTypeBean> getAllRegulatorType() {
        return mRegulatorTypeList;
    }

    @Override
    public ApiRegulatorBean getAllRegulator() {
        return mRegulatorBean;
    }

    /**
     * 获取所有省份信息
     */
    @Override
    public void httpGetAllProvince(MvpDataCallBack<List<ProvinceBean>> callBack) {
        MultipartBody.Builder builder = new RetrofitHttpParams(mContext).getRequestMultipartBody();
        new AppSubscribe(mContext).requestAllProvince(builder.build(), new ApiObserver<List<ProvinceBean>>(mContext, true) {
            @Override
            public void onSuccess(List<ProvinceBean> data) {
                if (data != null) {
                    mProvinceList = data;
                    callBack.onData(mProvinceList);
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }

    /**
     * 获取城市code
     *
     * @param callBack
     */
    @Override
    public void httpGetCityByCode(MvpDataCallBack<List<CityBean>> callBack) {
        if (mProvinceBean == null)
            return;
        MultipartBody.Builder builder = new RetrofitHttpParams(mContext).getRequestMultipartBody();
        builder.addFormDataPart("p_code", mProvinceBean.getCode());
        new AppSubscribe(mContext).requestCityByProvinceCode(builder.build(), new ApiObserver<List<CityBean>>(mContext, true) {
            @Override
            public void onSuccess(List<CityBean> data) {
                if (data != null) {
                    mCityList = data;
                    callBack.onData(mCityList);
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }

    /**
     * 获取所有监管机构类型
     *
     * @param callBack
     */
    @Override
    public void httpGetRegulatorsType(MvpDataCallBack<List<RegulatorTypeBean>> callBack) {
        MultipartBody.Builder builder = new RetrofitHttpParams(mContext).getRequestMultipartBody();
        new AppSubscribe(mContext).requestAllRegulatorType(builder.build(), new ApiObserver<List<RegulatorTypeBean>>(mContext, true) {
            @Override
            public void onSuccess(List<RegulatorTypeBean> data) {
                if (data != null) {
                    mRegulatorTypeList = data;
                    callBack.onData(mRegulatorTypeList);
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }

    @Override
    public void httpGetRegulators(MvpDataCallBack<ApiRegulatorBean> callBack) {
        if (mProvinceBean == null || mCityBean == null || mRegulatorTypeBean == null)
            return;
        MultipartBody.Builder builder = new RetrofitHttpParams(mContext).getRequestMultipartBody();
        builder.addFormDataPart("typeuuid", mRegulatorTypeBean.getPtypeuuid());
        builder.addFormDataPart("pcode", mProvinceBean.getCode());
        builder.addFormDataPart("cccode", mCityBean.getCode());
        new AppSubscribe(mContext).requestAllRegulator(builder.build(), new ApiObserver<ApiRegulatorBean>(mContext, true) {
            @Override
            public void onSuccess(ApiRegulatorBean data) {
                if (data != null) {
                    mRegulatorBean = data;
                    callBack.onData(mRegulatorBean);
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }

    /**
     * 保存信息
     *
     * @param serviceIp
     * @param servicePort
     * @param deviceName
     * @param callBack
     */
    @Override
    public void httpPostDeviceInfoSave(String serviceIp, String servicePort, String deviceName, MvpDataCallBack<Boolean> callBack) {
        if (mProvinceBean == null || mCityBean == null || mRegulatorTypeBean == null || (mPrisonBean == null && mFunitBean == null)) {
            ToastUtils.show(mContext, "请选择单位名称");
            return;
        }
        MultipartBody.Builder builder = new RetrofitHttpParams(mContext).getRequestMultipartBody();
        builder.addFormDataPart("deviceIp", PhoneUtils.getIPAddress(mContext));
        builder.addFormDataPart("deviceMac", PhoneUtils.getAdresseMAC(mContext));
        builder.addFormDataPart("deviceimei", DeviceUtils.getDeviceId(mContext));
        builder.addFormDataPart("deviceType", "");
        builder.addFormDataPart("ptypeuuid", mRegulatorTypeBean.getPtypeuuid());
        builder.addFormDataPart("deviceName", deviceName);
        builder.addFormDataPart("subFromUUID", mPrisonBean == null ? mFunitBean.getFunitUUID() : mPrisonBean.getPrisonUUID());
        builder.addFormDataPart("serverIP", serviceIp);
        builder.addFormDataPart("serverPort", servicePort);
        new AppSubscribe(mContext).requestDeviceInfoSave(builder.build(), new ApiObserver<DeviceInfoBean>(mContext, true) {
            @Override
            public void onSuccess(DeviceInfoBean data) {
                if (data != null) {
                    AppCacheManager.getInstance().setProvinceName(mProvinceBean.getName());
                    AppCacheManager.getInstance().setDeviceUuid(data.getDevicuuid());
                    EventBus.getDefault().post(new TagValueEvent(EventConstant.TITLE_REFRESH, null));
                    callBack.onData(true);
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }

    /**
     * 获取设备信息
     */
    @Override
    public void httpGetDeviceInfo(MvpDataCallBack<ApiDeviceInfoBean> callBack) {
        MultipartBody.Builder builder = new RetrofitHttpParams(mContext).getRequestMultipartBody();
        builder.addFormDataPart("deviceimei", DeviceUtils.getDeviceId(mContext));
        new AppSubscribe(mContext).requestDeviceInfo(builder.build(), new ApiObserver<ApiDeviceInfoBean>(mContext, true) {
            @Override
            public void onSuccess(ApiDeviceInfoBean data) {
                if (data != null && data.getAddress() != null && data.getDeviceinfo() != null && (data.getPrisonBean() != null || data.getUnitinfo() != null) && data.getTypename() != null) {
                    AppCacheManager.getInstance().setDeviceUuid(data.getDeviceinfo().getDevicuuid());
                    AppCacheManager.getInstance().setProvinceName(data.getAddress().getP_name());
                    EventBus.getDefault().post(new TagValueEvent(EventConstant.TITLE_REFRESH, null));
                    mProvinceBean = new ProvinceBean(data.getAddress().getP_code(), data.getAddress().getP_name());
                    mCityBean = new CityBean(data.getAddress().getC_code(), data.getAddress().getC_name());
                    mRegulatorTypeBean = data.getTypename();
                    mPrisonBean = data.getPrisonBean();
                    mFunitBean = data.getUnitinfo();
                    callBack.onData(data);
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }

    /**
     * 设置当前选中省份
     *
     * @param provinceBean
     */
    @Override
    public void setProvinceBean(ProvinceBean provinceBean) {
        this.mProvinceBean = provinceBean;
    }

    @Override
    public void setCityBean(CityBean cityBean) {
        this.mCityBean = cityBean;
    }

    @Override
    public void setRegulatorTypeBean(RegulatorTypeBean regulatorTypeBean) {
        this.mRegulatorTypeBean = regulatorTypeBean;
    }

    @Override
    public void setPrisonBean(PrisonBean prisonBean) {
        this.mPrisonBean = prisonBean;
    }

    @Override
    public void setFunitBean(FunitBean funitBean) {
        this.mFunitBean = funitBean;
    }

    @Override
    public void removeCityData() {
        mCityList = null;
        mCityBean = null;
    }

    @Override
    public void removeRegulatorData() {
        mFunitBean = null;
        mPrisonBean = null;
        mRegulatorBean = null;
    }


}
