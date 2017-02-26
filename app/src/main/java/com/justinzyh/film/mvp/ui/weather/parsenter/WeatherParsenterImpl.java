package com.justinzyh.film.mvp.ui.weather.parsenter;

import com.justinzyh.film.mvp.base.BasePresenterImpl;
import com.justinzyh.film.mvp.ui.weather.view.WeatherView;

/**
 * Created by justinzyh on 2016/11/7.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class WeatherParsenterImpl extends BasePresenterImpl<WeatherView> {

    public WeatherParsenterImpl(WeatherView weatherView) {
        attachView(weatherView);
    }
}
