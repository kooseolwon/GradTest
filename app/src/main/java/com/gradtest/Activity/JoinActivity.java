package com.gradtest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gradtest.ETC.MyLog;
import com.gradtest.Net.Net;
import com.gradtest.R;
import com.gradtest.DataType.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sm-pc on 2018-04-15.
 */

public class JoinActivity extends AppCompatActivity {

    EditText et_name,et_id,et_pw;
    Spinner star_area;
    ArrayAdapter<CharSequence> array3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        et_name = (EditText)findViewById(R.id.signup_name);
        et_id = (EditText)findViewById(R.id.signup_id);
        et_pw = (EditText)findViewById(R.id.signup_pw);
        star_area = (Spinner)findViewById(R.id.star_area);
        array3 = ArrayAdapter.createFromResource(JoinActivity.this,R.array.array3,android.R.layout.simple_spinner_item);
        star_area.setAdapter(array3);


        Button join = (Button)findViewById(R.id.join_btn);
        join.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String name = et_name.getText().toString();
                String id = et_id.getText().toString();
                String pw = et_pw.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(JoinActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(id)){
                    Toast.makeText(JoinActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pw)){
                    Toast.makeText(JoinActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User();
                user.setUser_id(id);
                user.setUser_name(name);
                user.setUser_pw(pw);

               /* Req_join req_join = new Req_join();
                req_join.setUser(new User(id,pw,name));
                res= Net.getInstance().getNetworkService().signup(req_join);
                res.enqueue(new Callback<Res_join>() {
                    @Override
                    public void onResponse(Call<Res_join> call, Response<Res_join> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Res_join users = response.body();

                                MyLog.d("Join", "회원가입 성공!");
                                Toast.makeText(JoinActivity.this, "회원 가입 성공!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                MyLog.d("Join 통신", "실패 1 response 내용이 없음");
                            }
                        } else {
                            try {
                                MyLog.d("Join 통신", "실패 2 서버 에러" + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Res_join> call, Throwable t) {
                        MyLog.d("Join 통신", "실패 3 통신 에러" +t.getLocalizedMessage());
                    }
                });*/

                Call<User> res = Net.getInstance().getNetworkService().post_user(user);
                res.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(JoinActivity.this, "회원 가입 성공!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            MyLog.d("Join 통신", "실패 1 response 내용이 없음");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        MyLog.d("Join 통신", "실패 3 통신 에러" +t.getLocalizedMessage());
                    }
                });

               /* Toast.makeText(getApplicationContext(), "회원가입 완료!", Toast.LENGTH_LONG).show();
                Intent it = new Intent(JoinActivity.this, LoginActivity.class);
                startActivity(it);*/
            }
        });

        Button back = (Button)findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent it = new Intent(JoinActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

    }



}