package com.qiaosong.arraignmentmeeting.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiaosong.arraignmentmeeting.AppCacheManager;
import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.bean.CityBean;
import com.qiaosong.arraignmentmeeting.bean.FunitBean;
import com.qiaosong.arraignmentmeeting.bean.ProvinceBean;
import com.qiaosong.arraignmentmeeting.bean.PrisonBean;
import com.qiaosong.arraignmentmeeting.bean.RegulatorTypeBean;
import com.qiaosong.arraignmentmeeting.bean.api.ApiDeviceInfoBean;
import com.qiaosong.arraignmentmeeting.bean.api.ApiRegulatorBean;
import com.qiaosong.arraignmentmeeting.callback.OnCitySelectListener;
import com.qiaosong.arraignmentmeeting.callback.OnProvinceSelectListener;
import com.qiaosong.arraignmentmeeting.callback.OnRegulatorSelectListener;
import com.qiaosong.arraignmentmeeting.callback.OnRegulatorTypeSelectListener;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.SettingContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.presenter.SettingPresenter;
import com.qiaosong.arraignmentmeeting.ui.viewholder.TitleViewHolder;
import com.qiaosong.arraignmentmeeting.ui.widget.CitySelectDialog;
import com.qiaosong.arraignmentmeeting.ui.widget.ProvinceSelectDialog;
import com.qiaosong.arraignmentmeeting.ui.widget.RegulatorSelectDialog;
import com.qiaosong.arraignmentmeeting.ui.widget.RegulatorTypeSelectDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingContacts.ISettingView {
    @BindView(R.id.et_ip)
    EditText etIp;
    @BindView(R.id.et_port)
    EditText etPort;
    @BindView(R.id.tv_service_ip)
    TextView tvServiceIp;
    @BindView(R.id.tv_location_ip)
    TextView tvLocationIp;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_device_name)
    EditText etDeviceName;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.rl_parent)
    RelativeLayout rlParent;

    ProvinceSelectDialog mProvinceSelectDialog;
    CitySelectDialog mCitySelectDialog;
    RegulatorTypeSelectDialog mRegulatorTypeSelectDialog;
    RegulatorSelectDialog mRegulatorSelectDialog;

    @Override

    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public SettingPresenter bindPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    public boolean isShowActionBar() {
        return false;
    }

    @Override
    public boolean isShowStatusTitle() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rlParent.addView(new TitleViewHolder(mContext, rlParent).getView());
        if (AppCacheManager.getInstance().getHttpAddressBean() != null) {
            etIp.setText(AppCacheManager.getInstance().getHttpAddressBean().getIp());
            etPort.setText(AppCacheManager.getInstance().getHttpAddressBean().getPort());
        }
    }

    /**
     * 当获得所有省份
     *
     * @param data
     */
    @Override
    public void onGetAllProvince(List<ProvinceBean> data) {
        if (mProvinceSelectDialog == null) {
            mProvinceSelectDialog = new ProvinceSelectDialog(mContext);
            mProvinceSelectDialog.setOnProvinceSelectListener(onProvinceSelectListener);
        }
        mProvinceSelectDialog.initData(data);
        mProvinceSelectDialog.show();
    }

    /**
     * 当获得所有城市
     */
    @Override
    public void onGetAllCity(List<CityBean> data) {
        if (data != null && !data.isEmpty()) {
            if (mCitySelectDialog == null) {
                mCitySelectDialog = new CitySelectDialog(mContext);
                mCitySelectDialog.setOnCitySelectListener(onCitySelectListener);
            }
            mCitySelectDialog.initData(data);
            mCitySelectDialog.show();
        }
    }

    /**
     * 获得所有监管机构类型
     *
     * @param data
     */
    @Override
    public void onGetAllRegulatorType(List<RegulatorTypeBean> data) {
        if (mRegulatorTypeSelectDialog == null) {
            mRegulatorTypeSelectDialog = new RegulatorTypeSelectDialog(mContext);
            mRegulatorTypeSelectDialog.setOnRegulatorTypeSelectListener(onRegulatorTypeSelectListener);
        }
        mRegulatorTypeSelectDialog.initData(data);
        mRegulatorTypeSelectDialog.show();
    }

    @Override
    public void onGetAllRegulator(ApiRegulatorBean data) {
        if (mRegulatorSelectDialog == null) {
            mRegulatorSelectDialog = new RegulatorSelectDialog(mContext);
            mRegulatorSelectDialog.setOnRegulatorSelectListener(onRegulatorSelectListener);
        }
        mRegulatorSelectDialog.initData(data);
        mRegulatorSelectDialog.show();
    }

    /**
     * 当获得基础信息
     *
     * @param deviceInfo
     */
    @Override
    public void onDeviceInfoData(ApiDeviceInfoBean deviceInfo) {
        etDeviceName.setText(deviceInfo.getDeviceinfo().getDevicename());
        tvProvince.setText(deviceInfo.getAddress().getP_name());
        tvCity.setText(deviceInfo.getAddress().getC_name());
        tvType.setText(deviceInfo.getTypename().getPtypename());
        if (deviceInfo.getPrisonBean() != null) {
            tvName.setText(deviceInfo.getPrisonBean().getPrisonName());
        } else if (deviceInfo.getUnitinfo() != null) {
            tvName.setText(deviceInfo.getUnitinfo().getFunitName());
        }

    }

    @OnClick({R.id.tv_province, R.id.tv_city, R.id.tv_type, R.id.tv_name, R.id.tv_service_ip, R.id.tv_location_ip, R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_service_ip:
                mvpPresenter.getDeviceInfo(etIp.getText().toString(), etPort.getText().toString());
                break;
            case R.id.tv_location_ip:
                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                startActivity(intent);
                break;
            case R.id.tv_province:
                mvpPresenter.getAllProvince();
                break;
            case R.id.tv_city:
                mvpPresenter.getCityByCode();
                break;
            case R.id.tv_type:
                mvpPresenter.getAllRegulatorType();
                break;
            case R.id.tv_name:
                mvpPresenter.getAllRegulator();
                break;
            case R.id.btn_save:
                mvpPresenter.postDeviceInfoSave(etIp.getText().toString(), etPort.getText().toString(), etDeviceName.getText().toString());
                break;
        }
    }

    /**
     * 当省份选择后
     */
    OnProvinceSelectListener onProvinceSelectListener = new OnProvinceSelectListener() {
        @Override
        public void onSelect(ProvinceBean bean) {
            tvProvince.setText(bean.getName());
            mvpPresenter.setProvinceBean(bean);
            tvCity.setText("");
            tvName.setText("");
            mvpPresenter.removeCityData();
            mvpPresenter.removeRegulatorData();
            mProvinceSelectDialog.dismiss();
        }
    };

    /**
     * 城市选择
     */
    OnCitySelectListener onCitySelectListener = new OnCitySelectListener() {
        @Override
        public void onSelect(CityBean bean) {
            tvCity.setText(bean.getName());
            mvpPresenter.setCityBean(bean);
            tvName.setText("");
            mvpPresenter.removeRegulatorData();
            mCitySelectDialog.dismiss();
        }
    };

    /**
     * 监管机构类型
     */
    OnRegulatorTypeSelectListener onRegulatorTypeSelectListener = new OnRegulatorTypeSelectListener() {
        @Override
        public void onSelect(RegulatorTypeBean bean) {
            tvType.setText(bean.getPtypename());
            mvpPresenter.setRegulatorTypeBean(bean);
            tvName.setText("");
            mvpPresenter.removeRegulatorData();
            mRegulatorTypeSelectDialog.dismiss();
        }
    };

    /**
     * 监管机构类型
     */
    OnRegulatorSelectListener onRegulatorSelectListener = new OnRegulatorSelectListener() {
        @Override
        public void onSelect(PrisonBean prisonBean, FunitBean funitBean) {
            if (prisonBean != null) {
                tvName.setText(prisonBean.getPrisonName());
                mvpPresenter.setPrisonBean(prisonBean);
            }
            if (funitBean != null) {
                tvName.setText(funitBean.getFunitName());
                mvpPresenter.setFunitBean(funitBean);
            }

            mRegulatorSelectDialog.dismiss();
        }
    };
}
