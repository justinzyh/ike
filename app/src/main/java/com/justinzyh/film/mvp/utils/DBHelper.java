package com.justinzyh.film.mvp.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.justinzyh.film.app.IkeApplication;
import com.justinzyh.film.mvp.db.RememberDatabaseHelper;

import java.util.HashMap;

/**
 * Created by justinzyh on 2016/11/23.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class DBHelper {

    private HashMap<String, Object> map = new HashMap<>();

    private void addData2Sqlite(String title, String time) {
        SQLiteOpenHelper mHelper = new RememberDatabaseHelper(IkeApplication.context);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        ContentValues content = new ContentValues();
        content.put("title", title);
        content.put("time", time);
        db.insert("remember", null, content);
        content.clear();
    }

    private HashMap<String, Object> getData2sqlite() {
        SQLiteOpenHelper mHelper = new RememberDatabaseHelper(IkeApplication.context);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query("remember", null, null, null, null, null, null);
        String title = null;
        String time = null;
        if (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            title = cursor.getString(cursor.getColumnIndex("title"));
            time = cursor.getString(cursor.getColumnIndex("time"));
            if (title == null || time == null) {
                return null;
            } else {
                map.put("_id", id);
                map.put("title", title);
                map.put("time", time);
            }
        }
        cursor.close();
        return map;
    }
}
