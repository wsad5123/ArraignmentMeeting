package com.qiaosong.arraignmentmeeting.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.bean.ProvinceBean;
import com.qiaosong.arraignmentmeeting.callback.OnProvinceSelectListener;
import com.qiaosong.arraignmentmeeting.ui.adapter.base.BaseRecyclerAdapter;
import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProvinceSelectAdapter extends BaseRecyclerAdapter<ProvinceBean> {
    private OnProvinceSelectListener onProvinceSelectListener;

    public ProvinceSelectAdapter(Context mContext, List<ProvinceBean> mData) {
        super(mContext, mData);
    }

    @Override
    public BaseViewHolder<ProvinceBean> initViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mContext, parent);
    }

    public void setOnProvinceSelectListener(OnProvinceSelectListener onProvinceSelectListener) {
        this.onProvinceSelectListener = onProvinceSelectListener;
    }

    class ViewHolder extends BaseViewHolder<ProvinceBean> {

        @BindView(R.id.tv_name)
        TextView tvName;

        ProvinceBean bean;

        public ViewHolder(Context mContext, ViewGroup parent) {
            super(mContext, parent);
        }

        @Override
        public int bindViewLayoutId() {
            return R.layout.item_recycler_dialog_select;
        }

        @Override
        public void initData(int position, ProvinceBean obj) {
            super.initData(position, obj);
            bean = obj;
            tvName.setText(obj.getName());
        }

        @OnClick(R.id.tv_name)
        public void onClick(View view) {
            if (onProvinceSelectListener != null)
                onProvinceSelectListener.onSelect(bean);
        }
    }

}
