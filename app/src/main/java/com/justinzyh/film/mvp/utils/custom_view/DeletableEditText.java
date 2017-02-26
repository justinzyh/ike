package com.justinzyh.film.mvp.utils.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

/**
 * Created by justinzyh on 2016/11/21.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 * 处理软键盘回删按钮backSpace时回调OnKeyListener
 */

public class DeletableEditText extends EditText{

    public DeletableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public DeletableEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DeletableEditText(Context context) {
        this(context,null);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new DeleteInputConnection(
                super.onCreateInputConnection(outAttrs), true);
    }

    private class DeleteInputConnection extends InputConnectionWrapper {

        public DeleteInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            return super.sendKeyEvent(event);
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            if (beforeLength == 1 && afterLength == 0) {
                return sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DEL))
                        && sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DEL));
            }

            return super.deleteSurroundingText(beforeLength, afterLength);
        }

    }
}
