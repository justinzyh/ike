package com.justinzyh.film.mvp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by justinzyh on 2016/11/20.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class RememberDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    private static  String  TABLE_NAME= "create table remember (_id integer primary key autoincrement, title varchar(30), time varchar(20))";

    public RememberDatabaseHelper(Context context) {
        super(context, "ike.db", null, 1);
        this.mContext = context;
    }

    /**
     * 数据库第一次被创建的时候调用，如果数据库已经创建，就不会执行这一句代码
     * @param db 代表的就是我们创建出来的数据库
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL(TABLE_NAME);
    }

    /**
     * 当数据库的版本需要更新的时候调用的方法
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("alter table info add money varchar(10)");

    }
}
