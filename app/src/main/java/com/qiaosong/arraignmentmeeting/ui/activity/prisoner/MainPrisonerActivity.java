package com.qiaosong.arraignmentmeeting.ui.activity.prisoner;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hst.fsp.FspEngine;
import com.qiaosong.arraignmentmeeting.AppApplication;
import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.fsp.FspEngineManager;
import com.qiaosong.arraignmentmeeting.ui.activity.family.VideoFamilyActivity;
import com.qiaosong.arraignmentmeeting.ui.adapter.CardIdInputAdapter;
import com.qiaosong.arraignmentmeeting.ui.adapter.KeyBoardAdapter;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainPrisonerContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.presenter.MainPrisonerPresenter;
import com.qiaosong.baselibrary.utils.PermissionsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainPrisonerActivity extends BaseActivity<MainPrisonerPresenter> implements MainPrisonerContacts.IMainPrisonerView {
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.rv_card_id)
    RecyclerView rvCardId;
    @BindView(R.id.rv_key_board)
    RecyclerView rvKeyBoard;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.v_setting)
    View vSetting;

    KeyBoardAdapter mKeyBoardAdapter;
    CardIdInputAdapter mCardIdInputAdapter;

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

    @OnClick(R.id.btn_sure)
    public void onClick(View view) {
        PermissionsUtils.requestPermissions((BaseActivity) mContext, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                FspEngineManager.getInstance().init(AppApplication.getInstance());
                if (FspEngineManager.getInstance().login("66667") == FspEngine.ERR_OK) {
                    startActivity(new Intent(mContext, VideoPrisonerActivity.class));
                }
            }
        }, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_WIFI_STATE});
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
}
