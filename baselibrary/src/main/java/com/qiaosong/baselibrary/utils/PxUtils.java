package com.qiaosong.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.qiaosong.baselibrary.manager.UIManager;

public class PxUtils {
    public static int mDeviceWidth = 0;
    public static int mDeviceHeight = 0;

    /**
     * 获取屏幕宽度和高度，单位为px
     *
     * @param context
     * @return
     */
    public static Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }

    /**
     * 获取屏幕长宽比
     *
     * @return
     */
    public static float getScreenRate(Point p) {
        float H = p.y;
        float W = p.x;
        return (H / W);
    }

    public static int getDeviceWidth(Activity context) {
        if (mDeviceWidth == 0) {
            DisplayMetrics dm = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(dm);

            mDeviceWidth = dm.widthPixels;
        }

        return mDeviceWidth;
    }

    public static int getDeviceHeight(Activity context) {
        if (mDeviceHeight == 0) {
            DisplayMetrics dm = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(dm);

            mDeviceHeight = dm.heightPixels;
        }

        return mDeviceHeight;
    }

    public static int getDeviceWidth() {
        if (mDeviceWidth == 0) {
            WindowManager wm = (WindowManager) UIManager.getInstance().getBaseContext()
                    .getApplicationContext()
                    .getSystemService(Context.WINDOW_SERVICE);
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);
            mDeviceWidth = size.x;
        }
        return mDeviceWidth;
    }

    public static int getDeviceHeight() {
        if (mDeviceHeight == 0) {
            WindowManager wm = (WindowManager) UIManager.getInstance().getBaseContext()
                    .getApplicationContext()
                    .getSystemService(Context.WINDOW_SERVICE);
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);
            mDeviceHeight = size.y;
        }

        return mDeviceHeight;
    }

    private static Context contextInstance;

    private static Context getContextInstance() {
        if (contextInstance == null) {
            contextInstance = UIManager.getInstance().getBaseContext();
        }
        return contextInstance;
    }

    public static int dip2px(float dipValue) {
        final float scale = getContextInstance().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static double getMessageImageMaxLength() {
        return getDeviceWidth() * 0.375;
    }

    public static int getStatusTitleHeight(Context mContext) {
        int statusBarHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = mContext.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (statusBarHeight == -1) {
            int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = mContext.getResources().getDimensionPixelSize(resourceId);
            }
        }
        return statusBarHeight;
    }
}
