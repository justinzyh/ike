package com.justinzyh.film.mvp.ui.remember.tabstyle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justinzyh.film.R;

/**
 * Created by justinzyh on 2016/11/23.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class TabLayoutFragment2 extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_textremark_tablayout2, null);
        return view;
    }
}
