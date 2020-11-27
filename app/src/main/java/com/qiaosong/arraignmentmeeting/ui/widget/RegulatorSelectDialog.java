package com.qiaosong.arraignmentmeeting.ui.widget;

import android.app.Dialog;
import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.bean.RegulatorBean;
import com.qiaosong.arraignmentmeeting.callback.OnRegulatorSelectListener;
import com.qiaosong.arraignmentmeeting.ui.adapter.RegulatorSelectAdapter;
import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;


public class RegulatorSelectDialog {
    private Context mContext;
    private Dialog mDialog;
    private ViewHolder mViewHolder;
    private OnRegulatorSelectListener onRegulatorSelectListener;

    public RegulatorSelectDialog(final Context mContext) {
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

    public void initData(List<RegulatorBean> data) {
        mViewHolder.initData(data);
    }

    public void setOnRegulatorSelectListener(OnRegulatorSelectListener onRegulatorSelectListener) {
        this.onRegulatorSelectListener = onRegulatorSelectListener;
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.rv_content)
        RecyclerView rvContent;

        RegulatorSelectAdapter adapter;

        public ViewHolder(Context mContext) {
            super(mContext);
        }

        @Override
        public int bindViewLayoutId() {
            return R.layout.dialog_select;
        }

        public void initData(List<RegulatorBean> data) {
            if (adapter == null) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rvContent.setLayoutManager(linearLayoutManager);
                adapter = new RegulatorSelectAdapter(mContext, data);
                adapter.setOnRegulatorSelectListener(onRegulatorSelectListener);
                rvContent.setAdapter(adapter);
            } else {
                adapter.updateData(data);
            }
        }
    }

}
