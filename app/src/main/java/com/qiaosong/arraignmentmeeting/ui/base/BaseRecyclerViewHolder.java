package com.qiaosong.arraignmentmeeting.ui.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder {
    /**
     * Holder所绑定的View
     */
    public BaseViewHolder<T> holderView;
    /**
     * 上下文
     */
    public Context mContext;

    public BaseRecyclerViewHolder(@NonNull Context mContext, @NonNull BaseViewHolder<T> holderView) {
        super(holderView.getView());
        this.holderView = holderView;
        this.mContext = mContext;
    }

    /**
     * 返回View
     *
     * @return
     */
    public BaseViewHolder<T> getViewHolder() {
        return holderView;
    }

    public void initData(int position, T obj) {
        holderView.initData(position, obj);
    }

    public void initHeaderData() {
        holderView.initHeaderData();
    }

    public void initFooterData() {
        holderView.initFooterData();
    }
}