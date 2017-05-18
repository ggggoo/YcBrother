package com.landicorp.ycbrother.timecount;

import android.os.CountDownTimer;

/**
 * Created by 杨大哥 on 2017/5/18.
 */

public class TimeCount extends CountDownTimer {

    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);

    }

    @Override
    public void onTick(long l) {

    }

    @Override
    public void onFinish() {

    }
}
