package com.gradtest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.gradtest.MyLog;
import com.gradtest.Net;
import com.gradtest.R;
import com.gradtest.User_login;

import org.json.JSONArray;

import java.io.IOException;

import okhttp3.ResponseBody;
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
                String id = et_id.getText().toString();
                String pw = et_pw.getText().toString();

                final User_login user_login = new User_login();
                user_login.setUser_id(id);
                user_login.setUser_pw(pw);

                Call<User_login> res = Net.getInstance().getNetworkService().post_login(user_login);

                    /*
                    @Override
                    public void onResponse(Call<Res_login> call, Response<Res_login> response) {

                        if (response.isSuccessful()) {
                            if(response.body()!=null){
                                Res_login users = response.body();

                                MyLog.d("Login", "로그인 성공!");
                                Toast.makeText(LoginActivity.this, "로그인 완료!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);

                            }else{
                                MyLog.d("Login 통신", "실패 1 response 내용이 없음");
                                Toast.makeText(LoginActivity.this, "내용x!", Toast.LENGTH_SHORT).show();
                            }

                            }else{
                            try{
                                MyLog.d("Login 통신", "실패 2 서버 에러"+response.errorBody().string());
                                Toast.makeText(LoginActivity.this, "서버에러", Toast.LENGTH_SHORT).show();
                            }catch(IOException e){
                                e.printStackTrace();
                            }
                        }
                        }


                    @Override
                    public void onFailure(Call<Res_login> call, Throwable t) {
                        MyLog.d("Login 통신", "실패 3 통신 에러" +t.getLocalizedMessage() );
                        Toast.makeText(LoginActivity.this, "통신에러", Toast.LENGTH_SHORT).show();
                    }
                });*/

               res.enqueue(new Callback<User_login>() {
                   @Override
                   public void onResponse(Call<User_login> call, Response<User_login> response) {
                       if (response.isSuccessful()) {

                           Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                           startActivity(intent);

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
