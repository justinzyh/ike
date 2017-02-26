package com.justinzyh.film.mvp.base.extend;

import android.os.Bundle;
import android.view.View;
import com.justinzyh.film.mvp.base.BaseFragment;
import com.justinzyh.film.mvp.base.BasePresenterImpl;
/**
 * 作者：justinzyh on 2016/11/2 10:47
 * 邮箱：tojustinzyh@163.com
 */
public abstract class MvpFragment<P extends BasePresenterImpl> extends BaseFragment {

    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
