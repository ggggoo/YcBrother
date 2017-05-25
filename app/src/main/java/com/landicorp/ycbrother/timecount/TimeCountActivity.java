package com.landicorp.ycbrother.timecount;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.landicorp.ycbrother.R;

/**
 * Created by 杨大哥 on 2017/5/18.
 */

public class TimeCountActivity extends AppCompatActivity {

    private TimeCountButton timeCountButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_time);
        timeCountButton = (TimeCountButton) findViewById(R.id.timeCount);
        timeCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeCountButton.startCount();
            }
        });
    }
}
