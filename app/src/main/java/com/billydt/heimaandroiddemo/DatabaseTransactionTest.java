package com.billydt.heimaandroiddemo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DatabaseTransactionTest extends AppCompatActivity {

    private MyOpenHelper myOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_transaction_test);
        myOpenHelper = new MyOpenHelper(this);
        myOpenHelper.getWritableDatabase();

    }

    public void transaction(View view) {
        //使用事务进行转账
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        //开启事务
        db.beginTransaction();
        try {

            db.execSQL("update account set money = money-100 where name=?", new String[]{"DT"});
            db.execSQL("update account set money = money+100 where name=?", new String[]{"AC"});

            db.setTransactionSuccessful();

            Toast.makeText(this, "转账成功！", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(this, "服务器繁忙！", Toast.LENGTH_SHORT).show();
        } finally {
            db.endTransaction();
        }

    }
}
