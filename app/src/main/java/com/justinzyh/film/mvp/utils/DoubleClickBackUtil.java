package com.justinzyh.film.mvp.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * @创建者 justinzyh
 * @创建时间 2016 2016/10/27 16:37
 * @描述 ${TODO}
 * @qq 1476156127
 */

public class DoubleClickBackUtil {
    private static Activity mActivity;
    private        boolean  isOnKeyBacking;
    private static Handler  mHandler;
    private        Toast    mBackToast;

    private DoubleClickBackUtil() {
    }

    private static DoubleClickBackUtil click = null;

    //静态工厂方法
    public static DoubleClickBackUtil getInstance(Activity activity) {
        mActivity = activity;
        mHandler = new Handler(Looper.getMainLooper());
        if (click == null) {
            click = new DoubleClickBackUtil();
        }
        return click;
    }

    /**
     * Activity onKeyDown事件
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return false;
        }
        if (isOnKeyBacking) {
            mHandler.removeCallbacks(onBackTimeRunnable);
            if (mBackToast != null) {
                mBackToast.cancel();
            }
            mActivity.finish();
            return true;
        } else {
            isOnKeyBacking = true;
            if (mBackToast == null) {
                mBackToast = Toast.makeText(mActivity, "再按一次返回", Toast.LENGTH_SHORT);
            }
            mBackToast.show();
            //延迟两秒的时间，把Runable发出去
            mHandler.postDelayed(onBackTimeRunnable, 2000);
            return true;
        }
    }

    private Runnable onBackTimeRunnable = new Runnable() {

        @Override
        public void run() {
            isOnKeyBacking = false;
            if (mBackToast != null) {
                mBackToast.cancel();
            }
        }
    };
}
