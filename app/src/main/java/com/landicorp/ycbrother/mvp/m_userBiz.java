package com.landicorp.ycbrother.mvp;


import android.os.Handler;

/**
 * Created by 杨大哥 on 2017/5/9.
 */

public class m_userBiz implements m_userIBiz{


    @Override
    public void login(final String userId, final String userPsw, final m_userListener onListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if("123".equals(userId)&&"123".equals(userPsw)){
                    m_userBean user = new m_userBean();
                    user.setUserId(userId);
                    user.setUserPsw(userPsw);
                    onListener.onSuc(user);
                }else{
                    onListener.onFail();
                }
            }
        },1500);
    }
}
