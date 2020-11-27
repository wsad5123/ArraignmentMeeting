package com.qiaosong.arraignmentmeeting.ui.widget;

import android.app.Dialog;
import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.bean.RegulatorTypeBean;
import com.qiaosong.arraignmentmeeting.callback.OnProvinceSelectListener;
import com.qiaosong.arraignmentmeeting.callback.OnRegulatorTypeSelectListener;
import com.qiaosong.arraignmentmeeting.ui.adapter.ProvinceSelectAdapter;
import com.qiaosong.arraignmentmeeting.ui.adapter.RegulatorTypeSelectAdapter;
import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;


public class RegulatorTypeSelectDialog {
    private Context mContext;
    private Dialog mDialog;
    private ViewHolder mViewHolder;
    private OnRegulatorTypeSelectListener onRegulatorTypeSelectListener;

    public RegulatorTypeSelectDialog(final Context mContext) {
        this.mContext = mContext;
        mViewHolder = new ViewHolder(mContext);
        mDialog = new Dialog(mContext, R.style.PlatformDialog);
        mDialog.setContentView(mViewHolder.getView());
    }


    public void dismiss() {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();

    }

    public void show() {
        if (mDialog != null && !mDialog.isShowing())
            mDialog.show();
    }

    public void initData(List<RegulatorTypeBean> data) {
        mViewHolder.initData(data);
    }

    public void setOnRegulatorTypeSelectListener(OnRegulatorTypeSelectListener onRegulatorTypeSelectListener) {
        this.onRegulatorTypeSelectListener = onRegulatorTypeSelectListener;
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.rv_content)
        RecyclerView rvContent;

        RegulatorTypeSelectAdapter adapter;

        public ViewHolder(Context mContext) {
            super(mContext);
        }

        @Override
        public int bindViewLayoutId() {
            return R.layout.dialog_select;
        }

        public void initData(List<RegulatorTypeBean> data) {
            if (adapter == null) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rvContent.setLayoutManager(linearLayoutManager);
                adapter = new RegulatorTypeSelectAdapter(mContext, data);
                adapter.setOnRegulatorTypeSelectListener(onRegulatorTypeSelectListener);
                rvContent.setAdapter(adapter);
            } else {
                adapter.updateData(data);
            }
        }
    }

}
