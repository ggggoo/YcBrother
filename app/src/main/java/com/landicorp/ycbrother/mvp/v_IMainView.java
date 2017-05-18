package com.landicorp.ycbrother.mvp;

/**
 * Created by 杨大哥 on 2017/5/9.
 */

public interface v_IMainView {

    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(m_userBean user);

    void showFailedError();
}
