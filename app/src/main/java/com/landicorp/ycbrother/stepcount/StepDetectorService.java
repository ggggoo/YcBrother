package com.landicorp.ycbrother.stepcount;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by 杨大哥 on 2017/5/16.
 */

public class StepDetectorService extends Service {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private StepDetector mStepDetector;

    public static int steps = 0;

    @Override
    public void onCreate() {
        mStepDetector = new StepDetector();
        registerDetector();
        mStepDetector.setStepListener(new StepListener() {
            @Override
            public void onStep(){
                steps ++;
                if(mOnStepDetectListener != null){
                    mOnStepDetectListener.onStepDetect(steps);
                }
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        steps = 0;
        return super.onStartCommand(intent, flags, startId);
    }

    public void registerDetector() {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mStepDetector, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onDestroy() {
        mOnStepDetectListener = null;
        unRegisterDetector();
        steps = 0;
    }

    public void unRegisterDetector(){
        if(mStepDetector != null && mSensorManager != null){
            mStepDetector.setStepListener(null);
            mSensorManager.unregisterListener(mStepDetector);
        }
    }

    public interface OnStepDetectListener {
        void onStepDetect(int steps);
    }

    public static OnStepDetectListener mOnStepDetectListener = null;

    public static void setOnStepDetectListener(OnStepDetectListener mListener){
        mOnStepDetectListener = mListener;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
