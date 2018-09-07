package com.gradtest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gradtest.Adapter.WritingAdapter;
import com.gradtest.ETC.ItemForm;
import com.gradtest.ETC.MyLog;
import com.gradtest.Net.Net;
import com.gradtest.R;
import com.gradtest.DataType.User_login;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.Buffer;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SM-PC on 2018-04-02.
 */


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_id, et_pw;
    String id, pw;
    int user_index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = (EditText)findViewById(R.id.et_loginid);
        et_pw = (EditText)findViewById(R.id.et_passwordtxt);

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
                id = et_id.getText().toString();
                pw = et_pw.getText().toString();

                final User_login user_login = new User_login();
                user_login.setUser_id(id);
                user_login.setUser_pw(pw);

                Call<User_login> res = Net.getInstance().getNetworkService().post_login(user_login);



               res.enqueue(new Callback<User_login>() {
                   @Override
                   public void onResponse(Call<User_login> call, Response<User_login> response) {
                       if (response.isSuccessful()) {


                           Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                           Log.v("seolwonk", String.valueOf(response.body().getUser_index()));

                           int useridx = response.body().getUser_index();

                           String tk = response.body().getToken();

                           SharedPreferences sh = getSharedPreferences("index", Activity.MODE_PRIVATE);
                           SharedPreferences.Editor edit = sh.edit();
                           edit.putInt("index",useridx);
                           edit.commit();




                           Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                           intent.putExtra("uidx",useridx);

                           startActivity(intent);
                           MyLog.d("Login 통신", tk);

                       }else{
                           MyLog.d("Login 통신", "실패 1 response 내용이 없음");
                           Toast.makeText(LoginActivity.this, "가입 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onFailure(Call<User_login> call, Throwable t) {
                       MyLog.d("Login 통신", "실패 3 통신 에러" +t.getLocalizedMessage());
                   }
               });
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
