package com.justinzyh.film.mvp.utils.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by justinzyh on 2016/11/16.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class VerticalTextView extends TextView {

    public VerticalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalTextView(Context context) {
        super(context);

    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if ("".equals(text) || text == null || text.length() == 0) {
            return;
        }
        int m = text.length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < m; i++) {
            CharSequence index = text.toString().subSequence(i, i + 1);
            sb.append(index + "\n");
        }
        super.setText(sb, type);
    }
}
