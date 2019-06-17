package com.billydt.heimaandroiddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewTest extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test);
        list = findViewById(R.id.listview_test);

        ListAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 20;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view;
                if (convertView == null) {
                    view = View.inflate(ListViewTest.this, R.layout.itemtest, null);
                } else {
                    view = convertView;
                }
                return view;
            }
        };
        list.setAdapter(adapter);
    }

}
