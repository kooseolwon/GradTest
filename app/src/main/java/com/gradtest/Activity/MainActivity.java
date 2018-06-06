package com.gradtest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gradtest.Adapter.WritingAdapter;
import com.gradtest.DataType.Board;
import com.gradtest.ETC.ItemForm;
import com.gradtest.Net.Net;
import com.gradtest.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcv;
    LinearLayoutManager llm;
    WritingAdapter wadapter;
    String date,id,title,item;
    int switch_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btn_w = (Button)findViewById(R.id.writing_btn);
                btn_w.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = getIntent();
                int uidx = intent.getIntExtra("uidx",0);
                Intent it_w = new Intent(MainActivity.this, WritingActivity.class);
                it_w.putExtra("switch_num",switch_num);
                it_w.putExtra("userindex",uidx);
                startActivity(it_w);
                            }
        });


        rcv = (RecyclerView)findViewById(R.id.recycler);
        llm = new LinearLayoutManager(this);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(llm);

        ArrayList<ItemForm> list = new ArrayList<>();


        Call<Board> res = Net.getInstance().getNetworkService().show_board();
        res.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                if(response.isSuccessful()){
                    Board board = response.body();
                    ArrayList data =    response.body().getData();
                    Log.v("seo",data.toString());


                } else{
                int statusCode = response.code();
                    Log.i("MyTag","응답코드 : "+statusCode);
                }
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                Log.i("MyTag", "서버 onFailure 에러내용 : " +t.getMessage());

            }
        });







        list.add(new ItemForm("seolwon",R.drawable.jjang1,"안녕!"));
        list.add(new ItemForm("seol",R.drawable.jjang2,"안녕하세요1"));
        list.add(new ItemForm("won",R.drawable.jjang3,"안녕하세요2"));
        list.add(new ItemForm("seolwon2",R.drawable.jjang4,"안녕하세요3"));
        list.add(new ItemForm("seolwon3",R.drawable.jjang1,"안녕"));
        list.add(new ItemForm("seolwon4",R.drawable.jjang2,"안녕ss"));
        list.add(new ItemForm("seolwon5",R.drawable.jjang3,"안녕서런아"));
        list.add(new ItemForm("seolwon6",R.drawable.jjang4,"안녕하쇼"));

        if(switch_num==1) {
            Toast toast_w = Toast.makeText(this,"글 작성 완료!",Toast.LENGTH_LONG);
            toast_w.show();
            list.add(new ItemForm(id, R.drawable.logo_image, title));
            switch_num = 0;
            wadapter = new WritingAdapter(this, list);//앞서 만든 리스트를 어뎁터에 적용시켜 객체를 만든다.
            rcv.setAdapter(wadapter);// 그리고 만든 겍체를 리싸이클러뷰에 적용시킨다.
        }

        else {
            wadapter = new WritingAdapter(this, list);//앞서 만든 리스트를 어뎁터에 적용시켜 객체를 만든다.
            rcv.setAdapter(wadapter);// 그리고 만든 겍체를 리싸이클러뷰에 적용시킨다.
        }


    }


}
