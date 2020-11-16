package com.qiaosong.arraignmentmeeting.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.ui.adapter.base.BaseRecyclerAdapter;
import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class KeyBoardAdapter extends BaseRecyclerAdapter<String> {

    OnKeyBoardClickListener onKeyBoardClickListener;

    public KeyBoardAdapter(Context mContext, List<String> mData) {
        super(mContext, mData);
        mData = new ArrayList<>();
        mData.add("1");
        mData.add("2");
        mData.add("3");
        mData.add("4");
        mData.add("5");
        mData.add("6");
        mData.add("7");
        mData.add("8");
        mData.add("9");
        mData.add("删除");
        mData.add("0");
        mData.add("X");
        this.mData = mData;
    }

    @Override
    public BaseViewHolder<String> initViewHolder(ViewGroup parent, int viewType) {
        return new KeyBoardHolder(mContext, parent);
    }

    class KeyBoardHolder extends BaseViewHolder<String> {

        @BindView(R.id.tv_num)
        TextView tvNum;

        String obj;

        public KeyBoardHolder(Context mContext, ViewGroup parent) {
            super(mContext, parent);
        }


        @Override
        public int bindViewLayoutId() {
            return R.layout.item_recycler_key_board;
        }

        @Override
        public void initData(int position, String obj) {
            super.initData(position, obj);
            this.obj = obj;
            if (position == 9) {
                tvNum.setTextSize(15);
            } else if (position == 11) {
                tvNum.setTextSize(25);
            } else {
                tvNum.setTextSize(20);
            }
            tvNum.setText(obj);
        }

        @OnClick(R.id.tv_num)
        public void onClick(View view) {
            if (obj.equals("删除")) {
                if (onKeyBoardClickListener != null) {
                    onKeyBoardClickListener.onDelete();
                }
            } else {
                if (onKeyBoardClickListener != null) {
                    onKeyBoardClickListener.onClick(obj);
                }
            }
        }
    }

    public void setOnKeyBoardClickListener(OnKeyBoardClickListener onKeyBoardClickListener) {
        this.onKeyBoardClickListener = onKeyBoardClickListener;
    }

    public interface OnKeyBoardClickListener {
        void onClick(String data);

        void onDelete();
    }
}
