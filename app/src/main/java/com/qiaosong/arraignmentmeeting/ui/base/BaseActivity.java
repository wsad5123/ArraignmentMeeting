package com.qiaosong.arraignmentmeeting.ui.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.qiaosong.baselibrary.callback.OnActionBarMenuClickListener;
import com.qiaosong.baselibrary.event.BaseEvent;
import com.qiaosong.baselibrary.event.EventConstant;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.annotation.Nullable;

public abstract class BaseActivity<P extends IPresenter> extends RxAppCompatActivity implements IView {
    public P mvpPresenter;
    protected Context mContext;
    public BaseActivityHolder mBaseHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mContext = this;
        mvpPresenter = bindPresenter();
        mBaseHolder = new BaseActivityHolder(this, isImmersion());
        mBaseHolder.initActivityContent(getLayoutId());
        setContentView(mBaseHolder.getView());
        //actionbar 后执行
        mBaseHolder.initActionBar(isShowPrimordialStatus(), isShowStatusTitle(), isShowActionBar());
    }

    /**
     * 获取子类activity布局layoutId
     *
     * @return layoutId
     */
    public abstract int getLayoutId();

    /**
     * 绑定Presenter
     *
     * @return
     */
    public abstract P bindPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //在生命周期结束时，将presenter与view之间的联系断开，防止出现内存泄露
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    @Override
    public Activity getSelfActivity() {
        return this;
    }

    /**
     * 是否显示标题栏
     *
     * @return
     */
    public boolean isShowActionBar() {
        return true;
    }

    /**
     * 是否显示状态栏
     *
     * @return
     */
    public boolean isShowStatusTitle() {
        return true;
    }

    /**
     * 是否显示原生状态栏
     *
     * @return
     */
    public boolean isShowPrimordialStatus() {
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
     * 设置刷新点击效果
     *
     * @param onRefreshClickListener
     */
    public void setOnRefreshClickListener(View.OnClickListener onRefreshClickListener) {
        mBaseHolder.setOnRefreshClickListener(onRefreshClickListener);
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
     * 设置back箭头颜色
     *
     * @param resId
     */
    public void setBackColor(int resId) {
        if (resId != 0)
            mBaseHolder.setBackColor(resId);
    }

    /**
     * event通知
     *
     * @param event
     */
    @Subscribe
    public void onEvent(BaseEvent event) {
        if (EventConstant.EVENT_LOGOUT.equals(event.getData())) {
            if (isTopActivity())
                return;
            finish();
        }
    }

    /**
     * 判断是否为最上层activity
     *
     * @return
     */
    private boolean isTopActivity() {
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        return cn.getClassName().contains(this.getLocalClassName());
    }
}
