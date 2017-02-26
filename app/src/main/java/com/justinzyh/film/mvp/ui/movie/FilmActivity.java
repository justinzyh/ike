package com.justinzyh.film.mvp.ui.movie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.justinzyh.film.R;
import com.justinzyh.film.mvp.bean.MovieGridViewBean;
import com.justinzyh.film.mvp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class FilmActivity extends AppCompatActivity
        implements  View.OnTouchListener {

    private int           screenWidth;
    private int           screenHeight;
    private WebView      mWebView;
    private LinearLayout mLayout;
    private GridView     mGridView;
    private List<MovieGridViewBean> mDatas = new ArrayList<>();
    private int[]gridViewIcons = {R.mipmap.fage, R.mipmap.fage, R.mipmap.fage,
            R.mipmap.fage, R.mipmap.fage, R.mipmap.fage};
    private GridMovieAdapter    mGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        getScreenWidthAndHeight();
        initView();
        initWebView();
        initGridview();
    }
    
    private void initGridview() {
        MovieGridViewBean bean = new MovieGridViewBean();
        for (int i = 0; i < gridViewIcons.length; i++) {
            bean.setGridviewicon(gridViewIcons[i]);
            bean.setTitle("真心英雄");
            bean.setIntroduce("真心英雄");
            bean.setLeader("真心英雄");
            bean.setDirector("真心英雄");
            bean.setCategory("真心英雄");
            bean.setScore("真心英雄");
            mDatas.add(bean);
        }
        mGridAdapter = new GridMovieAdapter(this, mDatas);
        mGridView.setAdapter(mGridAdapter);
    }
    
    private void initView() {
        mWebView = (WebView) findViewById(R.id.webview_film);
        mLayout = (LinearLayout) findViewById(R.id.ll_button_film);
        mGridView = (GridView) findViewById(R.id.gridview_film);
        mLayout.setOnTouchListener(this);
    }

    private void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //适应分辨率
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        // 支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //设置Web视图
        mWebView.setWebViewClient(new webViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl("file:///android_asset/editor.html");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ToastUtil.showToast("点击");
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void getScreenWidthAndHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

}
