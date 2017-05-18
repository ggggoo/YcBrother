package com.landicorp.ycbrother.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.landicorp.ycbrother.R;

/**
 * Created by 杨大哥 on 2017/5/9.
 */

public class v_main extends AppCompatActivity implements v_IMainView {

    private EditText etUserId,etPsw;
    private p_userLogin mPresent = new p_userLogin(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_login);
        initView();

    }

    private void initView() {
        etUserId = (EditText) findViewById(R.id.mvp_user);
        etPsw = (EditText) findViewById(R.id.mvp_psw);
        Button btn1 = (Button) findViewById(R.id.mvp_btn1);
        Button btn2 = (Button) findViewById(R.id.mvp_btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresent.doLogin();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresent.doClear();
            }
        });
    }

    @Override
    public String getUserName() {
        return etUserId.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPsw.getText().toString();
    }

    @Override
    public void clearUserName() {
        etUserId.setText("");
    }

    @Override
    public void clearPassword() {
        etPsw.setText("");
    }

    @Override
    public void showLoading() {
        Toast.makeText(this,"showLoading",Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this,"hideLoading",Toast.LENGTH_LONG).show();
    }

    @Override
    public void toMainActivity(m_userBean user) {
        Toast.makeText(this,"toMainActivity",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,"showFailedError",Toast.LENGTH_LONG).show();
    }
}
