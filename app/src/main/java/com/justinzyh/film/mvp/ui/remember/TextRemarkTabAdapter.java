package com.justinzyh.film.mvp.ui.remember;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.justinzyh.film.R;

import java.util.List;

/**
 * Created by justinzyh on 2016/11/21.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */
public class TextRemarkTabAdapter extends FragmentPagerAdapter {

    private Context mContext;
    final int PAGE_COUNT = 2;
    final int POSITION_TEXTREMARK = 11;
    private String[] tabTitles = {"随笔小记", "快乐游记"};
    private int[] imageResId = {R.mipmap.ic_edit_36dp, R.mipmap.ic_edit_book_36dp};
    private View mView;
    private List<Fragment> mdatas;

    public TextRemarkTabAdapter(FragmentManager fm, Context mContext,List<Fragment> mdatas) {
        super(fm);
        this.mContext = mContext;
        this.mdatas =mdatas;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return mdatas.get(position);
    }

    public View getTabView(int position) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_tablayout, null);
        ViewHolder holder = new ViewHolder(mView);

        holder.tv.setText(tabTitles[position]);
        holder.iv.setImageResource(imageResId[position]);
        return mView;
    }

    static class ViewHolder {
        View mView;
        TextView  tv;
        ImageView iv;
        public ViewHolder(View mView){
            this.mView = mView;
           tv = (TextView) mView.findViewById(R.id.tv_title_tab);
           iv = (ImageView) mView.findViewById(R.id.iv_icon_tab);
        }
    }
}
