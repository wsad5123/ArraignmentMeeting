package com.qiaosong.arraignmentmeeting.ui.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.baselibrary.callback.OnActionBarMenuClickListener;
import com.qiaosong.arraignmentmeeting.ui.viewholder.ActionBarMenuHolder;
import com.qiaosong.baselibrary.utils.ActionBarUtils;
import com.qiaosong.baselibrary.utils.PxUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseActivityHolder extends BaseViewHolder {
    @BindView(R.id.v_padding)
    public View vPadding;
    @BindView(R.id.ll_back)
    public LinearLayout llBack;
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.ll_base_title)
    public LinearLayout llBaseTitle;
    @BindView(R.id.ll_base_content)
    public LinearLayout llBaseContent;
    @BindView(R.id.ll_base_menu)
    public LinearLayout llBaseMenu;
    @BindView(R.id.v_immersion_padding)
    public View vImmersionPadding;
    @BindView(R.id.tv_immersion_title)
    public TextView tvImmersionTitle;
    @BindView(R.id.ll_menu)
    public LinearLayout llMenu;
    @BindView(R.id.ll_immersion_title)
    public LinearLayout llImmersionTitle;
    @BindView(R.id.iv_back)
    public ImageView ivBack;
    @BindView(R.id.iv_immersion_back)
    public ImageView ivImmersionBack;
    @BindView(R.id.ll_immersion_back)
    public LinearLayout llImmersionBack;
    @BindView(R.id.v_load_padding)
    public View vLoadPadding;
    @BindView(R.id.ll_base_loading)
    public LinearLayout llBaseLoading;
    @BindView(R.id.v_error_padding)
    public View vErrorPadding;
    @BindView(R.id.btn_base_retry)
    public Button btnBaseRetry;
    @BindView(R.id.ll_base_error)
    public LinearLayout llBaseError;

    private boolean isImmersion;//是否未沉浸式标题
    private View.OnClickListener onClickListener;//刷新按钮点击效果
    private View.OnClickListener onBackClickListener;//返回按钮点击效果

    public BaseActivityHolder(BaseActivity mContext, boolean isImmersion) {
        super(mContext);
        this.isImmersion = isImmersion;
    }

    @Override
    public int bindViewLayoutId() {
        return R.layout.base_activity_layout;
    }

    @OnClick({R.id.ll_back, R.id.ll_immersion_back, R.id.btn_base_retry})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
            case R.id.ll_immersion_back:
                if (onBackClickListener != null) {
                    onBackClickListener.onClick(view);
                } else {
                    ((BaseActivity) mContext).finish();
                }
                break;
            case R.id.btn_base_retry:
                if (onClickListener != null)
                    onClickListener.onClick(view);
                break;
        }
    }

    /**
     * 设置标题和状态栏
     *
     * @param isShowPrimordialStatus 是否显示原生状态栏
     * @param isShowStatus           是否显示状态栏
     * @param isShowActionBar        是否显示actionbar
     */
    public void initActionBar(boolean isShowPrimordialStatus, boolean isShowStatus, boolean isShowActionBar) {
        ActionBarUtils.initStatus((BaseActivity) mContext, isShowPrimordialStatus, false, true);
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
            setBackColor(R.color.white);
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
     * 设置Activity内容
     *
     * @param layoutId
     */
    public void initActivityContent(int layoutId) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View view = inflater.inflate(layoutId, null);
        llBaseContent.removeAllViews();
        llBaseContent.addView(view, param);
        ButterKnife.bind(mContext, llBaseContent);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (title != null)
            if (isImmersion)
                tvImmersionTitle.setText(title);
            else
                tvTitle.setText(title);
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
                tvTitle.setTextColor(mContext.getResources().getColorStateList(resId));
    }

    /**
     * 设置back箭头颜色
     *
     * @param resId
     */
    public void setBackColor(int resId) {
        if (isImmersion) {
            ivImmersionBack.setColorFilter(ContextCompat.getColor(mContext, resId));
        } else {
            ivBack.setColorFilter(ContextCompat.getColor(mContext, resId));
        }
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
     * 设置按钮
     *
     * @param resIds        图标资源
     * @param clickListener 按钮监听
     */
    public void setActionBarMenuText(int[] resIds, OnActionBarMenuClickListener clickListener) {
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
                menuHolder.initMenuText(res);
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
     * 设置点击返回效果
     *
     * @param onBackClickListener
     */
    public void setOnBackClickListener(View.OnClickListener onBackClickListener) {
        this.onBackClickListener = onBackClickListener;
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
     * 是否显示错误
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
