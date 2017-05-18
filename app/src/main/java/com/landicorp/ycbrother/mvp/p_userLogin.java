package com.landicorp.ycbrother.mvp;

/**
 * Created by 杨大哥 on 2017/5/9.
 */

class p_userLogin {

    private v_IMainView mainView;
    private m_userBiz userBiz;

     p_userLogin(v_IMainView mainView) {
        this.mainView = mainView;
        this.userBiz = new m_userBiz();
    }

     void doLogin() {
        mainView.showLoading();
        userBiz.login(mainView.getUserName(), mainView.getPassword(), new m_userListener() {
            @Override
            public void onSuc(m_userBean user) {
                mainView.toMainActivity(user);
            }

            @Override
            public void onFail() {
                mainView.showFailedError();
            }
        });
    }

     void doClear() {
        mainView.clearUserName();
        mainView.clearPassword();
    }
}
