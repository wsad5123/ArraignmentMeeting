package com.qiaosong.arraignmentmeeting.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class DeviceUtils {
    private static final String TAG = DeviceUtils.class.getSimpleName();

    private static final String TEMP_DIR = "system_config";
    private static final String TEMP_FILE_NAME = "system_file";
    private static final String SP_NAME = "device_info";
    private static final String SP_KEY_DEVICE_ID = "device_id";

    public static String getDeviceId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        String deviceId = sharedPreferences.getString(SP_KEY_DEVICE_ID, null);
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        deviceId = getIMEI(context);
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = createUUID(context);
        }
        sharedPreferences.edit()
                .putString(SP_KEY_DEVICE_ID, deviceId)
                .apply();
        return deviceId;
    }

    private static String createUUID(Context context) {
        String uuid = UUID.randomUUID().toString().replace("-", "");

        File externalDownloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File applicationFileDir = new File(externalDownloadsDir, TEMP_DIR);
        if (!applicationFileDir.exists()) {
            if (!applicationFileDir.mkdirs()) {
                LogUtils.e(TAG, "文件夹创建失败: " + applicationFileDir.getPath());
            }
        }
        File file = new File(applicationFileDir, TEMP_FILE_NAME);
        if (!file.exists()) {
            FileWriter fileWriter = null;
            try {
                if (file.createNewFile()) {
                    fileWriter = new FileWriter(file, false);
                    fileWriter.write(uuid);
                } else {
                    LogUtils.e(TAG, "文件创建失败：" + file.getPath());
                }
            } catch (IOException e) {
                LogUtils.e(TAG, "文件创建失败：" + file.getPath());
                e.printStackTrace();
            } finally {
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;
            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                uuid = bufferedReader.readLine();

                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return uuid;
    }

    private static String getIMEI(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager == null) {
                return null;
            }
            @SuppressLint({"MissingPermission", "HardwareIds"}) String imei = telephonyManager.getDeviceId();
            return imei;
        } catch (Exception e) {
            return null;
        }
    }
}
