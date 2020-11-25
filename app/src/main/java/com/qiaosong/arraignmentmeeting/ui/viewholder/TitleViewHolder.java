package com.qiaosong.arraignmentmeeting.ui.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;
import com.qiaosong.baselibrary.utils.DateUtils;

import butterknife.BindView;

public class TitleViewHolder extends BaseViewHolder {
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_date)
    TextView tvDate;

    public TitleViewHolder(Context mContext, ViewGroup parent) {
        super(mContext, parent);
        tvDate.setText(DateUtils.getFormatDateYMD() + "");
        tvTime.setText(DateUtils.getFormatDateHM() + "");
        tvTime.postDelayed(runnable, 1000);
    }

    @Override
    public int bindViewLayoutId() {
        return R.layout.view_title;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tvDate.setText(DateUtils.getFormatDateYMD() + "");
            tvTime.setText(DateUtils.getFormatDateHM() + "");
            tvTime.postDelayed(runnable, 1000);
        }
    };

}
