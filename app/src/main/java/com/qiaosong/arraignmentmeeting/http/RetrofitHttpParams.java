package com.qiaosong.arraignmentmeeting.http;

import android.content.Context;

import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.MultipartBody;

/**
 * Created by zhangxinyu on 2017/7/26.
 */
public class RetrofitHttpParams {
    protected Map<String, Object> requestParams;

    public RetrofitHttpParams(Context context) {
        requestParams = new TreeMap<String, Object>();
    }

    public void put(String key, String value) {
        requestParams.put(key, value);
    }

    public void put(String key, CharSequence value) {
        requestParams.put(key, value);
    }

    public void put(String key, int value) {
        requestParams.put(key, value);
    }

    public void put(String key, boolean value) {
        requestParams.put(key, value);
    }

    public void put(String key, long value) {
        requestParams.put(key, value);
    }

    public void put(String key, double value) {
        requestParams.put(key, value);
    }

    public void put(String key, Object value) {
        requestParams.put(key, value);
    }

    public void put(String key, InputStream stream) {
        requestParams.put(key, stream);
    }

    public void put(Map<String, Object> source) {
        if (source != null) {
            for (Map.Entry<String, Object> entry : source.entrySet()) {
                requestParams.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public boolean containsKey(String key) {
        return requestParams.containsKey(key);
    }

    public Map<String, Object> getRequestParams() {
        requestParams.put("app", "Android");
        return requestParams;
    }

    public MultipartBody.Builder getRequestMultipartBody() {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        return builder;
    }

    public void setRequestParams(Map<String, Object> params) {
        requestParams = params;
    }

    @Override
    public String toString() {
        return requestParams.toString();
    }


    public void remove(String key) {
        requestParams.remove(key);
    }

}