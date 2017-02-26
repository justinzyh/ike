package com.justinzyh.film.mvp.ui.main.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.justinzyh.film.R;

/**
 * Created by justinzyh on 2016/11/7.
 * 添加自定义icon加text的TabLayout布局
 */

public class MainFragmentPageAdapter extends FragmentPagerAdapter {

    final   int      PAGE_COUNT    = 8;
    final   int      POSITION_MAIN = 1;
    private String[] tabTitles     = {"t1", "t2", "t3", "t4", "t5", "t6", "t7", "t8"};
    private int[]    imageResId    = {R.mipmap.ic_music, R.mipmap.ic_music,
            R.mipmap.ic_music, R.mipmap.ic_music,
            R.mipmap.ic_music, R.mipmap.ic_music,
            R.mipmap.ic_music, R.mipmap.ic_music};
    private Context mContext;
    private View    mView;

    public MainFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return TestTabLayoutFragment.newInstance(position + POSITION_MAIN);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    public View getTabView(int position) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_tablayout, null);
        ViewHolder holder = new ViewHolder(mView);

        holder.tv.setText(tabTitles[position]);
        holder.iv.setImageResource(imageResId[position]);
        return mView;
    }

    static class ViewHolder {
        View      mView;
        TextView  tv;
        ImageView iv;

        public ViewHolder(View mView) {
            this.mView = mView;
            tv = (TextView) mView.findViewById(R.id.tv_title_tab);
            iv = (ImageView) mView.findViewById(R.id.iv_icon_tab);
        }
    }
}
