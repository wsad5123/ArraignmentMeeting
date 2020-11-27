package com.qiaosong.arraignmentmeeting.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.ui.adapter.KeyBoardAdapter;
import com.qiaosong.arraignmentmeeting.ui.base.BaseActivity;
import com.qiaosong.arraignmentmeeting.ui.base.IPresenter;
import com.qiaosong.arraignmentmeeting.ui.viewholder.TitleViewHolder;
import com.qiaosong.baselibrary.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class AdminPasswordActivity extends BaseActivity {
    @BindView(R.id.rv_key_board)
    RecyclerView rvKeyBoard;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.rl_parent)
    RelativeLayout rlParent;

    KeyBoardAdapter mKeyBoardAdapter;

    StringBuffer password = new StringBuffer();


    @Override
    public int getLayoutId() {
        return R.layout.activity_admin_password;
    }

    @Override
    public IPresenter bindPresenter() {
        return null;
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

        mKeyBoardAdapter = new KeyBoardAdapter(mContext, null);
        mKeyBoardAdapter.setOnKeyBoardClickListener(onKeyBoardClickListener);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        rvKeyBoard.setLayoutManager(gridLayoutManager);
        rvKeyBoard.setAdapter(mKeyBoardAdapter);
    }

    @OnClick(R.id.btn_sure)
    public void onClick(View view) {
        if ("20190311".equals(password.toString())) {
            startActivity(new Intent(mContext, SettingActivity.class));
            finish();
        } else {
            password.delete(0, password.length());
            ToastUtils.show(mContext, "密码错误，请重新输入");
        }
    }

    KeyBoardAdapter.OnKeyBoardClickListener onKeyBoardClickListener = new KeyBoardAdapter.OnKeyBoardClickListener() {
        @Override
        public void onClick(String data) {
            password.append(data);
        }

        @Override
        public void onDelete() {
            password.delete(0, password.length());
        }
    };

}
