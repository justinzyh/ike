package com.justinzyh.film.mvp.utils;

/**
 * Created by justinzyh on 2016/11/8.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

import android.widget.Toast;

import com.justinzyh.film.app.IkeApplication;

public class ToastUtil {
    private static Toast toast;
    /**
     * 强大的吐司，能够连续弹的吐司
     * @param text
     */
    public static void showToast(String text){
        if(toast==null){
            toast = Toast.makeText(IkeApplication.context, text,Toast.LENGTH_SHORT);
        }else {
            toast.setText(text);//如果不为空，则直接改变当前toast的文本
        }
        toast.show();
    }
}
