package com.gradtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcv;
    LinearLayoutManager llm;
    WrittingAdapter wadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rcv = (RecyclerView)findViewById(R.id.recycler);
        llm = new LinearLayoutManager(this);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(llm);

        ArrayList<ItemForm> list = new ArrayList<>();

        list.add(new ItemForm("seolwon",R.drawable.jjang1,"안녕!"));
        list.add(new ItemForm("seol",R.drawable.jjang2,"안녕하세요1"));
        list.add(new ItemForm("won",R.drawable.jjang3,"안녕하세요2"));
        list.add(new ItemForm("seolwon2",R.drawable.jjang4,"안녕하세요3"));
        list.add(new ItemForm("seolwon3",R.drawable.jjang1,"안녕"));
        list.add(new ItemForm("seolwon4",R.drawable.jjang2,"안녕ss"));
        list.add(new ItemForm("seolwon5",R.drawable.jjang3,"안녕서런아"));
        list.add(new ItemForm("seolwon6",R.drawable.jjang4,"안녕하쇼"));

        wadapter = new WrittingAdapter(this, list);//앞서 만든 리스트를 어뎁터에 적용시켜 객체를 만든다.
        rcv.setAdapter(wadapter);// 그리고 만든 겍체를 리싸이클러뷰에 적용시킨다.







    }
}
