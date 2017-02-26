package com.justinzyh.film.mvp.base;

/**
 * 作者：justinzyh on 2016/11/2 10:46
 * 邮箱：tojustinzyh@163.com
 */
public interface BaseView {

    void toast(String msg);

    void showProgress();

    void hideProgress();
}
