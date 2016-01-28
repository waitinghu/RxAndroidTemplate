package com.bingfeng.rxandroid.data.local;

import rx.Observable;
import rx.Subscriber;
import rx.Observable.OnSubscribe;
import rx.schedulers.Schedulers;

import com.bingfeng.rxandroid.bean.User;

import javax.inject.Inject;

public class DatabaseHelper {

    private static DatabaseHelper helper;

    @Inject
    public DatabaseHelper() {};

    public static DatabaseHelper getInstance() {
        if (helper == null) {
            helper = new DatabaseHelper();
        }
        return helper;
    }

    public Observable<User> getUser() {
        return Observable.create(new OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                // 设置个2000ms的延迟，模拟网络访问、数据库操作等等延时操作
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                User user = null;
                if (Math.random() > 0.5) {
                    user = new User("赵日天");
                }
                if (user == null) {
                    subscriber.onError(new Exception("User = null"));
                } else {
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                }
            }

        }).subscribeOn(Schedulers.newThread());
    }

}
