package com.landicorp.ycbrother.numberrunning;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.landicorp.ycbrother.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 杨大哥 on 2017/5/25.
 */

public class NumberRunning extends TextView {
    private int frameNum;
    private int textType;
    private boolean useCommaFormat;
    private boolean runWhenChange;
    private String preStr;
    private int finalNum;
    private int nowNum;
    private ExecutorService threadPool = Executors.newFixedThreadPool(1);

    public NumberRunning(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberRunning(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberRunning);
        frameNum = typedArray.getInt(R.styleable.NumberRunning_frameNum, 299);
        textType = typedArray.getInt(R.styleable.NumberRunning_textType, 0);
        useCommaFormat = typedArray.getBoolean(R.styleable.NumberRunning_useComaFormat, true);
        runWhenChange = typedArray.getBoolean(R.styleable.NumberRunning_runWhrnChange, true);
        typedArray.recycle();
    }

    public void setContent(String str) {
        if (runWhenChange) {
            if (TextUtils.isEmpty(str)) {
                preStr = str;
                playNumAnim(str);
                return;
            }

            if(str.equals(preStr)){
                return;
            }

            preStr = str;
        }
        playNumAnim(str);
    }

    public void playNumAnim(String numStr) {
        finalNum = Integer.parseInt(numStr);
        if (finalNum < frameNum){
            NumberRunning.this.setText(numStr);
            return;
        }

        nowNum = 0;
        try {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Message msg = handler.obtainMessage();
                    if(frameNum == 0) frameNum = 300;
                    int temp = finalNum / frameNum;//数字除以帧数，得到每帧跳跃的大小
                    msg.what = 1;
                    msg.obj = temp;
                    handler.sendMessage(msg);// 发送通知改变UI
                }
            });
        }catch (NumberFormatException e){
            e.printStackTrace();
            NumberRunning.this.setText(numStr);//如果转换Double失败则直接用setText
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NumberRunning.this.setText(String.valueOf(nowNum));
            nowNum += (Integer) msg.obj;//记录当前每帧递增后的数字
            if (nowNum < finalNum) {
                //如果当前记录的数字小于目标数字，即还没达到目标数字，继续递增
                Message msg2 = handler.obtainMessage();
                msg2.what = 1;
                msg2.obj = msg.obj;
                handler.sendMessageDelayed(msg2,100);
//                handler.sendMessage(msg2);// 继续发送通知改变UI
            } else {
                //已经达到目标的数字，显示最终的内容
                NumberRunning.this.setText(String.valueOf(finalNum));
            }
        }
    };


}
