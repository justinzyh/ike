package com.justinzyh.film.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者：justinzyh on 2016/11/2 11:22
 * 邮箱：tojustinzyh@163.com
 */
public abstract class BaseFragment extends Fragment {

    static final String FRAGMENT_INDEX = "fragment_index";
    int index = 0;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            index = bundle.getInt(FRAGMENT_INDEX);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    protected abstract void initData();

    protected abstract void initViews();


}
