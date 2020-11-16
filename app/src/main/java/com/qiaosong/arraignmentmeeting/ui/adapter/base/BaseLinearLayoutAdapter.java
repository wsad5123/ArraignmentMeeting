package com.qiaosong.arraignmentmeeting.ui.adapter.base;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;

import java.util.List;

public abstract class BaseLinearLayoutAdapter<T, Z extends BaseViewHolder<T>> {
    public List<T> mData;
    public LinearLayout mLinearLayout;
    public Context mContext;

    public BaseLinearLayoutAdapter(Context mContext, LinearLayout mLinearLayout) {
        this.mContext = mContext;
        this.mLinearLayout = mLinearLayout;
    }

    public BaseLinearLayoutAdapter(Context mContext, LinearLayout mLinearLayout, List<T> mData) {
        this.mContext = mContext;
        this.mLinearLayout = mLinearLayout;
        this.mData = mData;
        notifyDataSetChanged();
    }


    public void notifyDataSetChanged() {
        if (mLinearLayout == null) {//为空抛出异常
            throw new NullPointerException();
        }
        mLinearLayout.removeAllViews();
        if (getCount() > 0) {
            for (int i = 0; i < getCount(); i++) {
                int j = i;
                Z holder = getViewHolder();
                holder.initData(i, mData.get(i));
                holder.getView().setTag(holder);
                holder.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick(v, j);
                    }
                });
                mLinearLayout.addView(holder.getView());
            }
        }
    }

    /**
     * 更新数据
     *
     * @param mData
     */
    public void updateData(List<T> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    /**
     * 获取数据
     *
     * @return
     */
    public List<T> getData() {
        return mData;
    }

    public int getCount() {
        return mData == null ? 0 : mData.size();
    }


    public T getItem(int position) {
        return mData == null || mData.isEmpty() ? null : mData.get(position);
    }

    /**
     * 点击事件重现该方法实现
     *
     * @param view
     * @param position
     */
    public void onItemClick(View view, int position) {

    }

    public abstract Z getViewHolder();


}
