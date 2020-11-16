package com.qiaosong.arraignmentmeeting.ui.adapter.base;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.qiaosong.arraignmentmeeting.ui.base.BaseRecyclerViewHolder;
import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;

import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder<T>> {
    public static final int TYPE_NORMAL = -1;  //说明是不带有header和footer的
    public static final int TYPE_HEADER = -2;  //说明是带有Header的
    public static final int TYPE_FOOTER = -3;  //说明是带有Footer的

    private BaseViewHolder mHeaderView;
    private BaseViewHolder mFooterView;

    public List<T> mData;
    public Context mContext;

    public BaseRecyclerAdapter(Context mContext, List<T> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public void updateData(List<T> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    //HeaderView和FooterView的get和set函数
    public BaseViewHolder getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(BaseViewHolder headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public BaseViewHolder getFooterView() {
        return mFooterView;
    }

    public void setFooterView(BaseViewHolder footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    /**
     * 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    *
     */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return getItemType(position);
        }
        if (position == 0 && mHeaderView != null) {
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1 && mFooterView != null) {
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        int offset = mHeaderView == null ? 0 : 1;//头部需要减一
        return getItemType(position - offset);
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new BaseRecyclerViewHolder(mContext, mHeaderView);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new BaseRecyclerViewHolder(mContext, mFooterView);
        }
        return new BaseRecyclerViewHolder(mContext, initViewHolder(parent, viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            holder.initHeaderData();
            return;
        } else if (getItemViewType(position) == TYPE_FOOTER) {
            holder.initFooterData();
            return;
        } else {
            int offset = mHeaderView == null ? 0 : 1;//头部需要减一
            holder.initData(position - offset, mData == null ? null : mData.get(position - offset));
            return;
        }
    }

    @Override
    public int getItemCount() {
        int dataSize = mData == null ? 0 : mData.size();
        if (mHeaderView == null && mFooterView == null) {
            return dataSize;
        } else if (mHeaderView == null && mFooterView != null) {
            return dataSize + 1;
        } else if (mHeaderView != null && mFooterView == null) {
            return dataSize + 1;
        } else {
            return dataSize + 2;
        }
    }

    public abstract BaseViewHolder<T> initViewHolder(ViewGroup parent, int viewType);

    /**
     * 上层用于分类编写逻辑
     *
     * @param position
     * @return
     */
    public int getItemType(int position) {
        return TYPE_NORMAL;
    }
}
