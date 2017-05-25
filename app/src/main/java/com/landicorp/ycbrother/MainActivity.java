package com.landicorp.ycbrother;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.landicorp.ycbrother.sms.QueryMovieTool;
import com.landicorp.ycbrother.sms.SMSTool;
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
                        intent.setClass(MainActivity.this, com.landicorp.ycbrother.floatingball.FloatingBallActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        SMSTool smsTool = new SMSTool(getBaseContext());
                        smsTool.startSMS();
                        break;
                    case 3:
                        QueryMovieTool.queryMovieReal(getBaseContext(), new QueryMovieTool.queryMovieListener() {
                            @Override
                            public void onResult(String result) {
                                Looper.prepare();
                                Toast.makeText(getBaseContext(),result,Toast.LENGTH_LONG).show();
                                Looper.loop();
                            }
                        });
                        break;
                    case 4:
                        intent.setClass(MainActivity.this, com.landicorp.ycbrother.mvp.v_main.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(MainActivity.this, com.landicorp.ycbrother.stepcount.StepCountActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent.setClass(MainActivity.this, com.landicorp.ycbrother.timecount.TimeCountActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent.setClass(MainActivity.this, com.landicorp.ycbrother.passwordedit.PswEditActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent.setClass(MainActivity.this, com.landicorp.ycbrother.numberrunning.NumberRunningActivity.class);
                        startActivity(intent);
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
        listValue.add("5、Android MVP");
        listValue.add("6、计步");
        listValue.add("7、倒计时");
        listValue.add("8、密码输入框");
        listValue.add("9、滚动数字");
    }
}
