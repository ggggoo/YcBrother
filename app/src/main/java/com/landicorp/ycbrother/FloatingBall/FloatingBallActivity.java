package com.landicorp.ycbrother.floatingBall;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.landicorp.ycbrother.R;

/**
 * Created by 杨大哥 on 2017/5/5.
 */

public class FloatingBallActivity extends Activity{

    private FloatingBall mFloatingBall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_ball);
        init();
    }

    private void init() {
        mFloatingBall = (FloatingBall) findViewById(R.id.group);
        mFloatingBall.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case 0:
                        Toast.makeText(getBaseContext(),"按钮1",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getBaseContext(),"按钮2",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(getBaseContext(),"按钮3",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }
}
