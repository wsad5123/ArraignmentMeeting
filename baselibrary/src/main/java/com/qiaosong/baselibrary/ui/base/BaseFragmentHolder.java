package com.qiaosong.baselibrary.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.qiaosong.baselibrary.R;
import com.qiaosong.baselibrary.callback.OnActionBarMenuClickListener;
import com.qiaosong.baselibrary.ui.viewholder.ActionBarMenuHolder;
import com.qiaosong.baselibrary.utils.PxUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BaseFragmentHolder extends BaseViewHolder {
    LinearLayout llBaseContent;
    View vPadding;
    TextView tvBaseTitle;
    LinearLayout llBaseTitle;
    View vImmersionPadding;
    TextView tvImmersionTitle;
    LinearLayout llImmersionTitle;
    LinearLayout llMenu;
    LinearLayout llBaseMenu;
    View vLoadPadding;
    LinearLayout llBaseLoading;
    View vErrorPadding;
    Button btnBaseRetry;
    LinearLayout llBaseError;
    private boolean isImmersion;//是否未沉浸式标题
    private View.OnClickListener onClickListener;//刷新按钮点击效果

    public BaseFragmentHolder(Context mContext, boolean isImmersion) {
        super(mContext);
        this.isImmersion = isImmersion;

        llBaseContent = holderView.findViewById(R.id.ll_base_content);
        vPadding = holderView.findViewById(R.id.v_padding);
        tvBaseTitle = holderView.findViewById(R.id.tv_base_title);
        llBaseTitle = holderView.findViewById(R.id.ll_base_title);

        vImmersionPadding = holderView.findViewById(R.id.v_immersion_padding);
        tvImmersionTitle = holderView.findViewById(R.id.tv_immersion_title);
        llImmersionTitle = holderView.findViewById(R.id.ll_immersion_title);
        llMenu = holderView.findViewById(R.id.ll_menu);
        llBaseMenu = holderView.findViewById(R.id.ll_base_menu);

        vLoadPadding = holderView.findViewById(R.id.v_load_padding);
        llBaseLoading = holderView.findViewById(R.id.ll_base_loading);

        vErrorPadding = holderView.findViewById(R.id.v_error_padding);
        btnBaseRetry = holderView.findViewById(R.id.btn_base_retry);
        llBaseError = holderView.findViewById(R.id.ll_base_error);

        btnBaseRetry.setOnClickListener(onViewClickListener);
    }


    @Override
    public int bindViewLayoutId() {
        return R.layout.base_fragment_layout;
    }


    View.OnClickListener onViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.btn_base_retry) {
                if (onClickListener != null)
                    onClickListener.onClick(view);
            }
        }
    };

    /**
     * 设置标题和状态栏
     *
     * @param isShowStatus
     * @param isShowActionBar
     */
    public void initActionBar(boolean isShowStatus, boolean isShowActionBar) {
        if (isImmersion) {
            //设置沉浸式头部高度
            RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams) llImmersionTitle.getLayoutParams();
            ViewGroup.LayoutParams params = vImmersionPadding.getLayoutParams();
            params.height = isShowStatus ? PxUtils.getStatusTitleHeight(mContext) : 0;
            p.height = isShowActionBar ? params.height + mContext.getResources().getDimensionPixelSize(R.dimen.title_height) : params.height;
            llImmersionTitle.setLayoutParams(p);
            llImmersionTitle.setVisibility(View.VISIBLE);
            llBaseTitle.setVisibility(View.GONE);
            //设置沉浸式错误界面和loading界面头部padding
            LinearLayout.LayoutParams errorPaddingLayoutParams = (LinearLayout.LayoutParams) vErrorPadding.getLayoutParams();
            errorPaddingLayoutParams.height = PxUtils.getStatusTitleHeight(mContext) + mContext.getResources().getDimensionPixelSize(R.dimen.title_height);
            vErrorPadding.setLayoutParams(errorPaddingLayoutParams);
            LinearLayout.LayoutParams loadPaddingLayoutParams = (LinearLayout.LayoutParams) vLoadPadding.getLayoutParams();
            loadPaddingLayoutParams.height = PxUtils.getStatusTitleHeight(mContext) + mContext.getResources().getDimensionPixelSize(R.dimen.title_height);
            vLoadPadding.setLayoutParams(errorPaddingLayoutParams);
        } else {
            //设置头部高度
            RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams) llBaseTitle.getLayoutParams();
            ViewGroup.LayoutParams params = vPadding.getLayoutParams();
            params.height = isShowStatus ? PxUtils.getStatusTitleHeight(mContext) : 0;
            p.height = isShowActionBar ? params.height + mContext.getResources().getDimensionPixelSize(R.dimen.title_height) : params.height;
            llBaseTitle.setLayoutParams(p);
            llBaseTitle.setVisibility(View.VISIBLE);
            llImmersionTitle.setVisibility(View.GONE);
            //设置错误界面和loading界面头部padding
            LinearLayout.LayoutParams errorPaddingLayoutParams = (LinearLayout.LayoutParams) vErrorPadding.getLayoutParams();
            errorPaddingLayoutParams.height = PxUtils.getStatusTitleHeight(mContext) + mContext.getResources().getDimensionPixelSize(R.dimen.title_height) - p.height;
            vErrorPadding.setLayoutParams(errorPaddingLayoutParams);
            LinearLayout.LayoutParams loadPaddingLayoutParams = (LinearLayout.LayoutParams) vLoadPadding.getLayoutParams();
            loadPaddingLayoutParams.height = PxUtils.getStatusTitleHeight(mContext) + mContext.getResources().getDimensionPixelSize(R.dimen.title_height) - p.height;
            vLoadPadding.setLayoutParams(errorPaddingLayoutParams);
        }
    }

    /**
     * 设置Fragment内容
     *
     * @param layoutId
     */
    public Unbinder initFragmentContent(Fragment fragment, int layoutId) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View view = inflater.inflate(layoutId, null);
        llBaseContent.removeAllViews();
        llBaseContent.addView(view, param);
        return ButterKnife.bind(fragment, llBaseContent);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (title != null) {
            if (isImmersion)
                tvImmersionTitle.setText(title);
            else
                tvBaseTitle.setText(title);
        }

    }

    /**
     * 设置标题字颜色
     *
     * @param resId
     */
    public void setTitleColor(int resId) {
        if (resId != 0)
            if (isImmersion)
                tvImmersionTitle.setTextColor(mContext.getResources().getColorStateList(resId));
            else
                tvBaseTitle.setTextColor(mContext.getResources().getColorStateList(resId));
    }

    /**
     * 设置按钮
     *
     * @param resIds        图标资源
     * @param clickListener 按钮监听
     */
    public void setActionBarMenu(int[] resIds, OnActionBarMenuClickListener clickListener) {
        LinearLayout linearLayout;
        if (isImmersion) {
            linearLayout = llMenu;
        } else {
            linearLayout = llBaseMenu;
        }
        linearLayout.removeAllViews();
        if (resIds != null)
            for (int res : resIds) {
                ActionBarMenuHolder menuHolder = new ActionBarMenuHolder(mContext);
                menuHolder.initMenu(res);
                menuHolder.setOnClickListener(clickListener);
                linearLayout.addView(menuHolder.getView());
            }

    }

    /**
     * 设置标题背景
     *
     * @param resource
     */
    public void setActionBarBackgroundResource(int resource) {
        llBaseTitle.setBackgroundResource(resource);
    }

    /**
     * 设置刷新点击效果
     *
     * @param onClickListener
     */
    public void setOnRefreshClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * 是否显示加载中
     *
     * @param isVisible
     */
    public void setLoadingVisible(boolean isVisible) {
        if (isVisible) {
            if (llBaseLoading.getVisibility() == View.GONE) {
                llBaseContent.setVisibility(View.GONE);
                llBaseLoading.setVisibility(View.VISIBLE);
                llBaseError.setVisibility(View.GONE);
            }
        } else {
            if (llBaseLoading.getVisibility() == View.VISIBLE) {
                llBaseContent.setVisibility(View.VISIBLE);
                llBaseLoading.setVisibility(View.GONE);
                llBaseError.setVisibility(View.GONE);
            }
        }

    }


    /**
     * 是否显示错误信息
     *
     * @param isVisible
     */
    public void setErrorVisible(boolean isVisible) {
        if (isVisible) {
            if (llBaseError.getVisibility() == View.GONE) {
                llBaseContent.setVisibility(View.GONE);
                llBaseError.setVisibility(View.VISIBLE);
                llBaseLoading.setVisibility(View.GONE);
            }
        } else {
            if (llBaseError.getVisibility() == View.VISIBLE) {
                llBaseContent.setVisibility(View.VISIBLE);
                llBaseLoading.setVisibility(View.GONE);
                llBaseError.setVisibility(View.GONE);
            }
        }
    }

}
