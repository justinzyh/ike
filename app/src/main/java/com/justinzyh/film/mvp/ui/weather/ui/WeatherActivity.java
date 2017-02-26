package com.justinzyh.film.mvp.ui.weather.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.justinzyh.film.R;
import com.justinzyh.film.mvp.base.BaseActivity;
import com.justinzyh.film.mvp.ui.weather.parsenter.WeatherParsenterImpl;
import com.justinzyh.film.mvp.ui.weather.view.WeatherView;
import com.justinzyh.film.mvp.utils.ToastUtil;

/**
 * Created by justinzyh on 2016/11/7.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class WeatherActivity extends BaseActivity<WeatherParsenterImpl> implements View.OnClickListener, WeatherView {

    private ImageView    mImageWea;
    private Button       mBtWea;
    private Button       mBtCityWea;
    private EditText     mEtInputCityName;
    private PopupWindow  mPopupWindow;
    private View         mContentView;
    private LinearLayout mTv2;
    private LinearLayout mTv1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();

    }

    private void initView() {
        mImageWea = (ImageView) findViewById(R.id.img_weather_back_toolbar);
        mBtWea = (Button) findViewById(R.id.bt_weather_check);
        mBtCityWea = (Button) findViewById(R.id.tv_weather_city);
        mEtInputCityName = (EditText) findViewById(R.id.et_search_city);
        mImageWea.setOnClickListener(this);
        mBtWea.setOnClickListener(this);
        mBtCityWea.setOnClickListener(this);

    }

    @Override
    protected WeatherParsenterImpl createPresenter() {
        return null;
    }

    @Override
    public void getDataSuccess() {

    }

    @Override
    public void getDataFailer(String msg) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_weather_back_toolbar:
                finish();
                break;
            case R.id.bt_weather_check:
                //TODO
                break;
            case R.id.tv_weather_city:
                showPopupWindow();
                break;
            case R.id.ll_popup_item1:
                //TODO
                ToastUtil.showToast("讯飞语音");
                mPopupWindow.dismiss();
                break;
            case R.id.ll_popup_item2:
                showAlertDialog();
                mPopupWindow.dismiss();
                break;

        }
    }
    
    private void showAlertDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        Window window = dialog.getWindow();
   //     window.setContentView(R.layout.wea_dialog_city_picker);
//        cityPicker = (CityPicker) findViewById(R.id.citypicker);

    }
    
    private void showPopupWindow() {
        //设置contentView
        mContentView = LayoutInflater.from(this).inflate(R.layout.wea_popupwin, null);
        mPopupWindow = new PopupWindow(mContentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(mContentView);
        //设置各个控件的点击
        mTv1 = (LinearLayout) mContentView.findViewById(R.id.ll_popup_item1);
        mTv2 = (LinearLayout) mContentView.findViewById(R.id.ll_popup_item2);
        mTv1.setOnClickListener(this);
        mTv2.setOnClickListener(this);
        //显示PopupWindow
        mPopupWindow.showAsDropDown(mBtCityWea);
    }
}



























