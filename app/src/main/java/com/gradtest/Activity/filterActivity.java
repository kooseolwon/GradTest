package com.gradtest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gradtest.Adapter.filterAdapter;
import com.gradtest.ETC.area;
import com.gradtest.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SM-PC on 2018-09-04.
 */

public class filterActivity extends AppCompatActivity{
    private  filterAdapter filterAdapter;
    private RecyclerView rcv;
    private GridLayoutManager glm;
    Button btn;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        final HashMap<Integer, Boolean> map = new HashMap<>();


        int size = area.values().length;
        Toast.makeText(getApplicationContext(), Integer.toString(size), Toast.LENGTH_LONG).show();


        rcv = (RecyclerView)findViewById(R.id.recyclerview_selectarea_area);
        glm = new GridLayoutManager(this,4);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(glm);
        //cv.setHasFixedSize(true);
        //rcv.setLayoutManager(glm);

        for(int i = 0; i< size ; i++){
            map.put(i,false);
        }

        filterAdapter = new filterAdapter(this,map);
        //filterAdapter.addHashItem(map);
        rcv.setAdapter(filterAdapter);

       //System.out.println(filterAdapter.items) ;

        btn = (Button)findViewById(R.id.button_selectarea_select);
        btn.setOnClickListener(new View.OnClickListener(){
            ArrayList arr = new ArrayList();
            @Override
            public void onClick(View v) {

                System.out.println("들어왔"+map);
                for(int a = 0; a<map.size();a++){
                    if(map.get(a)){
                        arr.add(a);
                    }
                }
                System.out.println("koko"+arr);
                Intent intent00 = new Intent();//여기에 MainActivity.class 인자로 넣어줘야 ㅎㅏ지않아..?그 startActivity는 그렇게하던데
                intent00.putExtra("filterIntent",arr);
                setResult(RESULT_OK, intent00);//앞 파라미터가 requestcode
                finish();


            }
        });





    }
}
