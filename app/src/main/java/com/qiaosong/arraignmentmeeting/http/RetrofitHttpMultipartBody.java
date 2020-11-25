package com.qiaosong.arraignmentmeeting.http;

import android.content.Context;

import java.util.Map;
import java.util.TreeMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RetrofitHttpMultipartBody {

    protected Map<String, Object> requestParams;
    protected MultipartBody.Builder builder;

    public RetrofitHttpMultipartBody(Context context) {
        requestParams = new TreeMap<String, Object>();
        builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
    }

    public void put(String key, String value) {
        requestParams.put(key, value);
        builder.addFormDataPart(key, value);
    }

    public void put(String name, String filename, RequestBody body) {
        builder.addFormDataPart(name, filename, body);
    }

    public MultipartBody.Builder getRequestMultipartBody() {
        requestParams.put("app", "Android");
        builder.addFormDataPart("app", "Android");
        return builder;
    }

}
