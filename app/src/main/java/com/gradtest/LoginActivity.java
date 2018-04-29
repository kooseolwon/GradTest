package com.gradtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by SM-PC on 2018-04-02.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginbtn = (Button)findViewById(R.id.login_btn);
        loginbtn.setOnClickListener(this);

        Button signupbtn = (Button)findViewById(R.id.signup_btn);
       signupbtn.setOnClickListener(this);
        Button findpwbtn = (Button)findViewById(R.id.find_pw_btn);
        findpwbtn.setOnClickListener(this);
        Button findidbtn = (Button)findViewById(R.id.find_id_btn);
        findidbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.signup_btn:
                                Intent intent2 = new Intent(LoginActivity.this, JoinActivity.class);
                                startActivity(intent2);
                               break;

                                case R.id.find_pw_btn:
                                Intent intent4 = new Intent(LoginActivity.this, Pw1Activity.class);
                                startActivity(intent4);
                                break;
            case R.id.find_id_btn:
                Intent intent3 = new Intent(this, FindIdActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
