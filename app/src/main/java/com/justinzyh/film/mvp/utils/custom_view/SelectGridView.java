package com.justinzyh.film.mvp.utils.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by justinzyh on 2016/11/14.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class SelectGridView extends GridView {

    public SelectGridView(Context context) {
        this(context,null);
    }

    public SelectGridView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SelectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
