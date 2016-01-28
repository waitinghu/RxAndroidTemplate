package com.bingfeng.rxandroid.injection.component;

import com.bingfeng.rxandroid.injection.PerActivity;
import com.bingfeng.rxandroid.injection.module.ActivityModule;
import com.bingfeng.rxandroid.view.impl.UserAcitvity;

import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(UserAcitvity mainActivity);

}
