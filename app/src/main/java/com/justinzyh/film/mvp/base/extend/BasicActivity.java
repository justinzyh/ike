package com.justinzyh.film.mvp.base.extend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;

/**
 * Created by justinzyh on 2016/11/19.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public abstract class BasicActivity extends AppCompatActivity {

    private PopupMenu popup = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public abstract String getTitleName();
}
