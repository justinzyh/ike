package com.justinzyh.film.mvp.ui.main.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justinzyh.film.R;
import com.justinzyh.film.mvp.base.BaseFragment;

/**
 * Created by justinzyh on 2016/11/7.
 */

public  class TestTabLayoutFragment extends BaseFragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int  mPage;
    private View mView;
    private int mLayoutId;

    public static TestTabLayoutFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        TestTabLayoutFragment pageFragment = new TestTabLayoutFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    public int getLayoutId() {
        switch (mPage) {
            case 1:
                mLayoutId = R.layout.test;
                break;
            case 2:
                mLayoutId = R.layout.test;
                break;
            case 3:
                mLayoutId = R.layout.test;
                break;
            case 4:
                mLayoutId = R.layout.test;
                break;
            case 5:
                mLayoutId = R.layout.test;
                break;
            case 6:
                mLayoutId = R.layout.test;
                break;
            case 7:
                mLayoutId = R.layout.test;
                break;
            case 8:
                mLayoutId = R.layout.test;
                break;
            case 11:
                mLayoutId = R.layout.test;
                break;
            case 12:
                mLayoutId = R.layout.test;
                break;
        }
        return mLayoutId;
    }
}