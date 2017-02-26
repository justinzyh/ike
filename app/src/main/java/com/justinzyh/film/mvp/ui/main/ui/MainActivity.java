package com.justinzyh.film.mvp.ui.main.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.justinzyh.film.R;
import com.justinzyh.film.mvp.base.BaseActivity;
import com.justinzyh.film.mvp.bean.OnClickTener;
import com.justinzyh.film.mvp.bean.RightCardInfo;
import com.justinzyh.film.mvp.bean.VerticalRollingBean;
import com.justinzyh.film.mvp.ui.main.parsenter.MainParsenterImpl;
import com.justinzyh.film.mvp.ui.main.view.MainView;
import com.justinzyh.film.mvp.ui.popupmenu.AdviceFragment;
import com.justinzyh.film.mvp.ui.popupmenu.MyselfFragment;
import com.justinzyh.film.mvp.ui.popupmenu.ShareFragment;
import com.justinzyh.film.mvp.ui.popupmenu.USFragment;
import com.justinzyh.film.mvp.utils.DoubleClickBackUtil;
import com.justinzyh.film.mvp.utils.custom_view.VerticalRollingTextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity<MainParsenterImpl> implements MainView, View.OnClickListener {

    private ListView     mLeftListview;
    private RecyclerView mRightRecyclerView;
    private DrawerLayout mDrawerLayout;

    private List<Map<String, Object>> mDatas;
    private List<VerticalRollingBean> mBean;
    private SimpleAdapter             simpleAdapter;
    //图片封装成一个数组
    private int[]    l_icons    =
            {R.mipmap.ic_memory, R.mipmap.ic_music, R.mipmap.ic_local_movies,
                    R.mipmap.ic_navigation, R.mipmap.ic_wb_sunny, R.mipmap.ic_restaurant, R.mipmap.ic_setting};
    private String[] l_iconName =
            {"记忆旅行", "音乐之谷", "黑白电影", "位置导航", "天气查询", "填饱肚子", "设置"};
    private String[] from       = {"image", "text"};
    private int[]    to         = {R.id.item_left_image, R.id.item_left_textview};
    private VerticalRollingBean     bean;
    private VerticalRollingTextView mTextView;
    private ViewPager               mViewPager;
    private TabLayout               mLayout;
    private MainFragmentPageAdapter mAdapter;
    private String                  POSITION;
    private Toolbar                 mToolbar;
    private FloatingActionButton    fab;
    private LinearLayout            mItem;
    private NavigationView          mNv_left;
    private TextView                mToolT1;
    private TextView                mToolT2;
    private TextView                mToolT3;
    private ImageView               mToolMores;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, mLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mViewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initToolBarAndDrawer();
        //initMainTabLayoutData();
        initLeftData();
        initRightData();
    }

    private void initToolBarAndDrawer() {
        mDrawerLayout.setDrawerShadow(R.drawable.divider_drawlayout, GravityCompat.START);
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mDrawerLayout.setDrawerElevation(10);
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initMainTabLayoutData() {
        // 构造一个TabPagerAdapter对象
        mAdapter = new MainFragmentPageAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mAdapter);
        mLayout.setupWithViewPager(mViewPager);
        mLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < mLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mLayout.getTabAt(i);
            tab.setCustomView(mAdapter.getTabView(i));
        }
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);

        mToolT1 = (TextView) findViewById(R.id.tv_toolbar_t1);
        mToolT2 = (TextView) findViewById(R.id.tv_toolbar_t2);
        mToolT3 = (TextView) findViewById(R.id.tv_toolbar_t3);
        mToolMores = (ImageView) findViewById(R.id.iv_toolbar_mores);
        mToolT1.setOnClickListener(this);
        mToolT2.setOnClickListener(this);
        mToolT3.setOnClickListener(this);
        mToolMores.setOnClickListener(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNv_left = (NavigationView) findViewById(R.id.left_drawer);
        mTextView = (VerticalRollingTextView) findViewById(R.id.left_vertical_rolling_textview);
        mLeftListview = (ListView) findViewById(R.id.left_listview);
        mViewPager = (ViewPager) findViewById(R.id.main_tab_viewpager);
        mLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mLayout));
        mRightRecyclerView = (RecyclerView) findViewById(R.id.right_recycler_view);


    }
    
    private void initRightData() {
        List<RightCardInfo> list = new ArrayList<>();
        list.add(new RightCardInfo(R.mipmap.img_horse, "战马"));
        list.add(new RightCardInfo(R.mipmap.img_kate, "卡特教练"));
        list.add(new RightCardInfo(R.mipmap.img_happy, "当幸福来敲门"));
        list.add(new RightCardInfo(R.mipmap.img_heart, "甜心先生"));

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRightRecyclerView.setLayoutManager(manager);
        mRightRecyclerView.setItemAnimator(new DefaultItemAnimator());
        MainRightAdapter myAdapter = new MainRightAdapter(list);
        mRightRecyclerView.setAdapter(myAdapter);
    }

    private void initLeftData() {
        mBean = new ArrayList<>();
        bean = new VerticalRollingBean();
        for (int i = 0; i < 1; i++) {
            // Time
            bean.setmFront("我的梦想：");
            bean.setmBack("金钱，美女，豪车");
            bean.setmUrl("www.google.com");
            mBean.add(bean);
        }
        mTextView.setmTexts(mBean);
        mTextView.setFrontColor(Color.BLACK);
        mTextView.setBackColor(Color.BLACK);
        mTextView.setmInterval(2000);
        mTextView.setmDuration(3000);

        mTextView.setOnClickLitener(new OnClickTener() {
            @Override
            public void onClick(String mUrl) {
                final AlertDialog builder = new AlertDialog.Builder(getApplicationContext())
                        .setTitle("你最想说的话:")
                        .setIcon(R.mipmap.ic_launcher)
                        .setNegativeButton("没有想说的", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setPositiveButton("啥也不想说", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }).show();

            }
        });

        mDatas = new ArrayList<>();
        for (int i = 0; i < l_icons.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", l_icons[i]);
            map.put("text", l_iconName[i]);
            mDatas.add(map);
        }
        simpleAdapter = new SimpleAdapter(this, mDatas, R.layout.item_left, from, to);
        mLeftListview.setAdapter(simpleAdapter);
        mLeftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent0 = new Intent();
                        intent0.setAction("com.justinzyh.ike.remember");
                        startActivity(intent0);
                        break;
                    case 1:
                        break;
                    case 2:
                        Intent intent2 = new Intent();
                        intent2.setAction("com.justinzyh.ike.category");
                        startActivity(intent2);
                        break;
                    case 3:

                        break;
                    case 4:
                        Intent intent4 = new Intent();
                        intent4.setAction("com.justinzyh.ike.weather");
                        startActivity(intent4);
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    default:
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    protected MainParsenterImpl createPresenter() {
        return new MainParsenterImpl(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void SwitchItem(int itemId) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return DoubleClickBackUtil.getInstance(this).onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_toolbar_t1:
                break;
            case R.id.tv_toolbar_t2:
                break;
            case R.id.tv_toolbar_t3:
                break;
            case R.id.iv_toolbar_mores:
                showPopupMenu();
                break;
            default:
                break;
        }
    }
    
    private void showPopupMenu() {
        PopupMenu popup = new PopupMenu(this,mToolMores);
        try {
            Field field = popup.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            MenuPopupHelper mHelper = (MenuPopupHelper) field.get(popup);
            mHelper.setForceShowIcon(true);
        } catch ( NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.option_menu, popup.getMenu());
        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        Intent intent1 = new Intent(MainActivity.this, MyselfFragment.class);
                        startActivity(intent1);
                        break;
                    case R.id.menu_talk:
                        Intent intent2 = new Intent(MainActivity.this, AdviceFragment.class);
                        startActivity(intent2);
                        break;
                    case R.id.menu_share:
                        Intent intent3 = new Intent(MainActivity.this, ShareFragment.class);
                        startActivity(intent3);
                        break;
                    case R.id.menu_us:
                        Intent intent4 = new Intent(MainActivity.this, USFragment.class);
                        startActivity(intent4);
                        break;
                }
                return true;
            }
        });
        popup.show();
    }

    private void go2Fragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transition = fm.beginTransaction();
        transition.replace(R.id.drawer_layout, fragment);
        transition.commit();
    }
}

