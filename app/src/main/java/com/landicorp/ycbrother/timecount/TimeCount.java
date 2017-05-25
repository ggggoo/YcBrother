package com.landicorp.ycbrother.timecount;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by 杨大哥 on 2017/5/18.
 */

public class TimeCount extends CountDownTimer {

    private Button mButton;
    private String tipText;
    private String endText;

    public TimeCount(long millisInFuture, long countDownInterval, Button button, String tipText, String endText) {
        super(millisInFuture, countDownInterval);
        this.mButton = button;
        this.tipText = tipText;
        this.endText = endText;
    }

    @Override
    public void onTick(long l) {
        mButton.setText(l / 1000 + tipText);
        mButton.setEnabled(false);
    }

    @Override
    public void onFinish() {
        mButton.setEnabled(true);
        mButton.setText(endText);
    }
}
