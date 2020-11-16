package com.qiaosong.arraignmentmeeting.ui.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;

import java.util.List;

public abstract class BaseListAdapter<T, Z extends BaseViewHolder> extends BaseAdapter implements AdapterView.OnItemClickListener {
    public List<T> mData;
    public Context mContext;
    private int offset;//偏移量 如果listView 设置了headerView 则需要传入偏移量

    public BaseListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public BaseListAdapter(Context mContext, List<T> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    /**
     * 设置偏移量
     *
     * @param offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
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
     * 更新数据
     *
     * @param mData
     */
    public void setData(List<T> mData) {
        this.mData = mData;
    }


    /**
     * 获取数据
     *
     * @return
     */
    public List<T> getData() {
        return mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData == null || mData.isEmpty() ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Z holder = null;
        if (convertView == null) {
            holder = getViewHolder();
            convertView = holder.getView();
            convertView.setTag(holder);
        } else {
            holder = (Z) convertView.getTag();
        }
        holder.initData(position, getItem(position));
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == offset - 1 || position - offset >= mData.size() || position - offset < 0)
            return;
        onItemClick(view, position - offset, id);
    }

    /**
     * 点击事件重现该方法实现
     *
     * @param view
     * @param position
     * @param id
     */
    public void onItemClick(View view, int position, long id) {

    }

    public abstract Z getViewHolder();


}
