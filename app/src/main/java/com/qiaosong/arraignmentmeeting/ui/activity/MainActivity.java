package com.qiaosong.arraignmentmeeting.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.MainContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.presenter.MainPresenter;
import com.qiaosong.baselibrary.ui.base.BaseActivity;
import com.qiaosong.baselibrary.ui.base.IPresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContacts.IMainView {

    @BindView(R.id.tv_name)
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvName.setText("123");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter bindPresenter() {
        return new MainPresenter(this);
    }
}