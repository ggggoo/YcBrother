package com.landicorp.ycbrother.timecount;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by 杨大哥 on 2017/5/18.
 */

public class TimeCountButton extends Button {

    private TimeCount timeCount;
    public TimeCountButton(Context context) {
        super(context);
    }

    public TimeCountButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeCountButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void startCount(){
        timeCount = new TimeCount(10000,1000,this,"s后可点击","点击");
        timeCount.start();
    }

}
