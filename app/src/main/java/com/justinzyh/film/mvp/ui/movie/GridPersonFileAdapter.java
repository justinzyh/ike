package com.justinzyh.film.mvp.ui.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.justinzyh.film.R;
import com.justinzyh.film.mvp.bean.CategoryGridViewFileBean;

import java.util.List;

/**
 * Created by justinzyh on 2016/11/16.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class GridPersonFileAdapter extends BaseAdapter{

    private List<CategoryGridViewFileBean> mDatas ;
    private Context mContext;

    public GridPersonFileAdapter(Context context, List<CategoryGridViewFileBean> mDatas) {
        super();
        this.mContext = context;
        this.mDatas = mDatas;
    }

    @Override

    public int getCount() {
        return mDatas.size();
    }

    @Override
    public CategoryGridViewFileBean getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_film_gridview_person, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        CategoryGridViewFileBean bean = getItem(i);
        holder.gridViewPerson.setImageResource(bean.getGridViewPerson());
        holder.gridViewName.setText(bean.getGridViewName());
        holder.gridViewCountry.setText(bean.getGridViewCountry());

        return convertView;
    }

    final class ViewHolder {
        View      view;
        ImageView gridViewPerson;
        TextView  gridViewName;
        TextView  gridViewCountry;

        public ViewHolder(View view) {
            this.view = view;
            gridViewPerson = (ImageView) view.findViewById(R.id.pic);
            gridViewName = (TextView) view.findViewById(R.id.name);
            gridViewCountry = (TextView) view.findViewById(R.id.country);
        }
    }
}
