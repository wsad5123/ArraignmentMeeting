package com.qiaosong.arraignmentmeeting.http.subscribe;

import android.app.Application;
import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseSubscribe {
    protected Context mContext;

    public BaseSubscribe(Context context) {
        mContext = context;
    }

    protected LifecycleProvider getLifecycleProvider() {
        LifecycleProvider provider = null;
        if (null != mContext && mContext instanceof LifecycleProvider) {
            provider = (LifecycleProvider) mContext;
        }
        return provider;
    }

    public Observable subscribe(Observable mObservable, Observer observer) {
        if (mContext instanceof Application) {
            mObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        } else {
            mObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(getLifecycleProvider().bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribe(observer);
        }
        return mObservable;
    }
}
