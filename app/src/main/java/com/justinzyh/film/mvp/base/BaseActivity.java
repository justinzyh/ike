package com.justinzyh.film.mvp.base;

import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者：justinzyh on 2016/11/2 13:21
 * 邮箱：tojustinzyh@163.com
 */
public abstract class BaseActivity<P extends BasePresenterImpl> extends AppCompatActivity {

    public final static String TAG = BaseActivity.class.getCanonicalName();
    private   CompositeSubscription mCompositeSubscription;
    protected P                     Presente;
    private SlidingPaneLayout slidingPaneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Presente = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
        if (Presente != null) {
            Presente.detachView();
        }
    }


    public void onUnsubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscription) {
        mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(subscription);
    }

    protected abstract P createPresenter();

}
