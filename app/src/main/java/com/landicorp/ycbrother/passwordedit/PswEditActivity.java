package com.landicorp.ycbrother.passwordedit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.landicorp.ycbrother.R;

/**
 * Created by 杨大哥 on 2017/5/24.
 */

public class PswEditActivity extends AppCompatActivity {

    private PswEditText pswEditText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psw_edit);
        initView();
    }

    private void initView() {
        pswEditText = (PswEditText) findViewById(R.id.passwords);
        pswEditText.setPsw("123456", new PswEditText.pswListener() {
            @Override
            public void success() {
                Toast.makeText(getBaseContext(),"congratulations!!!!",Toast.LENGTH_LONG).show();
            }

            @Override
            public void error(String str) {
                Toast.makeText(getBaseContext(),"sorry"+str,Toast.LENGTH_LONG).show();
            }
        });

    }
}
