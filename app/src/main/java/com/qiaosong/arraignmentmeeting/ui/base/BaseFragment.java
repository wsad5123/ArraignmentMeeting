package com.qiaosong.arraignmentmeeting.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qiaosong.baselibrary.callback.OnActionBarMenuClickListener;
import com.qiaosong.baselibrary.event.BaseEvent;

import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Unbinder;

public abstract class BaseFragment<P extends IPresenter> extends RxFragment implements IView {
    public P mvpPresenter;
    protected Context mContext;
    private BaseFragmentHolder mBaseHolder;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        if (mBaseHolder == null) {
            mBaseHolder = new BaseFragmentHolder(mContext, isImmersion());
            mBaseHolder.initActionBar(isShowStatusTitle(), isShowActionBar());
            unbinder = mBaseHolder.initFragmentContent(this, getLayoutId());
        }
        mvpPresenter = bindPresenter();
        return mBaseHolder.getView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        //在生命周期结束时，将presenter与view之间的联系断开，防止出现内存泄露
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
        unbinder.unbind();
    }

    @Override
    public Activity getSelfActivity() {
        return getActivity();
    }

    /**
     * 获取子类fragment布局layoutId
     *
     * @return layoutId
     */
    protected abstract int getLayoutId();

    /**
     * 绑定Presenter
     *
     * @return
     */
    public abstract P bindPresenter();

    /**
     * 是否显示标题栏
     *
     * @return
     */
    public boolean isShowActionBar() {
        return false;
    }

    /**
     * 是否显示状态栏
     *
     * @return
     */
    public boolean isShowStatusTitle() {
        return false;
    }

    /**
     * 是否显示沉浸式
     *
     * @return
     */
    public boolean isImmersion() {
        return false;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mBaseHolder.setTitle(title);
        }
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setTitle(int resId) {
        if (resId != 0) {
            mBaseHolder.setTitle(getString(resId));
        }
    }

    /**
     * 设置标题字颜色
     *
     * @param resId
     */
    public void setTitleColor(int resId) {
        if (resId != 0) {
            mBaseHolder.setTitleColor(resId);
        }
    }


    /**
     * 设置标题背景
     *
     * @param resource
     */
    public void setActionBarBackgroundResource(int resource) {
        mBaseHolder.setActionBarBackgroundResource(resource);
    }

    /**
     * 设置按钮
     *
     * @param resIds
     * @param clickListener
     */
    public void setActionBarMenu(OnActionBarMenuClickListener clickListener, int... resIds) {
        mBaseHolder.setActionBarMenu(resIds, clickListener);
    }

    /**
     * 是否显示错误信息
     *
     * @param isVisible
     */
    public void setErrorVisible(boolean isVisible) {
        mBaseHolder.setErrorVisible(isVisible);
    }

    /**
     * 是否显示加载中
     *
     * @param isVisible
     */
    public void setLoadingVisible(boolean isVisible) {
        mBaseHolder.setLoadingVisible(isVisible);
    }


    /**
     * 设置刷新点击效果
     *
     * @param onRefreshClickListener
     */
    public void setOnRefreshClickListener(View.OnClickListener onRefreshClickListener) {
        mBaseHolder.setOnRefreshClickListener(onRefreshClickListener);
    }


    /**
     * event通知
     *
     * @param event
     */
    @Subscribe
    public void onEvent(BaseEvent event) {

    }
}
