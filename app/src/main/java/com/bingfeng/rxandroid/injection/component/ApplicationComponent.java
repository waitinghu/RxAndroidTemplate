package com.bingfeng.rxandroid.injection.component;

import javax.inject.Singleton;

import android.app.Application;
import android.content.Context;

import com.bingfeng.rxandroid.data.DataManager;
import com.bingfeng.rxandroid.data.local.DatabaseHelper;
import com.bingfeng.rxandroid.data.local.PreferencesHelper;
import com.bingfeng.rxandroid.injection.ApplicationContext;
import com.bingfeng.rxandroid.injection.module.ApplicationModule;
import com.bingfeng.rxandroid.service.SyncService;
import com.squareup.otto.Bus;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);
    @ApplicationContext Context context();
    Application application();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    Bus eventBus();

}
