package com.qiaosong.arraignmentmeeting.ui.activity.prisoner;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hst.fsp.FspEngine;
import com.qiaosong.arraignmentmeeting.AppApplication;
import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.bean.LoginTokenBean;
import com.qiaosong.arraignmentmeeting.event.EventConstant;
import com.qiaosong.arraignmentmeeting.event.TagValueEvent;
import com.qiaosong.arraignmentmeeting.event.bean.LoginResultEventBean;
import com.qiaosong.arraignmentmeeting.fsp.FspEngineManager;
import com.qiaosong.arraignmentmeeting.ui.activity.AdminPasswordActivity;
import com.qiaosong.arraignmentmeeting.ui.activity.SettingActivity;
import com.qiaosong.arraignmentmeeting.ui.activity.VideoWaitActivity;
import com.qiaosong.arraignmentmeeting.ui.adapter.CardIdInputAdapter;
import com.qiaosong.arraignmentmeeting.ui.adapter.KeyBoardAdapter;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainPrisonerContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.presenter.MainPrisonerPresenter;
import com.qiaosong.arraignmentmeeting.ui.viewholder.TitleViewHolder;
import com.qiaosong.baselibrary.utils.PermissionsUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainPrisonerActivity extends BaseActivity<MainPrisonerPresenter> implements MainPrisonerContacts.IMainPrisonerView {

    KeyBoardAdapter mKeyBoardAdapter;
    CardIdInputAdapter mCardIdInputAdapter;
    @BindView(R.id.rv_card_id)
    RecyclerView rvCardId;
    @BindView(R.id.rv_key_board)
    RecyclerView rvKeyBoard;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.v_setting)
    View vSetting;
    @BindView(R.id.rl_parent)
    RelativeLayout rlParent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_prisoner;
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
    public MainPrisonerPresenter bindPresenter() {
        return new MainPrisonerPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rlParent.addView(new TitleViewHolder(mContext, rlParent).getView());

        mKeyBoardAdapter = new KeyBoardAdapter(mContext, null);
        mKeyBoardAdapter.setOnKeyBoardClickListener(onKeyBoardClickListener);

        mCardIdInputAdapter = new CardIdInputAdapter(mContext, null);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCardId.setLayoutManager(linearLayoutManager);
        rvCardId.setAdapter(mCardIdInputAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        rvKeyBoard.setLayoutManager(gridLayoutManager);
        rvKeyBoard.setAdapter(mKeyBoardAdapter);
    }

    @OnClick({R.id.btn_sure, R.id.v_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_setting:
                startActivity(new Intent(mContext, AdminPasswordActivity.class));
                break;
            case R.id.btn_sure:
                mvpPresenter.getOrderCodeByCrimanalsCardId("312156555569874562");//mCardIdInputAdapter.getId());
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

    KeyBoardAdapter.OnKeyBoardClickListener onKeyBoardClickListener = new KeyBoardAdapter.OnKeyBoardClickListener() {
        @Override
        public void onClick(String data) {
            mCardIdInputAdapter.input(data);
        }

        @Override
        public void onDelete() {
            mCardIdInputAdapter.delete();
        }
    };

    /**
     * 获取登录token
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
}
