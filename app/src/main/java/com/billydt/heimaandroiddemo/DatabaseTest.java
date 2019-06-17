package com.billydt.heimaandroiddemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.security.Key;

public class DatabaseTest extends AppCompatActivity {

    private MyOpenHelper myOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_database);

        myOpenHelper = new MyOpenHelper(getApplicationContext());

//        //打开或创建数据库
//        SQLiteDatabase database = myOpenHelper.getWritableDatabase();
//        //打开或创建数据库 如果磁盘满了 返回只读的
//        SQLiteDatabase database1 = myOpenHelper.getReadableDatabase();
    }

    //以下直接执行sql语句没有返回值，不容易进行判断，可以使用Google给我们封装好的API
    //增加
    public void add(View view) {
        //获取数据库对象
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        //增加一条sql语句
        db.execSQL("insert into info(name,phone) values(?,?)", new Object[]{"DT", "13169879794"});
        db.execSQL("insert into info(name,phone) values(?,?)", new Object[]{"Billy",
                "110"});
        //数据库关不关看企业
        db.close();

        Toast.makeText(this, "增加成功!", Toast.LENGTH_SHORT).show();
    }

    //删除
    public void delete(View view) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        db.execSQL("delete from info where name = ?", new Object[]{"Billy"});
        db.close();
        Toast.makeText(this, "删除成功！", Toast.LENGTH_SHORT).show();
    }

    //更新
    public void update(View view) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        db.execSQL("update info set phone  = ? where name = ?", new Object[]{"111111", "DT"});
        db.close();
        Toast.makeText(this, "修改成功！", Toast.LENGTH_SHORT).show();
    }

    //查询
    public void find(View view) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from info", null);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "无数据！", Toast.LENGTH_SHORT).show();
        }
        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                //columnIndex从0开始
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                Toast.makeText(this, name + "   " + phone, Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }
        db.close();
    }

    //使用Google给我们封装好的API
    //增加
    public void gadd(View view) {
        //获取数据库对象
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "liu");
        values.put("phone", "222222");
        //返回值代表了插入新行的id
        long info = db.insert("info", null, values);
        db.close();
        if (info > 0) {
            Toast.makeText(this, "id=" + info + " 增加成功!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "添加失败!", Toast.LENGTH_SHORT).show();
        }
    }

    //删除
    public void gdelete(View view) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        int info = db.delete("info", "name=?", new String[]{"liu"});
        Toast.makeText(this, "删除了" + info + "行！", Toast.LENGTH_SHORT).show();

    }

    //更新
    public void gupdate(View view) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("phone", "444444");
        int info = db.update("info", values, "name=?", new String[]{"liu"});

        Toast.makeText(this, "更新了" + info + "组数据！", Toast.LENGTH_SHORT).show();
    }

    //查询
    public void gfind(View view) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        Cursor cursor = db.query("info", new String[]{"name", "phone"}, "name=?", new
                String[]{"liu"}, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                String phone = cursor.getString(1);
                Toast.makeText(this, name + "   " + phone, Toast.LENGTH_SHORT).show();

            }
            cursor.close();
        } else {
            Toast.makeText(this, "无数据！", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
