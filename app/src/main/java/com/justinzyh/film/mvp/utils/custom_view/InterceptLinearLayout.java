package com.justinzyh.film.mvp.utils.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by justinzyh on 2016/11/21.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class InterceptLinearLayout extends LinearLayout{

    private boolean intercept = false;

    public InterceptLinearLayout(Context context) {
        super(context);
    }

    public InterceptLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptLinearLayout(Context context, AttributeSet attrs,
                                 int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setIntercept(boolean b) {
        intercept = b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (intercept)
            return true;
        return super.onInterceptTouchEvent(ev);
    }
}
