package com.qiaosong.arraignmentmeeting.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;

import butterknife.BindView;


public class ProgressDialog {
    private Context mContext;
    private Dialog mDialog;
    private ViewHolder mViewHolder;

    public ProgressDialog(final Context mContext,boolean isCancelable) {
        this.mContext = mContext;
        mViewHolder = new ViewHolder(mContext);
        mDialog = new Dialog(mContext, R.style.PlatformDialog);
        mDialog.setContentView(mViewHolder.getView());
        mDialog.setCancelable(isCancelable);
        mDialog.setCanceledOnTouchOutside(isCancelable);
    }


    public void dismiss() {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();

    }

    public void show() {
        if (mDialog != null && !mDialog.isShowing())
            mDialog.show();
    }

    public void setTitle(String title) {
        mViewHolder.tvTitle.setText(title);
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ViewHolder(Context mContext) {
            super(mContext);
        }

        @Override
        public int bindViewLayoutId() {
            return R.layout.dialog_progress;
        }


    }
}
