package com.gradtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * Created by sm-pc on 2018-04-11.
 */

public class WritingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        ActionBar ab = getSupportActionBar();
        ab.setIcon(R.mipmap.logo);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);


        ImageButton cam_btn = (ImageButton)findViewById(R.id.cam);
        cam_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getApplicationContext(),"앨범",Toast.LENGTH_LONG).show();
            }
        });

        ImageButton rec_btn = (ImageButton)findViewById(R.id.rec);
        rec_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getApplicationContext(),"음성녹음하기",Toast.LENGTH_LONG).show();
            }
        });

        ImageButton pin_btn = (ImageButton)findViewById(R.id.pin);
        pin_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getApplicationContext(),"위치띄우기",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    } //액션바 메뉴 지정한거 붙이게 하는 코드


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.temp :
                Toast toast_t = Toast.makeText(this,"임시저장 완료!",Toast.LENGTH_LONG);
                toast_t.show();
                Intent it = new Intent(WritingActivity.this,MainActivity.class);
                startActivity(it);
                return true;
            case R.id.write_fin :
                Toast toast_w = Toast.makeText(this,"글 작성 완료!",Toast.LENGTH_LONG);
                toast_w.show();
                Intent it2 = new Intent(WritingActivity.this,MainActivity.class);
                startActivity(it2);
                return true;
            default :
                return false;
        }
    }


}