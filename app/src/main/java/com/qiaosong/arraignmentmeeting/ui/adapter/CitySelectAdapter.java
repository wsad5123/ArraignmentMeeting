package com.qiaosong.arraignmentmeeting.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.bean.CityBean;
import com.qiaosong.arraignmentmeeting.callback.OnCitySelectListener;
import com.qiaosong.arraignmentmeeting.ui.adapter.base.BaseRecyclerAdapter;
import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CitySelectAdapter extends BaseRecyclerAdapter<CityBean> {
    private OnCitySelectListener onCitySelectListener;

    public CitySelectAdapter(Context mContext, List<CityBean> mData) {
        super(mContext, mData);
    }

    public void setOnCitySelectListener(OnCitySelectListener onCitySelectListener) {
        this.onCitySelectListener = onCitySelectListener;
    }

    @Override
    public BaseViewHolder<CityBean> initViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mContext, parent);
    }


    class ViewHolder extends BaseViewHolder<CityBean> {

        @BindView(R.id.tv_name)
        TextView tvName;

        CityBean bean;

        public ViewHolder(Context mContext, ViewGroup parent) {
            super(mContext, parent);
        }

        @Override
        public int bindViewLayoutId() {
            return R.layout.item_recycler_dialog_select;
        }

        @Override
        public void initData(int position, CityBean obj) {
            super.initData(position, obj);
            bean = obj;
            tvName.setText(obj.getName());
        }

        @OnClick(R.id.tv_name)
        public void onClick(View view) {
            if (onCitySelectListener != null)
                onCitySelectListener.onSelect(bean);
        }
    }

}
