package com.qiaosong.arraignmentmeeting.ui.mvp.presenter;

import com.qiaosong.arraignmentmeeting.AppCacheManager;
import com.qiaosong.arraignmentmeeting.callback.MvpDataCallBack;
import com.qiaosong.arraignmentmeeting.ui.activity.prisoner.VideoPrisonerActivity;
import com.qiaosong.arraignmentmeeting.ui.base.BasePresenter;
import com.qiaosong.arraignmentmeeting.ui.mvp.contacts.VideoPrisonerContacts;
import com.qiaosong.arraignmentmeeting.ui.mvp.model.VideoPrisonerModel;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideoPrisonerPresenter extends BasePresenter<VideoPrisonerActivity> implements VideoPrisonerContacts.IVideoPrisonerPresenter {
    private VideoPrisonerModel mModel;

    public VideoPrisonerPresenter(VideoPrisonerActivity view) {
        super(view);
        mModel = new VideoPrisonerModel(mvpReference.get());
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
