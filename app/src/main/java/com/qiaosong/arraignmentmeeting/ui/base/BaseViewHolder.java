package com.qiaosong.arraignmentmeeting.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * Holder
 */
public abstract class BaseViewHolder<T> {
    /**
     * Holder所绑定的View
     */
    public View holderView;
    /**
     * 上下文
     */
    public Context mContext;


    public BaseViewHolder(Context mContext) {
        if (mContext != null) {
            this.mContext = mContext;
            holderView = View.inflate(mContext, bindViewLayoutId(), null);
            ButterKnife.bind(this, holderView);
        }

    }

    public BaseViewHolder(Context mContext, ViewGroup parent) {
        if (mContext != null) {
            this.mContext = mContext;
            holderView = LayoutInflater.from(mContext).inflate(bindViewLayoutId(), parent, false);
            ButterKnife.bind(this, holderView);
        }

    }


    /**
     * 返回View
     *
     * @return
     */
    public View getView() {
        return holderView;
    }

    /**
     * 获取View資源文件ID
     *
     * @return
     */
    public abstract int bindViewLayoutId();

    /**
     * 当加载数据时调用，普通View使用手动调用
     * 继承BaseListAdapter,BaseRecyclerAdapter自动调用
     * 当使用BaseRecyclerAdapter时该类只在非header和footer时调用
     *
     * @param position
     * @param obj
     */
    public void initData(int position, T obj) {

    }

    /**
     * 当父控件继承BaseRecyclerAdapter时有header时调用
     */
    public void initHeaderData() {

    }

    /**
     * 当父控件继承BaseRecyclerAdapter时有footer时调用
     */
    public void initFooterData() {

    }
}
