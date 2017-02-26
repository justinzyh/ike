package com.justinzyh.film.mvp.ui.main.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.justinzyh.film.R;
import com.justinzyh.film.mvp.bean.OnItemClick;
import com.justinzyh.film.mvp.bean.RightCardInfo;

import java.util.List;

/**
 * Created by justinzyh on 2016/11/7.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class MainRightAdapter extends RecyclerView.Adapter<MainRightAdapter.MyViewHolder> {

    private List<RightCardInfo> list;

    public MainRightAdapter(List<RightCardInfo> list) {
        this.list = list;
    }

    @Override
    public MainRightAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_right, parent, false);
        return new MainRightAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainRightAdapter.MyViewHolder holder, int i) {
        holder.iv_backgroud.setBackgroundResource(list.get(i).getImageId());
        holder.tv_title.setText(list.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private View      itemView;
        private ImageView iv_backgroud;
        private TextView  tv_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv_backgroud = (ImageView) itemView.findViewById(R.id.picture);
            tv_title = (TextView) itemView.findViewById(R.id.name);
        }
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}

