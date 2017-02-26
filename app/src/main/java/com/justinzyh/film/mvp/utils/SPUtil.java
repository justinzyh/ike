package com.justinzyh.film.mvp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by justinzyh on 2016/11/20.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class SPUtil {

    //添加数据
    public static void putString(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("data", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("data", Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    //保存数据
    public static String getString(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences("data", context.MODE_PRIVATE);
        String value = pref.getString(key, "null");
        return value;
    }

    public static int getInt(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences("data", context.MODE_PRIVATE);
        int value = pref.getInt(key, 00 - 00 - 00);
        return value;
    }
}
