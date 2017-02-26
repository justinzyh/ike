package com.justinzyh.film.mvp.ui.popupmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.justinzyh.film.R;

/**
 * Created by justinzyh on 2016/11/23.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class USFragment extends PopupMenuFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    int getLayoutId() {
        return R.layout.activity_us_fragment;
    }

    public void initWebView() {
        // WebView webView = (WebView) view.findViewById(R.id.webview_us);
        // WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        //webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        // webSettings.setAllowFileAccess(true);
        //加载需要显示的网页
        // webView.loadUrl("file:///android_asset/us.html ");
    }
}
