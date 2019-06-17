package com.billydt.heimaandroiddemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListShowDatabase extends AppCompatActivity {

    ListView listView;
    List<Person> personList;
    private MyOpenHelper myOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_show_database);

        myOpenHelper = new MyOpenHelper(getApplicationContext());
        listView = findViewById(R.id.data_list);
        personList = new ArrayList<>();

    }

    public void show(View view) {

        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        Cursor cursor = db.query("info", new String[]{"name", "phone"}, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                String phone = cursor.getString(1);
                Person person = new Person();
                person.setName(name);
                person.setPhone(phone);
                personList.add(person);
            }
            cursor.close();
        }

        listView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return personList.size();
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
                view = View.inflate(ListShowDatabase.this, R.layout.databaseitem, null);
            } else {
                view = convertView;
            }

            TextView name = view.findViewById(R.id.name);
            TextView phone = view.findViewById(R.id.phone);

            name.setText(personList.get(position).getName());
            phone.setText(personList.get(position).getPhone());

            return view;
        }
    }

    class Person {
        private String name;
        private String phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
