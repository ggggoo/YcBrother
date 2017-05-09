package com.landicorp.ycbrother;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.landicorp.ycbrother.SMS.QueryMovieTool;
import com.landicorp.ycbrother.SMS.SMSTool;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListMenu;
    private List<String> listValue = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.init("Yang")
                .methodCount(3)    // 方法栈打印的个数，默认是 2
                .hideThreadInfo(); // 隐藏线程信息，默认显示
//                .methodOffset(2);
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
                switch (i) {
                    case 0:
                        intent.setClass(MainActivity.this, com.landicorp.ycbrother.wifiborken.MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, com.landicorp.ycbrother.floatingBall.FloatingBallActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        SMSTool smsTool = new SMSTool(getBaseContext());
                        smsTool.startSMS();
                        break;
                    case 3:
                        QueryMovieTool.queryMovieReal();
                        break;
                }
//                startActivity(intent);
            }
        };
        return listener;
    }

    private void setListMenu() {
        listValue.add("1、wifi暴力破解");
        listValue.add("2、滑动悬浮球");
        listValue.add("3、短信验证码");
        listValue.add("4、查票房");
    }
}
