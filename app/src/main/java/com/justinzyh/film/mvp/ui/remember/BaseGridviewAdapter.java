package com.justinzyh.film.mvp.ui.remember;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justinzyh on 2016/11/19.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public abstract class BaseGridviewAdapter<Bean> extends BaseAdapter {

    private List<? extends Bean> mDatas = new ArrayList<>();
    private AdapterListener mListener;

    public BaseGridviewAdapter(List<Bean> mDatas,AdapterListener mListener) {
        super();
        this.mDatas = mDatas;
        this.mListener = mListener;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(getLayoutID(), null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       mListener.bindViewHolder(holder, position);
        return convertView;
    }

    public interface AdapterListener {
        void bindViewHolder(ViewHolder viewHolder, int position);

    }

    protected abstract int getLayoutID();

    public static class ViewHolder {
        View mView;

        public ViewHolder(View view) {
            this.mView = view;
        }

        // private HashMap<Integer, View> items = new HashMap<>();
        private SparseArray<View> items = new SparseArray<>();

        public View getViewById(int viewId) {
            View subView = items.get(viewId);
            if (subView == null) {
                subView = this.mView.findViewById(viewId);
                items.put(viewId, subView);
            }
            return subView;

        }
    }
}
