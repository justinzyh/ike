package com.justinzyh.film.mvp.ui.remember;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.justinzyh.film.R;
import com.justinzyh.film.mvp.ui.remember.tabstyle.TabLayoutFragment1;
import com.justinzyh.film.mvp.ui.remember.tabstyle.TabLayoutFragment2;

import java.util.ArrayList;
import java.util.List;

public class TextRemarkActivity extends AppCompatActivity {

    private static final int TAB_COUNT = 2;
    private TabLayout            mTablayout;
    private ViewPager            mViewpager;
    private TextView             mToobarName;
    private ImageView            mTitleEdit;
    private ImageView            mImgAdd;
    private ImageView            mImgMore;
    private TextRemarkTabAdapter mAdapter;
    private List<Fragment> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_remark);

        initView();
        initToolBar();
        initTabLayout();
    }
    
    private void initTabLayout() {
        TabLayoutFragment1 tab1 = new TabLayoutFragment1();
        TabLayoutFragment2 tab2 = new TabLayoutFragment2();
        mDatas.add(tab1);
        mDatas.add(tab2);

        // 构造一个TabPagerAdapter对象
        mAdapter = new TextRemarkTabAdapter(getSupportFragmentManager(), this,mDatas);
        mViewpager.setAdapter(mAdapter);
        mTablayout.setupWithViewPager(mViewpager);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mTablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < TAB_COUNT; i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            tab.setCustomView(mAdapter.getTabView(i));
        }
    }

    private void initToolBar() {
        mToobarName.setText("笔墨纸砚");
    }

    private void initView() {
        mToobarName = (TextView) findViewById(R.id.title_toolbar_name);
        mTitleEdit = (ImageView) findViewById(R.id.title_toolbar_edit);
        mImgAdd = (ImageView) findViewById(R.id.title_toolbar_add);
        mImgMore = (ImageView) findViewById(R.id.title_toolbar_more);
        mImgMore.setVisibility(View.GONE);

        mTablayout = (TabLayout) findViewById(R.id.tabLayout_text_remark);
        mViewpager = (ViewPager) findViewById(R.id.text_remark_viewpager);
        mViewpager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(mTablayout));
    }
}
