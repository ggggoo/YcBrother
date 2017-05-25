package com.landicorp.ycbrother.sms;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by 杨大哥 on 2017/5/5.
 */

public class SMSTool {

    private Context mContext;
    private EventHandler eventHandler;
    public SMSTool(Context context){
        this.mContext = context;
        eventHandler = new EventHandler(){
            public void onRegister(){
                Logger.d("register");
            }
            public void beforeEvent(int event, Object data){
                Logger.d("event %s","beforeEvent");

            }
            public void afterEvent(int event, int result, Object data){
                Logger.d(data);
            }
            public void onUnregister(){

            }
        };
    }

    public void startSMS() {
        SMSSDK.initSDK(mContext, "1d983be025060", "e37da3cd1d3636955f68a9021ef662ac");
        SMSSDK.registerEventHandler(eventHandler);

        toSmsSend();
    }

    private void toSmsSend() {
        //打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

                    // 提交用户信息（此方法可以不调用）
//                    registerUser(country, phone);
                    unRegister();
                }
            }
        });
        registerPage.show(mContext);
    }

    private void unRegister(){
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
