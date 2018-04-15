package com.gradtest;

/**
 * Created by sm-pc on 2018-04-15.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by sm-pc on 2018-04-11.
 */

public class Pw1Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw1);

        Button pw1 = (Button)findViewById(R.id.findpw);
        pw1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "Pw2로 가기", Toast.LENGTH_LONG).show();
                Intent it = new Intent(Pw1Activity.this, Pw2Activity.class);
                startActivity(it);
            }
        });

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent it = new Intent(Pw1Activity.this, LoginActivity.class);
                startActivity(it);
            }
        });
    }
}