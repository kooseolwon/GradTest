package com.gradtest.Activity;

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
                //Intent intent = new Intent();
                //intent.putExtra("area_filter", map);
                System.out.println("들어왔"+map);
                for(int a = 0; a<map.size();a++){
                    if(map.get(a)){
                        arr.add(a);
                    }
                }

               // setResult(RESULT_OK,intent);
                finish();
            }
        });





    }
}
