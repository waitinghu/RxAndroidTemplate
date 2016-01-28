package com.bingfeng.rxandroid.presenter.impl;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.bingfeng.rxandroid.bean.User;
import com.bingfeng.rxandroid.data.DataManager;
import com.bingfeng.rxandroid.presenter.UserPresenter;
import com.bingfeng.rxandroid.presenter.base.BasePresenter;
import com.bingfeng.rxandroid.view.UserView;

import javax.inject.Inject;

public class UserPresenterImpl extends BasePresenter<UserView> implements UserPresenter {

    @Inject DataManager mDataManager;
    private Subscription mSubscription;
    
    public UserPresenterImpl(UserView view) {
        this.attachView(view);
    }
    
    @Override
    public void attachView(UserView mvpView) {
        this.mMvpView = mvpView;
    }
    
    @Override
    public void detachView() {
        this.mMvpView = null;
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    @Override
    public void getUser() {
        getMvpView().showProgressDialog();
        mSubscription = mDataManager.getUser()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<User>() {

            @Override
            public void onCompleted() {
                getMvpView().hideProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getMvpView().showError(e.getMessage());
                getMvpView().hideProgressDialog();
            }

            @Override
            public void onNext(User user) {
                getMvpView().updateView(user);
            }

        });
    }

}
