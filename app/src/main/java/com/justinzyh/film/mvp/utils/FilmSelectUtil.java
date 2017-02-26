package com.justinzyh.film.mvp.utils;

import android.text.TextUtils;

import com.justinzyh.film.mvp.bean.CategoryFilmSelectBean;
import com.justinzyh.film.mvp.bean.CategoryFilmViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justinzyh on 2016/11/15.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class FilmSelectUtil {

    /**
     * 获取所有数据
     *
     * @return
     */
    public static int getSumFilm(List<CategoryFilmViewBean> mData) {
        int data = 0;
        for (CategoryFilmViewBean item : mData) {
            data += item.getAllFilm();
        }
        return data;
    }

    /**
     * 获取库存
     *
     * @param colorStr
     * @param sizeStr
     * @return
     */
    public static int getDataByCountAndCate(List<CategoryFilmViewBean> mData, String colorStr, String sizeStr) {
        int data = 0;
        for (CategoryFilmViewBean item : mData) {
            String country = item.getFilmCountry();
            String category = item.getFilmCategory();

            if (country.equals(colorStr) && category.equals(sizeStr)) {
                data = item.getAllFilm();
                break;
            }
        }
        return data;
    }


    /**
     * 清空状态
     */
    public static List<CategoryFilmSelectBean> clearAdapterStates(List<CategoryFilmSelectBean> mData) {
        int size = mData.size();
        for (int i = 0; i < size; i++) {
            CategoryFilmSelectBean bean = mData.get(i);
            bean.setStates("1");
            mData.set(i, bean);
        }
        return mData;
    }


    /**
     * 设置选中状态
     */
    public static List<CategoryFilmSelectBean> setAdapterStates(List<CategoryFilmSelectBean> mData, String key) {
        int size = mData.size();
        for (int i = 0; i < size; i++) {
            CategoryFilmSelectBean bean = mData.get(i);
            String str = bean.getName();
            if (str.equals(key)) {
                bean.setStates("0");
            } else {
                bean.setStates("1");
            }
            mData.set(i, bean);
        }
        return mData;
    }

    /**
     * 获取该颜色的所有库存
     *
     * @param str
     * @return
     */
    public static int getCountryAllStock(List<CategoryFilmViewBean> mData, String str) {
        int data = 0;
        for (CategoryFilmViewBean item : mData) {
            String color = item.getFilmCountry();
            if (color.equals(str)) {
                data += item.getAllFilm();
                break;
            }
        }
        return data;
    }

    /**
     * 获取该尺码的所有库存
     *
     * @param categoryStr
     * @return
     */
    public static int getCategoryAllStock(List<CategoryFilmViewBean> mData, String categoryStr) {
        int data = 0;
        for (CategoryFilmViewBean item : mData) {
            String category = item.getFilmCategory();
            if (category.equals(categoryStr)) {
                data += item.getAllFilm();
            }
        }
        return data;
    }

    /**
     * 更新适配器状态
     *
     * @param states
     */
    public static List<CategoryFilmSelectBean> updateAdapterStates(List<CategoryFilmSelectBean> mData, String states, int postion) {
        int size = mData.size();
        for (int i = 0; i < size; i++) {
            CategoryFilmSelectBean bean = mData.get(i);
            if (i == postion) {
                bean.setStates(states);
            } else {
                if (!bean.getStates().equals("2")) {
                    bean.setStates("1");
                }
            }
            mData.set(i, bean);
        }
        return mData;
    }

    /**
     * 点击地区后，获取该地区对应的所有类型列表
     *
     * @param countryStr
     */
    public static List<String> getSizeListByColor(List<CategoryFilmViewBean> mData, String countryStr) {
        List<String> list = null;
        if (!TextUtils.isEmpty(countryStr)) {
            list = new ArrayList<String>();
            for (CategoryFilmViewBean item : mData) {
                String country = item.getFilmCountry();
                String category = item.getFilmCategory();
                if (country.equals(countryStr)) {
                    list.add(category);
                }
            }
        }
        return list;
    }

    /**
     * 点击类型后，获取该类型对应的所有地区列表
     *
     * @param categoryStr
     */
    public static List<String> getColorListBySize(List<CategoryFilmViewBean> mData, String categoryStr) {
        List<String> list = null;
        list = new ArrayList<String>();
        for (CategoryFilmViewBean item : mData) {
            String country = item.getFilmCountry();
            String category = item.getFilmCategory();
            if (category.equals(categoryStr)) {
                list.add(country);
            }
        }
        return list;
    }

    /**
     * @param mData 类型列表/地区列表
     * @param list  该地区对应的类型列表/地区列表
     * @param key   之前选中的类型/地区
     * @return
     */
    public static List<CategoryFilmSelectBean> setSizeOrColorListStates(List<CategoryFilmSelectBean> mData, List<String> list, String key) {
        int size = mData.size();
        List<CategoryFilmSelectBean> list2 = new ArrayList<CategoryFilmSelectBean>();
        for (int i = 0; i < size; i++) {
            CategoryFilmSelectBean bean = mData.get(i);
            String name = bean.getName();
            for (String str : list) {
                if (name.equals(str)) {
                    bean.setStates("1");
                    if (key.equals(str)) {
                        bean.setStates("0");
                    }
                    break;
                } else {
                    bean.setStates("2");
                }
            }
            list2.add(bean);
        }
        return list2;
    }

}
