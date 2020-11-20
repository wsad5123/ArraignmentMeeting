package com.qiaosong.baselibrary.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public static void show(Context mContext, String msg, int duration) {
        Toast.makeText(mContext, msg, duration).show();
    }

    public static void show(Context mContext, int msgRes, int duration) {
        Toast.makeText(mContext, msgRes, duration).show();
    }

    public static void show(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context mContext, int msgRes) {
        Toast.makeText(mContext, msgRes, Toast.LENGTH_SHORT).show();
    }

}
