package com.justinzyh.film.mvp.ui.movie;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.justinzyh.film.R;
import com.justinzyh.film.mvp.bean.CategoryFilmSelectBean;
import com.justinzyh.film.mvp.bean.OnItemClickListen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justinzyh on 2016/11/14.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class CategoryFilmSelectAdapter extends BaseAdapter{

    private List<CategoryFilmSelectBean> mDatas = new ArrayList<>();
    private LayoutInflater mInflater;
    public  OnItemClickListen    itemClickListener;
    private Context mContext;

    public void setItemClickListen(OnItemClickListen itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public CategoryFilmSelectAdapter(List<CategoryFilmSelectBean> mDatas, Context context) {
        super();
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public CategoryFilmSelectBean getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_film_select_gridview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final CategoryFilmSelectBean bean = getItem(i);
        bean.setStates("2");
        switch (bean.getStates()) {
            case "0":
                holder.layout.setBackgroundResource(R.drawable.rectangle_select_shape2);
                holder.title.setTextColor(Color.WHITE);
                break;
            // 未选中
            case "1":
                holder.layout.setBackgroundResource(R.drawable.rectangle_select_shape1);
                holder.title.setTextColor(Color.BLACK);
                break;
            // 不可选
            case "2":
                holder.layout.setBackgroundResource(R.drawable.rectangle_select_shape1);
                holder.title.setTextColor(Color.parseColor("#999999"));
                break;
            default:
                break;
        }
        holder.title.setText(bean.getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    if (!bean.getStates().equals("2")) {
                        itemClickListener.OnItemClickListen(bean,i);
                    }
                }
            }
        });
        return convertView;
    }

    public final class ViewHolder {
        public View view;
        public TextView title;
        public LinearLayout layout;

        public ViewHolder(View view) {
            this.view = view;
            title = (TextView) view.findViewById(R.id.itemtext);
            layout = (LinearLayout) view.findViewById(R.id.layout);
        }
    }
}
