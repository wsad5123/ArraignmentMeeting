package com.qiaosong.arraignmentmeeting.ui.widget;

import android.app.Dialog;
import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.bean.FunitBean;
import com.qiaosong.arraignmentmeeting.bean.PrisonBean;
import com.qiaosong.arraignmentmeeting.bean.api.ApiRegulatorBean;
import com.qiaosong.arraignmentmeeting.callback.OnRegulatorSelectListener;
import com.qiaosong.arraignmentmeeting.ui.adapter.FunitSelectAdapter;
import com.qiaosong.arraignmentmeeting.ui.adapter.PrisonSelectAdapter;
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

    public void initData(ApiRegulatorBean bean) {
        if (bean.getUnitData() != null) {
            mViewHolder.initFunitData(bean.getUnitData());
        }
        if (bean.getPrisonData() != null) {
            mViewHolder.initPrisonData(bean.getPrisonData());
        }
    }

    public void setOnRegulatorSelectListener(OnRegulatorSelectListener onRegulatorSelectListener) {
        this.onRegulatorSelectListener = onRegulatorSelectListener;
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.rv_content)
        RecyclerView rvContent;

        PrisonSelectAdapter prisonSelectAdapter;
        FunitSelectAdapter funitSelectAdapter;

        public ViewHolder(Context mContext) {
            super(mContext);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            rvContent.setLayoutManager(linearLayoutManager);
        }

        @Override
        public int bindViewLayoutId() {
            return R.layout.dialog_select;
        }

        public void initPrisonData(List<PrisonBean> data) {
            if (prisonSelectAdapter == null) {
                prisonSelectAdapter = new PrisonSelectAdapter(mContext, data);
                prisonSelectAdapter.setOnRegulatorSelectListener(onRegulatorSelectListener);
                rvContent.setAdapter(prisonSelectAdapter);
            } else {
                prisonSelectAdapter.updateData(data);
            }
        }

        private void initFunitData(List<FunitBean> data) {
            if (funitSelectAdapter == null) {
                funitSelectAdapter = new FunitSelectAdapter(mContext, data);
                funitSelectAdapter.setOnRegulatorSelectListener(onRegulatorSelectListener);
                rvContent.setAdapter(funitSelectAdapter);
            } else {
                funitSelectAdapter.updateData(data);
            }
        }
    }

}
