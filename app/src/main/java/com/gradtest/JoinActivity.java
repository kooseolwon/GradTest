package com.gradtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by sm-pc on 2018-04-15.
 */

public class JoinActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Button join = (Button)findViewById(R.id.join_btn);
        join.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "회원가입 완료!", Toast.LENGTH_LONG).show();
                Intent it = new Intent(JoinActivity.this, LoginActivity.class);
                startActivity(it);
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