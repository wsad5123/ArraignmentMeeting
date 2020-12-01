package com.qiaosong.arraignmentmeeting.ui.viewholder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tv_state)
    TextView tvState;

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

    /**
     * 加载状态
     *
     * @param state    0 等待中，1 会见中， 2 会见结束
     * @param restTime
     */
    public void initVideoView(int state, String restTime) {
        llTitle.setVisibility(View.GONE);
        tvState.setVisibility(View.VISIBLE);
        if (state == 0) {
            tvState.setText("等待会见");
            tvState.setTextColor(mContext.getResources().getColor(R.color.white));
        } else if (state == 1) {
            String time = "0分钟";
            if (!TextUtils.isEmpty(restTime)) {
                int rest = Integer.parseInt(restTime);
                if (rest >= 60) {
                    time = (rest / 60) + "分钟";
                } else {
                    time = rest + "秒";
                }
            }
            tvState.setText("剩余时间  " + time);
            tvState.setTextColor(mContext.getResources().getColor(R.color.red));
        } else if (state == 2) {
            tvState.setText("会见已结束，即将关闭聊天室");
            tvState.setTextColor(mContext.getResources().getColor(R.color.red));
        }

    }
}
