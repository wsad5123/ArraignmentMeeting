package com.qiaosong.arraignmentmeeting.ui.activity.family;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiaosong.arraignmentmeeting.AppApplication;
import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.event.EventConstant;
import com.qiaosong.arraignmentmeeting.event.TagValueEvent;
import com.qiaosong.arraignmentmeeting.event.bean.LoginResultEventBean;
import com.qiaosong.arraignmentmeeting.fsp.FspEngineManager;
import com.qiaosong.arraignmentmeeting.ui.activity.AdminPasswordActivity;
import com.qiaosong.arraignmentmeeting.ui.activity.VideoWaitActivity;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainFamilyContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.presenter.MainFamilyPresenter;
import com.qiaosong.arraignmentmeeting.ui.viewholder.TitleViewHolder;
import com.qiaosong.arraignmentmeeting.utils.InputManagerUtils;
import com.qiaosong.arraignmentmeeting.utils.LogUtils;
import com.qiaosong.baselibrary.utils.PermissionsUtils;
import com.qiaosong.baselibrary.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainFamilyActivity extends BaseActivity<MainFamilyPresenter> implements MainFamilyContacts.IMainFamilyView {

    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.rl_parent)
    RelativeLayout rlParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rlParent.addView(new TitleViewHolder(mContext, rlParent).getView());
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

    @OnClick({R.id.btn_sure, R.id.rl_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_setting:
                startActivity(new Intent(mContext, AdminPasswordActivity.class));
                break;
            case R.id.btn_sure:
                if (getCode().length() == 4) {
                    mvpPresenter.getToken(getCode());
                } else {
                    ToastUtils.show(mContext, "请输入4位会见码");
                }
                break;
        }
    }

    private String getCode() {
        String one = tvOne.getText().toString();
        String two = tvTwo.getText().toString();
        String three = tvThree.getText().toString();
        String four = tvFour.getText().toString();
        String code = "";
        if (!TextUtils.isEmpty(one)) {
            code = one;
        }
        if (!TextUtils.isEmpty(two)) {
            code += two;
        }
        if (!TextUtils.isEmpty(three)) {
            code += three;
        }
        if (!TextUtils.isEmpty(four)) {
            code += four;
        }
        return code;
    }

//    @OnTextChanged(R.id.et_code)
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        tvOne.setText("");
//        tvTwo.setText("");
//        tvThree.setText("");
//        tvFour.setText("");
////        vFour.setVisibility(View.GONE);
////        vThree.setVisibility(View.GONE);
////        vTwo.setVisibility(View.GONE);
////        vOne.setVisibility(View.GONE);
//        int length = s.length();
//        switch (length) {
//            case 4:
//                tvFour.setText(s.subSequence(3, 4));
//            case 3:
//                tvThree.setText(s.subSequence(2, 3));
//            case 2:
//                tvTwo.setText(s.subSequence(1, 2));
//            case 1:
//                tvOne.setText(s.subSequence(0, 1));
//                break;
//        }
////        switch (length) {
////            case 3:
////                vFour.setVisibility(View.VISIBLE);
////                break;
////            case 2:
////                vThree.setVisibility(View.VISIBLE);
////                break;
////            case 1:
////                vTwo.setVisibility(View.VISIBLE);
////                break;
////            case 0:
////                vOne.setVisibility(View.VISIBLE);
////                break;
////        }
//    }

//    /**
//     * 搜索按钮触发回调
//     *
//     * @param v
//     * @param actionId
//     * @param event
//     * @return
//     */
//    @OnEditorAction(R.id.et_code)
//    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//            if (etCode.getText().length() == 4) {
//                mvpPresenter.getToken(etCode.getText().toString());
//            } else {
//                ToastUtils.show(mContext, "请输入4位会见码");
//            }
//        }
//        return false;
//    }

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
                if (FspEngineManager.getInstance().init(AppApplication.getInstance(), bean.getServerAddr(), bean.getAppid(), bean.getAppsecretkey())) {
                    FspEngineManager.getInstance().login(bean.getUserUuid(), FspEngineManager.getInstance().getToken(bean.getUserUuid()));//bean.getToken());
                }
            }
        }, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_WIFI_STATE});
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:    //返回键
                LogUtils.d("back--->");
                return true;
            case KeyEvent.KEYCODE_ENTER:     //确定键enter
            case KeyEvent.KEYCODE_DPAD_CENTER:
//                btnSure.performClick();
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FspEngineManager.getInstance().onDestroy();
    }

}