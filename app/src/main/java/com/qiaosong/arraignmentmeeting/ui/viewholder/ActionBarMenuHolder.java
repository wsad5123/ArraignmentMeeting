package com.qiaosong.arraignmentmeeting.ui.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;
import com.qiaosong.baselibrary.callback.OnActionBarMenuClickListener;


public class ActionBarMenuHolder extends BaseViewHolder {
    ImageView ivMenu;
    LinearLayout llMenu;
    TextView tvMenu;

    private int resId;
    private OnActionBarMenuClickListener onClickListener;

    public ActionBarMenuHolder(Context mContext) {
        super(mContext);
        ivMenu = holderView.findViewById(R.id.iv_menu);
        llMenu = holderView.findViewById(R.id.ll_menu);
        tvMenu = holderView.findViewById(R.id.tv_menu);

        llMenu.setOnClickListener(onViewClickListener);
    }

    @Override
    public int bindViewLayoutId() {
        return R.layout.view_action_bar_menu;
    }

    View.OnClickListener onViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.ll_menu) {
                if (resId != 0 && onClickListener != null)
                    onClickListener.onClick(resId);
            }
        }
    };

    /**
     * 设置监听
     *
     * @param onClickListener
     */
    public void setOnClickListener(OnActionBarMenuClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * 设置menu图标
     *
     * @param resId
     */
    public void initMenu(int resId) {
        if (resId != 0) {
            this.resId = resId;
            ivMenu.setImageResource(resId);
            ivMenu.setVisibility(View.VISIBLE);
            tvMenu.setVisibility(View.GONE);
        }
    }

    /**
     * 设置menu文本
     *
     * @param resId
     */
    public void initMenuText(int resId) {
        if (resId != 0) {
            this.resId = resId;
            ivMenu.setVisibility(View.GONE);
            tvMenu.setVisibility(View.VISIBLE);
            tvMenu.setText(resId);
        }
    }
}
