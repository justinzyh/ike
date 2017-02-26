package com.justinzyh.film.mvp.ui.remember;

import com.justinzyh.film.R;

import java.util.List;

/**
 * Created by justinzyh on 2016/11/19.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class GridRememberAdapter extends BaseGridviewAdapter<GridRememberBean> {

    public GridRememberAdapter(List<GridRememberBean> mDatas,AdapterListener mListener) {
        super(mDatas,mListener);
    }
    @Override
    protected int getLayoutID() {
        return R.layout.item_remember_card_gridview;
    }
}
