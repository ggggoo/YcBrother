package com.landicorp.ycbrother.stepcount;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by 杨大哥 on 2017/5/15.
 */

public class StepService extends Service implements SensorEventListener {

    private SensorManager sensorManager;
    private static int stepSensor = -1;
    private boolean hasRecord = false;
    private int CURRENT_SETP = 0;
    private int hasStepCount = 0;
    private int previousStepCount = 0;
    private Messenger messenger = new Messenger(new MessenerHandler());

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                startStepDetector();
            }
        }).start();

    }

    private void startStepDetector() {
        if (sensorManager != null) {
            sensorManager = null;
        }

        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        int VERSION_CODE = Build.VERSION.SDK_INT;
        if (VERSION_CODE >= 19) {
            addCountStepListener();
        }
    }

    private void addCountStepListener() {
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        Sensor detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (countSensor != null) {
            sensorManager.registerListener(StepService.this, countSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else if (detectorSensor != null) {
            sensorManager.registerListener(StepService.this, detectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (stepSensor == 0) {
            int tempStep = (int) sensorEvent.values[0];
            if (!hasRecord) {
                hasRecord = true;
                hasStepCount = tempStep;
            } else {
                int thisStepCount = tempStep - hasStepCount;
                CURRENT_SETP += (thisStepCount - previousStepCount);
                previousStepCount = thisStepCount;
            }
        } else if (stepSensor == 1) {
            if (sensorEvent.values[0] == 1.0) {
                CURRENT_SETP++;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private class MessenerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    try {
                        Messenger messenger = msg.replyTo;
                        Message replyMsg = Message.obtain(null, 1);
                        Bundle bundle = new Bundle();
                        bundle.putInt("step", CURRENT_SETP);
                        replyMsg.setData(bundle);
                        messenger.send(replyMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);

            }
        }
    }
}
