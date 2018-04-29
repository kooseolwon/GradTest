package com.gradtest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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
    TextView where_text;
    Date date;
    int switch_num;
    int w_p1;
    int[] sp1;
    RadioButton btn_where1, btn_where2;
    RadioButton where1_1, where1_2;
    Spinner spinner1,spinner2_0,spinner2_1,spinner2_2;
    String where,where2_0, where2_1, where2_2;
    ArrayAdapter<CharSequence> array,array2_0,array2_1,array2_2;


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

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2_0 = (Spinner)findViewById(R.id.spinner2_0);
        spinner2_1 = (Spinner)findViewById(R.id.spinner2_1);
        spinner2_2 = (Spinner)findViewById(R.id.spinner2_2);

        array = ArrayAdapter.createFromResource(WritingActivity.this,R.array.array,android.R.layout.simple_spinner_item);
        array2_0 = ArrayAdapter.createFromResource(WritingActivity.this,R.array.array2_0,android.R.layout.simple_spinner_item);
        array2_1 = ArrayAdapter.createFromResource(WritingActivity.this,R.array.array2_1,android.R.layout.simple_spinner_item);
        array2_2 = ArrayAdapter.createFromResource(WritingActivity.this,R.array.array2_2,android.R.layout.simple_spinner_item);


        spinner1.setAdapter(array);spinner2_0.setAdapter(array2_0);spinner2_1.setAdapter(array2_1);spinner2_2.setAdapter(array2_2);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                w_p1 = position;
                Toast.makeText(getApplicationContext(), Integer.toString(w_p1), Toast.LENGTH_SHORT).show();


                switch(position) {
                    case 1:
                        where_text.setText("지역설정 > 서울특별시 > ");
                        spinner2_0.setVisibility(View.VISIBLE);
                        spinner2_1.setVisibility(View.GONE);
                        spinner2_2.setVisibility(View.GONE);
                }
                switch(position) {
                    case 2:
                        where_text.setText("지역설정 > 부산광역시 > ");
                        spinner2_0.setVisibility(View.GONE);
                        spinner2_1.setVisibility(View.VISIBLE);
                        spinner2_2.setVisibility(View.GONE);
                }
                switch(position) {
                    case 3:
                        where_text.setText("지역설정 > 인천광역시 >");
                        spinner2_0.setVisibility(View.GONE);
                        spinner2_1.setVisibility(View.GONE);
                        spinner2_2.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2_0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                where2_0 = String.valueOf(tv.getText());
                where_text.setText("지역설정 > 서울특별시 > " +where2_0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                where2_1 = String.valueOf(tv.getText());
                where_text.setText("지역설정 > 부산광역시 > " +where2_1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                where2_2 = String.valueOf(tv.getText());
                where_text.setText("지역설정 > 인천광역시 > " +where2_2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        where_text = (TextView)findViewById(R.id.where_text);
        btn_where1 = (RadioButton)findViewById(R.id.btn_where);
        btn_where1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                spinner1.setVisibility(View.VISIBLE);
                where_text.setText("지역설정 > ");

            }
        });


        btn_where2 = (RadioButton)findViewById(R.id.btn_where2);
        btn_where2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner1.setVisibility(View.GONE);
                where_text.setText("지역설정 > 안함");
            }
        });


    }







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