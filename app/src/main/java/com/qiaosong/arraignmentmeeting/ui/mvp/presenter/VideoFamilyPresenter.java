package com.qiaosong.arraignmentmeeting.ui.mvp.presenter;

import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.ui.activity.family.VideoFamilyActivity;
import com.qiaosong.arraignmentmeeting.ui.base.BasePresenter;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.VideoFamilyContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.model.VideoFamilyModel;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideoFamilyPresenter extends BasePresenter<VideoFamilyActivity> implements VideoFamilyContacts.IVideoFamilyPresenter {
    private VideoFamilyModel mModel;

    public VideoFamilyPresenter(VideoFamilyActivity view) {
        super(view);
        mModel = new VideoFamilyModel(mvpReference.get());
    }

    /**
     * 获取是否开始会见
     */
    @Override
    public void getIsBeginMeeting() {
        if (isViewAttach()) {
            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> emitter) throws InterruptedException {
                    while (!mModel.isBegin()) {
                        emitter.onNext("");
                        Thread.sleep(2000);
                    }
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(String s) {
                    mModel.getHttpIsBeginMeeting(new MvpDataCallBack<Boolean>() {
                        @Override
                        public void onData(Boolean data) {
                            if (isViewAttach())
                                mvpReference.get().onIsBeginVideo();
                        }
                    });
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onComplete() {
                }
            });
        }
    }
}
