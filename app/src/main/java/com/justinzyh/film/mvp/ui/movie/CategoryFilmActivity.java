package com.justinzyh.film.mvp.ui.movie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.justinzyh.film.R;
import com.justinzyh.film.mvp.bean.CategoryFilmSelectBean;
import com.justinzyh.film.mvp.bean.CategoryFilmViewBean;
import com.justinzyh.film.mvp.bean.CategoryGridViewFileBean;
import com.justinzyh.film.mvp.bean.OnItemClickListen;
import com.justinzyh.film.mvp.utils.custom_view.CustomGallery;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by justinzyh on 2016/11/14.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class CategoryFilmActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener{

    private CustomGallery mGallery;
    private int[] imageResIDs = {R.mipmap.image1, R.mipmap.image2, R.mipmap.image3,
            R.mipmap.image4, R.mipmap.image1, R.mipmap.image2,};
    private GalleryMovieAdapter mGalleryAdapter;
    private       int index    = 0;
    private final int AUTOPLAY = 2;

    //文件夹数组
    private List<CategoryGridViewFileBean> mPersonDatas;
    private String[] personArr = {"周润发", "古天乐", "周星驰", "张国荣", "李若彤", "王祖贤"};
    private int[] personResIDs = {R.mipmap.image1, R.mipmap.image2, R.mipmap.image3,
            R.mipmap.image4, R.mipmap.image1, R.mipmap.image2,};

    //地区数组
    public static final String[]                     countryArr    = {"全部", "大陆", "香港", "台湾", "美国", "英国", "法国", "泰国", "日本", "韩国", "其它"};
    //类型数组
    public static final String[]                     categoryArr   = {"全部", "电影", "电视剧", "动漫"};

    private             List<CategoryFilmViewBean>   mViewDatas   ;
    private             List<CategoryFilmSelectBean> mDatas  ;
    private List<CategoryFilmSelectBean> countryDatas = new ArrayList<>();
    private             List<CategoryFilmSelectBean> categoryDatas = new ArrayList<>();
    private CategoryFilmSelectAdapter filmCountryAdapter;
    private CategoryFilmSelectAdapter filmCategoryAdapter;
    private CategoryFilmSelectBean mFilmBean;
    private GridView mGvCountry;
    private GridView mGvCategory;
    private String mCountry;
    private GridPersonFileAdapter mPersonFileAdapter;
    private GridView mGvPersonFile;
    private CategoryGridViewFileBean mFileBean;
    private CardView mGvCardViewFilm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryfilm);
        initView();
        initGallery();
        initSelector();
        initGridview();
    }

    private void initGridview() {
        mPersonDatas = new ArrayList<>();
        mFileBean = new CategoryGridViewFileBean();
        for (int i = 0; i < personArr.length; i++) {
            System.out.println(personArr[i]+countryArr[i]);
            mFileBean.setGridViewPerson(personResIDs[i]);
            mFileBean.setGridViewName(personArr[i]);
            mFileBean.setGridViewCountry(countryArr[i]);
            mPersonDatas.add(mFileBean);
        }
        mPersonFileAdapter = new GridPersonFileAdapter(this, mPersonDatas);
        mGvPersonFile.setAdapter(mPersonFileAdapter);
    }
    private void initGallery() {
        mGalleryAdapter = new GalleryMovieAdapter(imageResIDs,this);
        mGallery.setAdapter(mGalleryAdapter);
        mGallery.setSpacing(6);//图片之间的间距
        mGallery.setSelection((Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2) % imageResIDs.length);
        mGallery.setOnItemSelectedListener(this);
        mGallery.setOnItemClickListener(this);
        Timer timer = new Timer();
        timer.schedule(task, 3000, 3000);
    }

    /**
     * 定时器，实现自动播放
     */
    private TimerTask task    = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = AUTOPLAY;
            index = mGallery.getSelectedItemPosition();
            index++;
            handler.sendMessage(message);
        }
    };
    private Handler   handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AUTOPLAY:
                    mGallery.setSelection(index);
                    break;
                default:
                    break;
            }
        }
    };
    private void initSelector() {
        mFilmBean = new CategoryFilmSelectBean();
        for (int i = 0; i < countryArr.length; i++) {
            mFilmBean.setName(countryArr[i]);
            countryDatas.add(mFilmBean);
        }
        filmCountryAdapter = new CategoryFilmSelectAdapter(countryDatas,this);
        mGvCountry.setAdapter(filmCountryAdapter);
        filmCountryAdapter.setItemClickListen(new OnItemClickListen() {
            @Override
            public void OnItemClickListen(CategoryFilmSelectBean bean, int position) {
                bean = mFilmBean;
                mCountry = bean.getName();
                bean.setStates("2");
                switch (bean.getStates()) {
                    case "0":
                        /*// 清空尺码
                        mSizeList=DataUtil.clearAdapterStates(mSizeList);
                        skuSizeAdapter.notifyDataSetChanged();
                        // 清空颜色
                        mColorList=DataUtil.clearAdapterStates(mColorList);
                        skuColorAdapter.notifyDataSetChanged();
                        color = "";
                        // 判断使用选中了尺码
                        if (!TextUtils.isEmpty(size)) {
                            // 选中尺码，计算库存
                            stock =DataUtil.getSizeAllStock(mList,size);
                            if (stock > 0) {
                                tvSkuStock.setText("库存：" + stock + "");
                            }
                            tvSkuName.setText("请选择尺码");
                            // 获取该尺码对应的颜色列表
                            List<String> list = DataUtil.getColorListBySize(mList,size);
                            if (list != null && list.size() > 0) {
                                // 更新颜色列表
                                mColorList = DataUtil.setSizeOrColorListStates(mColorList,list, color);
                                skuColorAdapter.notifyDataSetChanged();
                            }
                            mSizeList=DataUtil.setAdapterStates(mSizeList,size);
                            skuSizeAdapter.notifyDataSetChanged();
                        } else {
                            // 所有库存
                            stock = DataUtil.getAllStock(mList);
                            if (stock > 0) {
                                tvSkuStock.setText("库存：" + stock + "");
                            }
                            tvSkuName.setText("请选择尺码,颜色分类");
                        }*/
                        break;
                    case "1":

                        break;
                    default:
                        break;
                }
            }
        });

        for (int i = 0; i < categoryArr.length; i++) {
            mFilmBean.setName(categoryArr[i]);
            categoryDatas.add(mFilmBean);
        }
        filmCategoryAdapter = new CategoryFilmSelectAdapter(categoryDatas,this);
        mGvCategory.setAdapter(filmCategoryAdapter);
        filmCategoryAdapter.setItemClickListen(new OnItemClickListen() {
            @Override
            public void OnItemClickListen(CategoryFilmSelectBean bean, int position) {
                bean.setStates("2");
            }
        });
    }
    
    private void initView() {
        mGallery = (CustomGallery) findViewById(R.id.custom_gallery);
        mGvCountry = (GridView) findViewById(R.id.gv_country);
        mGvCategory = (GridView) findViewById(R.id.gv_category);
        mGvCardViewFilm = (CardView) findViewById(R.id.cardview_category_film);
        mGvPersonFile = (GridView) findViewById(R.id.gv_category_select_file);
        mGvPersonFile.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (view.getId() == R.id.cardview_category_film) {
        Intent intent = new Intent();
        intent.setAction("com.justinzyh.ike.film");
        startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
