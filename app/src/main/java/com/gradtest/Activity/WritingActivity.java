package com.gradtest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gradtest.DataType.Board;
import com.gradtest.ETC.MyLog;
import com.gradtest.Net.Net;
import com.gradtest.R;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sm-pc on 2018-04-11.
 */

public class WritingActivity extends AppCompatActivity {

    TextView where_text;
    TextView pin_text;
    Date date;
    int switch_num;
    int w_p1;
    int[] sp1;
    RadioButton btn_where1, btn_where2;
    RadioButton where1_1, where1_2;
    Spinner spinner1,spinner2;
    String where,where2_0, where2_1, where2_2;
    ArrayAdapter<CharSequence> array,array2;
    EditText title,content;

    String board_title, board_content;
    File board_photo;
    int board_category = 0;
    String pin;
    int index_temp;
    Call<Board> res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        Intent pinsave = new Intent(this.getIntent());
         Intent intent1 = getIntent();
         index_temp = intent1.getIntExtra("uidx",0);

        pin = getIntent().getStringExtra("pin");

        ActionBar ab = getSupportActionBar();
        ab.setIcon(R.mipmap.logo);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);


        ImageButton cam_btn = (ImageButton)findViewById(R.id.cam);
        cam_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent_photo = new Intent(WritingActivity.this, PhotoActivity.class);
                startActivity(intent_photo);
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
                Intent intent_pin = new Intent(WritingActivity.this, MapActivity.class);
                startActivity(intent_pin);

            }
        });


        pin_text=(TextView)findViewById(R.id.pin_txt);
        if(pin==null) {
            pin_text.setText("[위치] : 위치 정보 없음");
        }else{
            pin_text.setText("[위치] : " + pin);
        }




        title = (EditText) findViewById(R.id.editTitle);
        content = (EditText) findViewById(R.id.editText);



        Long now = System.currentTimeMillis();
        date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        //메인화면 레이아웃에 날짜 추가해서 포맷  붙여야함

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);


        array = ArrayAdapter.createFromResource(WritingActivity.this,R.array.array,android.R.layout.simple_spinner_item);
        array2 = ArrayAdapter.createFromResource(WritingActivity.this,R.array.array2,android.R.layout.simple_spinner_item);


        spinner1.setAdapter(array);spinner2.setAdapter(array2);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                w_p1 = position;

                switch(position) {
                    case 1:
                        where_text.setText("지역설정 > 서울특별시 > ");
                        spinner2.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                where2_0 = String.valueOf(tv.getText());
                where_text.setText("지역설정 > 서울특별시 > " +where2_0);

                board_category = position+1;
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
                board_category =0;
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

                board_title = title.getText().toString();
                board_content = content.getText().toString();
                Board board = new Board();
                board.setBoard_title(board_title);
                board.setBoard_content(board_content);
                board.setBoard_category(board_category);
                board.setUser_index(index_temp);
                res = Net.getInstance().getNetworkService().post_board(board);
                res.enqueue(new Callback<Board>() {
                    @Override
                    public void onResponse(Call<Board> call, Response<Board> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(WritingActivity.this, "글쓰기 완료!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(WritingActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{

                            MyLog.d("Join 통신", "실패 1 response 내용이 없음"+response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<Board> call, Throwable t) {
                        MyLog.d("Join 통신", "실패 3 통신 에러" +t.getLocalizedMessage());
                    }
                });

                return true;
            default :
                return false;
        }
    }



}