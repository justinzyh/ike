package com.justinzyh.film.mvp.ui.popupmenu;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.justinzyh.film.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyselfFragment extends PopupMenuFragment {

    private ArrayList<Map<String, Object>> mDatas;
    //图片封装成一个数组
    private int[]    l_icons    =
            {R.mipmap.ic_girl_service, R.mipmap.ic_qi_name, R.mipmap.ic_constellation,
                    R.mipmap.ic_me_joke, R.mipmap.ic_me_joke, R.mipmap.ic_zxing, R.mipmap.ic_message, R.mipmap.ic_money_book,R.mipmap.ic_phone,R.mipmap.ic_edit_pwd};
    private String[] l_iconName =
            {"头像","昵称", "星座","个性签名","滚动签名", "扫一扫","通讯录", "积分商城", "绑定手机号", "修改密码"};
    private String[] from       = {"image", "text"};
    private int[]    to         = {R.id.item_popup_image, R.id.item_popup_textview};
    private SimpleAdapter simpleAdapter;
    private ImageView mIv_popup;
    private TextView mTv_popup;
    private ListView mMyListview;
    private LinearLayout mLl_popup;
    private NestedScrollView mNestedScrollview;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        mToolbar.setEnabled(true);
    }

    protected void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_popup);

        mIv_popup = (ImageView) findViewById(R.id.item_popup_image);
        mTv_popup = (TextView) findViewById(R.id.item_popup_textview);
        mMyListview = (ListView) findViewById(R.id.listview_myself);
        mLl_popup = (LinearLayout) findViewById(R.id.ll_popup_item_listview);
        mNestedScrollview = (NestedScrollView) findViewById(R.id.nestedScrollView_popup);
    }

    protected void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < l_icons.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", l_icons[i]);
            map.put("text", l_iconName[i]);
            mDatas.add(map);
        }
        mNestedScrollview.smoothScrollTo(0,0);
        simpleAdapter = new SimpleAdapter(this, mDatas, R.layout.item_popup, from, to);
        mMyListview.setAdapter(simpleAdapter);
    }

    @Override
    int getLayoutId() {
        return R.layout.activity_myself_fragment;
    }

}
