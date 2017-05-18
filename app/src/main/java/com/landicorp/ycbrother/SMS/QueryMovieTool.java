package com.landicorp.ycbrother.sms;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 杨大哥 on 2017/5/5.
 */

public class QueryMovieTool {

    public QueryMovieTool(){

    }

    public static void queryMovieReal(final Context context, final queryMovieListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MobClient mobClient;
                    mobClient = new MobClient("http://apicloud.mob.com/boxoffice/day/query");
                    mobClient.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                    mobClient.addRequestProperty("Accept", "application/json");
                    mobClient.addParam("key","1d9b165406e08");
                    mobClient.addParam("area","CN");
                    String result = mobClient.post();
                    Logger.d(result);
                    listener.onResult(result);
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public interface queryMovieListener{
        void onResult(String result);
    }
}
