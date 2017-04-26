package com.landicorp.ycbrother;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListMenu;
    private List<String> listValue = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mListMenu = (ListView) findViewById(R.id.main_list);
        setListMenu();
        ListAdapter mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listValue);
        mListMenu.setAdapter(mAdapter);
        mListMenu.setOnItemClickListener(createItemClickListener());
    }

    private AdapterView.OnItemClickListener createItemClickListener() {
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                 switch (i){
                     case 0:
                        intent.setClass(MainActivity.this, com.landicorp.ycbrother.wifiborken.MainActivity.class);
                         break;
                 }
                startActivity(intent);
            }
        };
        return listener;
    }

    private void setListMenu() {
        listValue.add("1、wifi暴力破解");
    }
}
