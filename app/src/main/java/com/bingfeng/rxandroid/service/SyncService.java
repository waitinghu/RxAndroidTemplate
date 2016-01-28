package com.bingfeng.rxandroid.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;

import com.bingfeng.rxandroid.BFApplication;
import com.bingfeng.rxandroid.data.DataManager;
import com.bingfeng.rxandroid.util.AndroidComponentUtil;
import com.bingfeng.rxandroid.util.NetworkUtil;

import javax.inject.Inject;

import rx.Subscription;

public class SyncService extends Service {

    @Inject
    DataManager mDataManager;
    private Subscription mSubscription;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SyncService.class);
    }

    public static boolean isRunning(Context context) {
        return AndroidComponentUtil
                .isServiceRunning(context, SyncService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BFApplication.getApplication().getComponent().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        if (!NetworkUtil.isNetworkConnected(this)) {
            AndroidComponentUtil.toggleComponent(this,
                    SyncOnConnectionAvailable.class, true);
            stopSelf(startId);
            return START_NOT_STICKY;
        }

        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        
//        mSubscription = mDataManager.syncRibots().subscribeOn(Schedulers.io())
//                .subscribe(new Observer<Ribot>() {
//                    @Override
//                    public void onCompleted() {
//                        stopSelf(startId);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        stopSelf(startId);
//
//                    }
//
//                    @Override
//                    public void onNext(Ribot ribot) {
//                    }
//                });

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static class SyncOnConnectionAvailable extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(
                    ConnectivityManager.CONNECTIVITY_ACTION)
                    && NetworkUtil.isNetworkConnected(context)) {
                AndroidComponentUtil.toggleComponent(context, this.getClass(),false);
                context.startService(getStartIntent(context));
            }
        }
    }

}