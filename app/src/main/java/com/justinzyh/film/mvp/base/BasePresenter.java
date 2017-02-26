package com.justinzyh.film.mvp.base;

/**
 * 作者：justinzyh on 2016/11/2 10:30
 * 邮箱：tojustinzyh@163.com
 */
public interface BasePresenter<V> {

    void attachView(V view);

    void detachView();

}
