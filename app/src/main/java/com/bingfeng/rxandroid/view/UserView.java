package com.bingfeng.rxandroid.view;

import com.bingfeng.rxandroid.bean.User;
import com.bingfeng.rxandroid.view.base.IBaseView;

public interface UserView extends IBaseView {

    void updateView(User user);

    void showProgressDialog();

    void hideProgressDialog();

    void showError(String msg);
}
