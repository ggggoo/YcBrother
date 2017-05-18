package com.landicorp.ycbrother.mvp;

/**
 * Created by 杨大哥 on 2017/5/9.
 */

public interface m_userIBiz {

     void login(String userId,String userPsw,m_userListener onListener);
}
