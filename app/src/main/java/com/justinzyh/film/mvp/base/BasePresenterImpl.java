package com.justinzyh.film.mvp.base;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者：justinzyh on 2016/11/2 10:48
 * 邮箱：tojustinzyh@163.com
 */
public class BasePresenterImpl<V> implements BasePresenter<V> {
    public V mView;

    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(V view) {
        this.mView = view;

    }

    @Override
    public void detachView() {
        this.mView = null;
        onUnsubscribe();
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
