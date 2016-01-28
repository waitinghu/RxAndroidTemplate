package com.bingfeng.rxandroid;

import android.app.Application;

import com.bingfeng.rxandroid.injection.component.ApplicationComponent;
import com.bingfeng.rxandroid.injection.component.DaggerApplicationComponent;
import com.bingfeng.rxandroid.injection.module.ApplicationModule;

public class BFApplication extends Application {

    ApplicationComponent mApplicationComponent;
    private static BFApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static BFApplication getApplication(){
        return mApplication;
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

}
