package com.gradtest;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by sm-pc on 2018-04-11.
 */

public class Pw2Activity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw2);

        Button savepw = (Button)findViewById(R.id.savepw);
        savepw.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "비밀번호 저장", Toast.LENGTH_LONG).show();
                Intent it = new Intent(Pw2Activity.this, LoginActivity.class);
                startActivity(it);
            }
        });

    }
}