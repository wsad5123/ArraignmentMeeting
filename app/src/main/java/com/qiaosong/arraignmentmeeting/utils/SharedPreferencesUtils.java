package com.qiaosong.arraignmentmeeting.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    //SharedPreferences文件名
    private static final String FILE_NAME = "ArraignmentMeeting";

    public static final String HTTP_ADDRESS_BEAN_JSON = "http_address_bean_json";//网络地址
    public static final String DEVICE_UUID = "device_uuid";//设备唯一号
    public static final String PROVINCE_NAME = "province_name";//省份名称


    public static void setHttpAddressBeanJson(Context context, String jsonData) {
        saveData(context, HTTP_ADDRESS_BEAN_JSON, jsonData);
    }

    public static String getHttpAddressBeanJson(Context context) {
        return (String) getData(context, HTTP_ADDRESS_BEAN_JSON, "");
    }

    public static void setDeviceUuid(Context context, String jsonData) {
        saveData(context, DEVICE_UUID, jsonData);
    }

    public static String getDeviceUuid(Context context) {
        return (String) getData(context, DEVICE_UUID, "");
    }

    public static void setProvinceName(Context context, String jsonData) {
        saveData(context, PROVINCE_NAME, jsonData);
    }

    public static String getProvinceName(Context context) {
        return (String) getData(context, PROVINCE_NAME, "");
    }


    /**
     * 删除SharedPreferences文件中所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        remove(context, HTTP_ADDRESS_BEAN_JSON);
    }

    /**
     * 保存数据到文件
     *
     * @param context
     * @param key
     * @param data
     */
    private static void saveData(Context context, String key, Object data) {
        String type = data.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) data);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) data);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) data);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) data);
        }
        editor.commit();
    }

    /**
     * 从文件中读取数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    private static Object getData(Context context, String key, Object defValue) {

        String type = defValue.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences
                (FILE_NAME, Context.MODE_PRIVATE);

        //defValue为为默认值，如果当前获取不到数据就返回它
        if ("Integer".equals(type)) {
            return sharedPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return sharedPreferences.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return sharedPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return sharedPreferences.getLong(key, (Long) defValue);
        }
        return null;
    }

    /**
     * 删除SharedPreferences文件中某个key的数据
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean remove(Context context, String key) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit().remove(key).commit();
    }
}
