package com.qiaosong.arraignmentmeeting.utils;

import android.util.Log;

import com.qiaosong.baselibrary.BuildConfig;

public class LogUtils {
    private static final String LOG_TAG = "log_tag";
    //生产false 测试true
    public static boolean debug = BuildConfig.DEBUG;

    public static void d(String tag, String msg) {
        if (debug) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        if (debug) {
            Log.d(LOG_TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (debug) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg) {
        if (debug) {
            Log.e(LOG_TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (debug) {
            Log.i(tag, msg);
        }
    }

    public static void i(String msg) {
        if (debug) {
            Log.i(LOG_TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (debug) {
            Log.w(tag, msg);
        }
    }


    public static void w(String msg) {
        if (debug) {
            Log.w(LOG_TAG, msg);
        }
    }
}
