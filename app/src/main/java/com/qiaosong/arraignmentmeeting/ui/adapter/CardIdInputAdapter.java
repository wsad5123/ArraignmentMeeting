package com.qiaosong.arraignmentmeeting.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiaosong.arraignmentmeeting.R;
import com.qiaosong.arraignmentmeeting.ui.adapter.base.BaseRecyclerAdapter;
import com.qiaosong.arraignmentmeeting.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class CardIdInputAdapter extends BaseRecyclerAdapter<String> {
    String[] mCardId = new String[18];

    public CardIdInputAdapter(Context mContext, List<String> mData) {
        super(mContext, mData);
    }

    /**
     * 输入
     *
     * @param data
     */
    public void input(String data) {
        for (int i = 0; i < mCardId.length; i++) {
            if (TextUtils.isEmpty(mCardId[i])) {
                mCardId[i] = data;
                break;
            }
        }
        notifyDataSetChanged();
    }

    public void delete() {
        for (int i = mCardId.length - 1; i >= 0; i--) {
            if (!TextUtils.isEmpty(mCardId[i])) {
                mCardId[i] = "";
                break;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCardId.length;
    }

    @Override
    public BaseViewHolder<String> initViewHolder(ViewGroup parent, int viewType) {
        return new CardIdInputHolder(mContext, parent);
    }

    class CardIdInputHolder extends BaseViewHolder<String> {

        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.v_line)
        View vLine;

        public CardIdInputHolder(Context mContext, ViewGroup parent) {
            super(mContext, parent);
        }

        @Override
        public int bindViewLayoutId() {
            return R.layout.item_recycler_card_id_input;
        }

        @Override
        public void initData(int position, String obj) {
            super.initData(position, obj);
            String num = mCardId[position];
            if (TextUtils.isEmpty(num)) {
                tvNum.setText("");
                vLine.setVisibility(View.VISIBLE);
            } else {
                tvNum.setText(num);
                vLine.setVisibility(View.INVISIBLE);
            }
        }
    }
}
