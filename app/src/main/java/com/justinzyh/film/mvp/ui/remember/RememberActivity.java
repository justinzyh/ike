package com.justinzyh.film.mvp.ui.remember;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.justinzyh.film.R;
import com.justinzyh.film.mvp.base.extend.BasicActivity;

import java.util.ArrayList;
import java.util.List;


public class RememberActivity extends BasicActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    //获取图片
    private static final int    REQUEST_CODE_PICK_IMAGE     = 110;
    private static final int    REQUEST_CODE_CAPTURE_CAMEIA = 111;
    private              String capturePath                 = null;

    private ImageView           mBackBtn;
    private TextView            mToobarName;
    private ImageView           mTitleEdit;
    private ImageView           mImgMore;
    private ImageView           mImgAdd;
    private GridView            mGv_remember;
    private GridRememberAdapter mAdapter;
    private GridRememberBean mBean = new GridRememberBean();
    private List<GridRememberBean> mList = new ArrayList<>();
    private ImageView mIv_remGrid;
    private TextView  mEt_titRem;
    private TextView  mEt_markRem;
    private ImageView mIvDelete;
    private TextView mTvTitle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember);

        initView();
        initToolBar();
        initGridView();
    }

    private void initToolBar() {
        mToobarName.setText(getTitleName());
    }

    private void initGridView() {
        mAdapter = new GridRememberAdapter(mList, new BaseGridviewAdapter.AdapterListener() {
            @Override
            public void bindViewHolder(BaseGridviewAdapter.ViewHolder holder, int position) {
                mBean = mList.get(position);
                mIv_remGrid = (ImageView) holder.getViewById(R.id.iv_remember_gridview);
                mEt_titRem = (TextView) holder.getViewById(R.id.et_title_remember);
                mEt_markRem = (TextView) holder.getViewById(R.id.et_remark_remember);

                for (int i = 0; i < mList.size(); i++) {
                    mIv_remGrid.setImageResource(mBean.getRememberImg());
                    mEt_titRem.setText(mBean.getRememberTitle());
                    mEt_markRem.setText(mBean.getRememberremark());
                }
            }
        });
        mGv_remember.setAdapter(mAdapter);

    }

    private void initView() {
        mBackBtn = (ImageView) findViewById(R.id.img_back_toolbar);
        mToobarName = (TextView) findViewById(R.id.title_toolbar_name);
        mTitleEdit = (ImageView) findViewById(R.id.title_toolbar_edit);
        mImgAdd = (ImageView) findViewById(R.id.title_toolbar_add);

        mIvDelete = (ImageView) findViewById(R.id.iv_del);
        mIvDelete.setVisibility(View.INVISIBLE);
        mTvTitle = (TextView) findViewById(R.id.et_title_remember);

        mGv_remember = (GridView) findViewById(R.id.gv_remember);
        mImgMore = (ImageView) findViewById(R.id.title_toolbar_more);
        mGv_remember.setOnItemClickListener(this);
        mImgMore.setVisibility(View.GONE);
        mImgAdd.setOnClickListener(this);
        mTitleEdit.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
        mTvTitle.setOnClickListener(this);
        mIvDelete.setOnClickListener(this);
    }

    @Override
    public String getTitleName() {
        return "文件仓库";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back_toolbar:
                finish();
                break;
            case R.id.title_toolbar_add:
                doAdd();
                break;
            case R.id.title_toolbar_edit:
                doCompile();
                break;
            case R.id.iv_del:
                doDelete();
                break;
            case R.id.et_title_remember:
                doDialog();
                break;
        }
    }
    protected void doCompile() {

    }

    protected void doAdd() {
        Intent intent = new Intent(this,TextRemarkActivity.class);
        startActivity(intent);
    }

    private void doDialog() {

    }

    private void doDelete() {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

}




