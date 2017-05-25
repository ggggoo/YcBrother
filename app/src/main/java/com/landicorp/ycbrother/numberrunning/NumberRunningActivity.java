package com.landicorp.ycbrother.numberrunning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.landicorp.ycbrother.R;

/**
 * Created by 杨大哥 on 2017/5/25.
 */

public class NumberRunningActivity extends AppCompatActivity {

    private NumberRunning tvNum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_running);
        tvNum = (NumberRunning) findViewById(R.id.number_running);
        tvNum.setContent("300");
    }
}
