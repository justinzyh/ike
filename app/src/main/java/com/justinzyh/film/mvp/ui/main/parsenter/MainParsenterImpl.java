package com.justinzyh.film.mvp.ui.main.parsenter;


import com.justinzyh.film.mvp.base.BasePresenterImpl;
import com.justinzyh.film.mvp.ui.main.view.MainView;

/**
 * @创建者 justinzyh
 * @创建时间 2016 2016/11/2 14:25
 * @描述 ${TODO}
 * @qq 1476156127
 */

public class MainParsenterImpl extends BasePresenterImpl<MainView> {

    public MainParsenterImpl(MainView mainView) {
        attachView(mainView);
    }

    public void switchNavigation(int id) {
        mView.SwitchItem(id);
    }
}
