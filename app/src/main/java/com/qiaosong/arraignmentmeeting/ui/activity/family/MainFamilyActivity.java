package com.qiaosong.arraignmentmeeting.ui.activity.family;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hst.fsp.FspEngine;
import com.qiaosong.arraignmentmeeting.AppApplication;
import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.event.EventConstant;
import com.qiaosong.arraignmentmeeting.event.TagValueEvent;
import com.qiaosong.arraignmentmeeting.event.bean.LoginResultEventBean;
import com.qiaosong.arraignmentmeeting.fsp.FspEngineManager;
import com.qiaosong.arraignmentmeeting.ui.activity.SettingActivity;
import com.qiaosong.arraignmentmeeting.ui.activity.VideoWaitActivity;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainFamilyContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.presenter.MainFamilyPresenter;
import com.qiaosong.arraignmentmeeting.ui.viewholder.TitleViewHolder;
import com.qiaosong.baselibrary.utils.PermissionsUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainFamilyActivity extends BaseActivity<MainFamilyPresenter> implements MainFamilyContacts.IMainFamilyView {
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.v_one)
    View vOne;
    @BindView(R.id.rl_one)
    RelativeLayout rlOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.v_two)
    View vTwo;
    @BindView(R.id.rl_two)
    RelativeLayout rlTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.v_three)
    View vThree;
    @BindView(R.id.rl_three)
    RelativeLayout rlThree;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.v_four)
    View vFour;
    @BindView(R.id.rl_four)
    RelativeLayout rlFour;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.v_setting)
    View vSetting;
    @BindView(R.id.rl_parent)
    RelativeLayout rlParent;

    StringBuffer stringBuffer = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rlParent.addView(new TitleViewHolder(mContext, rlParent).getView());
        initCode();
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
    public int getLayoutId() {
        return R.layout.activity_main_family;
    }

    @Override
    public MainFamilyPresenter bindPresenter() {
        return new MainFamilyPresenter(this);
    }

    @OnClick({R.id.btn_sure, R.id.v_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_setting:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.btn_sure:
                mvpPresenter.getToken("9467");
                break;
        }
    }

    @Subscribe
    public void onEvent(TagValueEvent event) {
        if (EventConstant.LOGIN_RESULT_EVENT.equals(event.getTag())) {
            if (event.getValue() instanceof LoginResultEventBean) {
                LoginResultEventBean bean = (LoginResultEventBean) event.getValue();
                if (bean.isOk()) {
                    startActivity(new Intent(mContext, VideoWaitActivity.class));
                }
            }
        }
    }

    /**
     * 获得token数据
     *
     * @param bean
     */
    @Override
    public void onLoginToken(LoginTokenBean bean) {
        PermissionsUtils.requestPermissions((BaseActivity) mContext, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                if (FspEngineManager.getInstance().init(AppApplication.getInstance()) == FspEngine.ERR_OK) {
                    FspEngineManager.getInstance().login(bean.getUserUuid(), FspEngineManager.getInstance().getToken(bean.getUserUuid()));//bean.getToken());
                }
            }
        }, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_WIFI_STATE});
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:     //确定键enter
            case KeyEvent.KEYCODE_DPAD_CENTER:
                btnSure.performClick();
                break;
            case KeyEvent.KEYCODE_0:
                inputNum("0");
                break;
            case KeyEvent.KEYCODE_1:
                inputNum("1");
                break;
            case KeyEvent.KEYCODE_2:
                inputNum("2");
                break;
            case KeyEvent.KEYCODE_3:
                inputNum("3");
                break;
            case KeyEvent.KEYCODE_4:
                inputNum("4");
                break;
            case KeyEvent.KEYCODE_5:
                inputNum("5");
                break;
            case KeyEvent.KEYCODE_6:
                inputNum("6");
                break;
            case KeyEvent.KEYCODE_7:
                inputNum("7");
                break;
            case KeyEvent.KEYCODE_8:
                inputNum("8");
                break;
            case KeyEvent.KEYCODE_9:
                inputNum("9");
                break;
            case KeyEvent.KEYCODE_DEL:    //删除
                removeNum();
                break;
            case KeyEvent.KEYCODE_BACK:    //返回键
                break;   //这里由于break会退出，所以我们自己要处理掉 不返回上一层
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);

    }

    private void initCode() {
        vFour.setVisibility(View.GONE);
        vThree.setVisibility(View.GONE);
        vTwo.setVisibility(View.GONE);
        vOne.setVisibility(View.GONE);
        if (stringBuffer.length() == 3) {
            vFour.setVisibility(View.VISIBLE);
        } else if (stringBuffer.length() == 2) {
            vThree.setVisibility(View.VISIBLE);
        } else if (stringBuffer.length() == 1) {
            vTwo.setVisibility(View.VISIBLE);
        } else if (stringBuffer.length() == 0) {
            vOne.setVisibility(View.VISIBLE);
        }
        tvOne.setText("");
        tvTwo.setText("");
        tvThree.setText("");
        tvFour.setText("");
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (i == 0) {
                tvOne.setText(stringBuffer.toString().substring(0, 1));
            } else if (i == 1) {
                tvTwo.setText(stringBuffer.toString().substring(1, 2));
            } else if (i == 2) {
                tvThree.setText(stringBuffer.toString().substring(2, 3));
            } else if (i == 3) {
                tvFour.setText(stringBuffer.toString().substring(3, 4));
            }
        }
    }


    public void inputNum(String num) {
        if (!TextUtils.isEmpty(num) && stringBuffer.length() < 4) {
            stringBuffer.append(num);
        }
        initCode();
    }

    public void removeNum() {
        if (stringBuffer.length() > 1) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        initCode();
    }

}