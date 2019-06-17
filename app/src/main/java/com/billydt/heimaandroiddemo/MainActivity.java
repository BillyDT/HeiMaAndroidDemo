package com.billydt.heimaandroiddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView list;
    String[] arr = {
            "0.数据库增删改查",
            "1.数据库事务——银行转账",
            "2.ListView入门",
            "3.ListView展示数据库中的数据",
    };
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.main_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.main_list_items, arr);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, DatabaseTest.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, DatabaseTransactionTest.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, ListViewTest.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, ListShowDatabase.class);
                        startActivity(intent);
                        break;
                    default:
                }
            }
        });
    }
}
