package com.billydt.heimaandroiddemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyOpenHelper extends SQLiteOpenHelper {

    /**
     * @param context 上下文对象
     *                name 数据库名字
     *                factory  cursor对象 结果集
     *                version  版本号
     */
    public MyOpenHelper(Context context) {
        super(context, "billy.db", null, 3);
    }


    //第一次创建 第一次创建 第一次创建 数据库的时候执行
    //适合作表结构的初始化  sql语句
    @Override
    public void onCreate(SQLiteDatabase db) {
        //id 一般以 _ 开头
        db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20)," +
                "phone varchar(20))");
        db.execSQL("create table account(_id integer primary key autoincrement,name varchar(20)," +
                "phone varchar(20),money varchar(20))");
        db.execSQL("insert into account ('name','phone','money') values ('DT','138888','2000')");
        db.execSQL("insert into account ('name','phone','money') values ('AC','139999','5000')");
    }

    //数据库版本升级调用
    //适合作表结构的更新
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table info add phone varchar(20)");
    }
}
