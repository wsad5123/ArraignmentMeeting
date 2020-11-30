package com.qiaosong.arraignmentmeeting.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.bean.FunitBean;
import com.qiaosong.arraignmentmeeting.bean.PrisonBean;
import com.qiaosong.arraignmentmeeting.callback.OnRegulatorSelectListener;
import com.qiaosong.arraignmentmeeting.ui.adapter.base.BaseRecyclerAdapter;
import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FunitSelectAdapter extends BaseRecyclerAdapter<FunitBean> {
    private OnRegulatorSelectListener onRegulatorSelectListener;

    public FunitSelectAdapter(Context mContext, List<FunitBean> mData) {
        super(mContext, mData);
    }

    @Override
    public BaseViewHolder<FunitBean> initViewHolder(ViewGroup parent, int view) {
        return new ViewHolder(mContext, parent);
    }

    public void setOnRegulatorSelectListener(OnRegulatorSelectListener onRegulatorSelectListener) {
        this.onRegulatorSelectListener = onRegulatorSelectListener;
    }

    class ViewHolder extends BaseViewHolder<FunitBean> {

        @BindView(R.id.tv_name)
        TextView tvName;

        FunitBean bean;

        public ViewHolder(Context mContext, ViewGroup parent) {
            super(mContext, parent);
        }

        @Override
        public int bindViewLayoutId() {
            return R.layout.item_recycler_dialog_select;
        }

        @Override
        public void initData(int position, FunitBean obj) {
            super.initData(position, obj);
            bean = obj;
            tvName.setText(obj.getFunitName());
        }

        @OnClick(R.id.tv_name)
        public void onClick(View view) {
            if (onRegulatorSelectListener != null)
                onRegulatorSelectListener.onSelect(null, bean);
        }
    }

}
