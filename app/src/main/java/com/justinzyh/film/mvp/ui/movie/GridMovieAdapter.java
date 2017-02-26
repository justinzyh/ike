package com.justinzyh.film.mvp.ui.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.justinzyh.film.R;
import com.justinzyh.film.mvp.bean.MovieGridViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justinzyh on 2016/11/14.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class GridMovieAdapter extends BaseAdapter {

    private List<MovieGridViewBean> mDatas = new ArrayList<>();
    private Context mContext;

    public GridMovieAdapter(Context context, List<MovieGridViewBean> mDatas) {
        super();
        this.mContext = context;
        this.mDatas = mDatas;
    }

    @Override

    public int getCount() {
        return mDatas.size();
    }

    @Override
    public MovieGridViewBean getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_film_gridview, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        MovieGridViewBean bean = mDatas.get(i);
        holder.gridviewicon.setImageResource(bean.getGridviewicon());
        holder.title.setText(bean.getTitle());
        holder.introduce.setText(bean.getTitle());
        holder.leader.setText(bean.getIntroduce());
        holder.director.setText(bean.getLeader());
        holder.category.setText(bean.getDirector());
        holder.score.setText(bean.getCategory());

        return convertView;
    }

    class ViewHolder {
        View      view;
        ImageView gridviewicon;
        TextView  title;
        TextView  introduce;
        TextView  leader;
        TextView  director;
        TextView  category;
        TextView  score;

        public ViewHolder(View view) {
            this.view = view;
            gridviewicon = (ImageView) view.findViewById(R.id.iv_film_gridview);
            title = (TextView) view.findViewById(R.id.tv_film_title);
            introduce = (TextView) view.findViewById(R.id.tv_film_introduce);
            leader = (TextView) view.findViewById(R.id.tv_film_leader);
            director = (TextView) view.findViewById(R.id.tv_film_director);
            category = (TextView) view.findViewById(R.id.tv_film_category);
            score = (TextView) view.findViewById(R.id.tv_film_score);
        }
    }
}
