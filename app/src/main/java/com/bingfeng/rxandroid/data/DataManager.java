package com.bingfeng.rxandroid.data;

import java.io.File;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bingfeng.rxandroid.bean.User;
import com.bingfeng.rxandroid.data.local.DatabaseHelper;
import com.bingfeng.rxandroid.data.local.PreferencesHelper;
import com.bingfeng.rxandroid.event.EventPosterHelper;
import com.squareup.otto.Bus;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {
    
    private final DatabaseHelper mDataBaseHelper;
    private final EventPosterHelper mEventPoster;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(PreferencesHelper preferencesHelper,
                        DatabaseHelper databaseHelper, EventPosterHelper eventPosterHelper){
        mDataBaseHelper = databaseHelper;
        mEventPoster = eventPosterHelper;
        mPreferencesHelper = preferencesHelper;
    }
    

    public Observable<User> getUser() {
        return mDataBaseHelper.getUser();
    }
    
    
    public Observable<Bitmap> test(final String path){
         Observable.create(new OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> arg0) {
                arg0.onNext(path);
                arg0.onCompleted();
            }
        }).map(new Func1<String, Bitmap>() {
            @Override
            public Bitmap call(String path) {
                return BitmapFactory.decodeFile(path);
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Bitmap>() {

            @Override
            public void onCompleted() {
                
            }

            @Override
            public void onError(Throwable arg0) {
                
            }

            @Override
            public void onNext(Bitmap arg0) {
                
            }

        });
         return null;
    } 
    
    public Observable<Bitmap> ss(File[] folders){
        return Observable.from(folders)
        .flatMap(new Func1<File, Observable<File>>() {
            @Override
            public Observable<File> call(File file) {
                return Observable.from(file.listFiles());
            }
        })
        .filter(new Func1<File, Boolean>() {
            @Override
            public Boolean call(File file) {
                return file.getName().endsWith(".png");
            }
        })
        .map(new Func1<File, Bitmap>() {
            @Override
            public Bitmap call(File file) {
                return BitmapFactory.decodeFile(file.getPath());
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
    }
    
    public Observable<String> ss(){
        return Observable.just("images/logo.png").map(new Func1<String, Bitmap>() {
            @Override
            public Bitmap call(String arg0) {
                return BitmapFactory.decodeFile(arg0);
            }
        }).flatMap(new Func1<Bitmap, Observable<? extends String>>() {
            @Override
            public Observable<? extends String> call(Bitmap arg0) {
                return Observable.just(arg0.toString());
            }
        });
    }
}
