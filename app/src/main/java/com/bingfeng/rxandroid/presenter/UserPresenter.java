package com.bingfeng.rxandroid.presenter;

import com.bingfeng.rxandroid.presenter.base.IPresenter;
import com.bingfeng.rxandroid.view.UserView;


public interface UserPresenter extends IPresenter<UserView>{
    void getUser();
}
