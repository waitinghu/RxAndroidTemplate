package com.bingfeng.rxandroid.view.impl;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.bingfeng.rxandroid.R;
import com.bingfeng.rxandroid.bean.User;
import com.bingfeng.rxandroid.common.BaseActivity;
import com.bingfeng.rxandroid.presenter.UserPresenter;
import com.bingfeng.rxandroid.presenter.impl.UserPresenterImpl;
import com.bingfeng.rxandroid.view.UserView;

public class UserAcitvity extends BaseActivity implements UserView ,OnClickListener{

    @Bind(R.id.textview)
    TextView mTvShow;
    
    @Bind(R.id.button)
    Button mBtnUpdate;
    
    ProgressDialog mProgressDialog;

    private UserPresenter mUserPresenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        
        mUserPresenter = new UserPresenterImpl(this);
        
        mProgressDialog = new ProgressDialog(this);
        mBtnUpdate.setOnClickListener(this);
    }

    @Override
    public void updateView(User user) {
        mTvShow.setText(user.getName());
    }

    @Override
    public void showProgressDialog() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        mUserPresenter.getUser();
    }

}
