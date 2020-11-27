package com.qiaosong.arraignmentmeeting.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.bean.RegulatorTypeBean;
import com.qiaosong.arraignmentmeeting.callback.OnProvinceSelectListener;
import com.qiaosong.arraignmentmeeting.callback.OnRegulatorTypeSelectListener;
import com.qiaosong.arraignmentmeeting.ui.adapter.base.BaseRecyclerAdapter;
import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RegulatorTypeSelectAdapter extends BaseRecyclerAdapter<RegulatorTypeBean> {
    private OnRegulatorTypeSelectListener onRegulatorTypeSelectListener;

    public RegulatorTypeSelectAdapter(Context mContext, List<RegulatorTypeBean> mData) {
        super(mContext, mData);
    }

    @Override
    public BaseViewHolder<RegulatorTypeBean> initViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mContext, parent);
    }

    public void setOnRegulatorTypeSelectListener(OnRegulatorTypeSelectListener onRegulatorTypeSelectListener) {
        this.onRegulatorTypeSelectListener = onRegulatorTypeSelectListener;
    }

    class ViewHolder extends BaseViewHolder<RegulatorTypeBean> {

        @BindView(R.id.tv_name)
        TextView tvName;

        RegulatorTypeBean bean;

        public ViewHolder(Context mContext, ViewGroup parent) {
            super(mContext, parent);
        }

        @Override
        public int bindViewLayoutId() {
            return R.layout.item_recycler_dialog_select;
        }

        @Override
        public void initData(int position, RegulatorTypeBean obj) {
            super.initData(position, obj);
            bean = obj;
            tvName.setText(obj.getPtypename());
        }

        @OnClick(R.id.tv_name)
        public void onClick(View view) {
            if (onRegulatorTypeSelectListener != null)
                onRegulatorTypeSelectListener.onSelect(bean);
        }
    }

}
