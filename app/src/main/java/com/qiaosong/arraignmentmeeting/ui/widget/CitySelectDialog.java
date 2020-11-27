package com.qiaosong.arraignmentmeeting.ui.widget;

import android.app.Dialog;
import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.bean.CityBean;
import com.qiaosong.arraignmentmeeting.bean.ProvinceBean;
import com.qiaosong.arraignmentmeeting.callback.OnCitySelectListener;
import com.qiaosong.arraignmentmeeting.callback.OnProvinceSelectListener;
import com.qiaosong.arraignmentmeeting.ui.adapter.CitySelectAdapter;
import com.qiaosong.arraignmentmeeting.ui.adapter.ProvinceSelectAdapter;
import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;


public class CitySelectDialog {
    private Context mContext;
    private Dialog mDialog;
    private ViewHolder mViewHolder;
    private OnCitySelectListener onCitySelectListener;

    public CitySelectDialog(final Context mContext) {
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

    public void initData(List<CityBean> data) {
        mViewHolder.initData(data);
    }

    public void setOnCitySelectListener(OnCitySelectListener onCitySelectListener) {
        this.onCitySelectListener = onCitySelectListener;
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.rv_content)
        RecyclerView rvContent;

        CitySelectAdapter adapter;

        public ViewHolder(Context mContext) {
            super(mContext);
        }

        @Override
        public int bindViewLayoutId() {
            return R.layout.dialog_select;
        }

        public void initData(List<CityBean> data) {
            if (adapter == null) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rvContent.setLayoutManager(linearLayoutManager);
                adapter = new CitySelectAdapter(mContext, data);
                adapter.setOnCitySelectListener(onCitySelectListener);
                rvContent.setAdapter(adapter);
            } else {
                adapter.updateData(data);
            }
        }
    }

}
