package com.justinzyh.film.mvp.ui.movie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.justinzyh.film.mvp.utils.ImageUtil;

/**
 * Created by justinzyh on 2016/11/14.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class GalleryMovieAdapter extends BaseAdapter {

    private int[] imageResIDs;
    private Context mContext;

    public GalleryMovieAdapter(int[] imageResIDs,Context context) {
        this.imageResIDs = imageResIDs;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;//用于循环滚动
    }

    @Override
    public Object getItem(int position) {
        if (position >= imageResIDs.length) {
            position = position % imageResIDs.length;
        }

        return position;
    }

    @Override
    public long getItemId(int position) {
        if (position >= imageResIDs.length) {
            position = position % imageResIDs.length;
        }
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView != null) {
            imageView = (ImageView) convertView;
        } else {
            imageView = new ImageView(mContext);
        }

        if (position >= imageResIDs.length) {
            position = position % imageResIDs.length;
        }

        Bitmap bitmap = ImageUtil.getImageBitmap(mContext.getResources(),
                imageResIDs[position]);
        BitmapDrawable drawable = new BitmapDrawable(bitmap);
        drawable.setAntiAlias(true); // 消除锯齿
        imageView.setImageDrawable(drawable);
        Gallery.LayoutParams params = new Gallery.LayoutParams(240, 320);
        imageView.setLayoutParams(params);
        return imageView;
    }
}
