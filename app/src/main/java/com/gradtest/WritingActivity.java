package com.gradtest;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;



/**
 * Created by sm-pc on 2018-04-11.
 */

public class WritingActivity extends AppCompatActivity {

    DBHelper dbHelper;
    TextView title;
    TextView item;
    Date date;
    int switch_num;

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




        dbHelper = new DBHelper(getApplicationContext(), "Board.db",null,1);

        title = (TextView)findViewById(R.id.editTitle);
        item = (TextView)findViewById(R.id.editText);

        Long now = System.currentTimeMillis();
        date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        //메인화면 레이아웃에 날짜 추가해서 포맷  붙여야함


    }

   /* public byte[] getByteArrayFromDrawable(Drawable d){
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] data = stream.toByteArray();

        return data;*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    } //액션바 메뉴 지정한거 붙이게 하는 코드


    @Override
    public boolean onOptionsItemSelected(MenuItem menuitem) {
        switch(menuitem.getItemId()){
            case R.id.temp :
                Toast toast_t = Toast.makeText(this,"임시저장 완료!",Toast.LENGTH_LONG);
                Intent it = new Intent(WritingActivity.this,MainActivity.class);
                startActivity(it);
                return true;
            case R.id.write_fin :
                //Toast toast_w = Toast.makeText(this,"글 작성 완료!",Toast.LENGTH_LONG);
                //toast_w.show();

                String str_date = "2018.4.15";
                String str_id = "b89786";
                String str_title = title.getText().toString();
                String str_item =  item.getText().toString();


                //아이디,프사
                switch_num = 1;


                Intent it2 = new Intent(WritingActivity.this,MainActivity.class);
                it2.putExtra("date",str_date);
                it2.putExtra("id",str_id);
                it2.putExtra("title",str_title);
                it2.putExtra("item",str_item);
                it2.putExtra("switch_num",switch_num);

                finish();
                startActivity(it2);


                return true;
            default :
                return false;
        }
    }



}