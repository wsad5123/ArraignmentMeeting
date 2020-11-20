package com.qiaosong.baselibrary.utils;

import android.Manifest;

import androidx.appcompat.app.AppCompatActivity;

import com.qiaosong.baselibrary.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class PermissionsUtils {

    /**
     * 申请使用权限，如果未获得权限则会弹出提示，已获得则不弹出
     */
    public static void requestPermissions(AppCompatActivity mContext, Consumer<Boolean> consumer, String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(mContext);
        rxPermissions.request(permissions)
                .subscribe(consumer);
    }

    /**
     * 判断是否拥有权限
     */
    public static boolean isHavePermissions(AppCompatActivity mContext, String permissions) {
        RxPermissions rxPermissions = new RxPermissions(mContext);
        return rxPermissions.isGranted(permissions);
    }


    /**
     * 申请使用权限，如果未获得权限则会弹出提示，已获得则不弹出,如果被拒绝则提示相应提示
     */
    public static void requestPermissionToast(final AppCompatActivity mContext, final Consumer<Boolean> consumer, final String permissions) {
        RxPermissions rxPermissions = new RxPermissions(mContext);
        rxPermissions.request(permissions)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        consumer.accept(aBoolean);
                        if (!aBoolean) {
                            if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions)) {
                                ToastUtils.show(mContext, R.string.permissions_read_external_storage);
                            } else if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(permissions)) {
                                ToastUtils.show(mContext, R.string.permissions_read_external_storage);
                            } else if (Manifest.permission.CAMERA.equals(permissions)) {
                                ToastUtils.show(mContext, R.string.permissions_camera);
                            }
                        }
                    }
                });
    }

}
