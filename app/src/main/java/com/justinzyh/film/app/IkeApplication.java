package com.justinzyh.film.app;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 作者：justinzyh on 2016/11/2 10:43
 * 邮箱：tojustinzyh@163.com
 */
public class IkeApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        ImageLoader.getInstance().init(configuration);
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext

    }}
