package com.qiaosong.arraignmentmeeting.http;

import android.content.Context;
import android.text.TextUtils;

import com.qiaosong.arraignmentmeeting.http.exception.ApiException;
import com.qiaosong.arraignmentmeeting.http.exception.HttpResponseException;
import com.qiaosong.arraignmentmeeting.ui.widget.ProgressDialog;
import com.qiaosong.baselibrary.bean.ApiResultBean;
import com.qiaosong.baselibrary.utils.ToastUtils;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Description: API统一订阅者，采用弱引用管理上下文，防止内存泄漏
 * @author: zhangxinyu
 * @date: 2017-01-03 14:07
 */
public abstract class ApiObserver<T> implements Observer<ApiResultBean<T>> {
    public WeakReference<Context> contextWeakReference;
    public boolean isShowLoading = false;
    public boolean isCancelable = true;
    public boolean isShowError = true;
    public String dialogMessage;
    private ProgressDialog mProgressDialog;

    public ApiObserver(Context context) {
        contextWeakReference = new WeakReference<Context>(context);
    }

    public ApiObserver(Context context, boolean isShowLoading) {
        contextWeakReference = new WeakReference<Context>(context);
        this.isShowLoading = isShowLoading;
    }

    public ApiObserver(Context context, String dialogMessage, boolean isShowLoading, boolean isCancelable) {
        contextWeakReference = new WeakReference<Context>(context);
        this.dialogMessage = dialogMessage;
        this.isShowLoading = isShowLoading;
        this.isCancelable = isCancelable;
    }

    public ApiObserver(Context context, boolean isShowLoading, boolean isCancelable) {
        contextWeakReference = new WeakReference<Context>(context);
        this.isShowLoading = isShowLoading;
        this.isCancelable = isCancelable;
    }

    public ApiObserver(Context context, boolean isShowLoading, boolean isCancelable, boolean isShowError) {
        contextWeakReference = new WeakReference<Context>(context);
        this.isShowLoading = isShowLoading;
        this.isCancelable = isCancelable;
        this.isShowError = isShowError;
    }

    public ApiObserver(Context context, String dialogMessage, boolean isShowLoading, boolean isCancelable, boolean isShowError) {
        contextWeakReference = new WeakReference<Context>(context);
        this.dialogMessage = dialogMessage;
        this.isShowLoading = isShowLoading;
        this.isCancelable = isCancelable;
        this.isShowError = isShowError;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (isShowLoading) {
            mProgressDialog = new ProgressDialog(contextWeakReference.get(), isCancelable);
            if (!TextUtils.isEmpty(dialogMessage))
                mProgressDialog.setTitle(dialogMessage);
            mProgressDialog.show();
        }
    }


    @Override
    public void onComplete() {
        if (isShowLoading && mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        onFinish();
    }

    @Override
    public void onNext(ApiResultBean<T> apiResultBean) {
        if (apiResultBean != null) {
            if (apiResultBean.getErrCode() == ApiCode.Response.HTTP_SUCCESS) {
                onSuccess(apiResultBean.getData());
            } else {
                ApiException apiException = ApiException.handleException(new HttpResponseException(apiResultBean));
                onError(apiResultBean.getData(), apiException);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        onError(null, ApiException.handleException(e));
        onComplete();
    }

    public abstract void onSuccess(T data);

    public void onError(T data, ApiException exception) {
        if (isShowError && exception.isNeedShowError())
            ToastUtils.show(contextWeakReference.get(), exception.getErrorMessage());
    }

    public abstract void onFinish();
}
