package com.bingfeng.rxandroid.presenter.base;

import com.bingfeng.rxandroid.view.base.IBaseView;

public interface IPresenter<V extends IBaseView> {

    void attachView(V baseView);

    void detachView();
}