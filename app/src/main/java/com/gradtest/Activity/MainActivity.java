package com.gradtest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gradtest.Adapter.WritingAdapter;
import com.gradtest.DataType.Board;
import com.gradtest.ETC.ItemForm;
import com.gradtest.Net.Net;
import com.gradtest.R;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcv;
    LinearLayoutManager llm;
    WritingAdapter wadapter;
    ArrayList searchAreaArray;
    String date,id,title,item,content;
    //String user_name[]={}, board_title[]={};
    int switch_num = 0;
    int switchn = 0;


    // 밑 부분은 필터 누르고 갖고온 결과를 처리해주는 부분임.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            searchAreaArray = new ArrayList();
            Log.v("searchArea","들어옴");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        switchn = getIntent().getIntExtra("switch",0);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");

        List<String> user_name = new ArrayList<String>();
        List<String> board_title = new ArrayList<String>();


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
        });//글쓰기 버튼

        Button btn_filter = (Button)findViewById(R.id.filter_btn);
        btn_filter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent it_filter = new Intent(MainActivity.this, filterActivity.class);
                startActivity(it_filter);

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
                    ArrayList data = response.body().getData();
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




/*



        list.add(new ItemForm("koo",R.drawable.jjang1,"서러니최고","쿠쿠쿠"));
        list.add(new ItemForm("seolwon6289",R.drawable.jjang2,"서러니귀엽다","너무 이뻠"));
        list.add(new ItemForm("seolwon6289",R.drawable.jjang3,"씨스타","푸시푸시베이비 내맘을 받아줘"));


        if(switch_num==1) {
            Toast toast_w = Toast.makeText(this,"글 작성 완료!",Toast.LENGTH_LONG);
            toast_w.show();
            list.add(new ItemForm(id, R.drawable.logo_image, title,content));
            switch_num = 0;
            wadapter = new WritingAdapter(this, list);//앞서 만든 리스트를 어뎁터에 적용시켜 객체를 만든다.
            rcv.setAdapter(wadapter);// 그리고 만든 겍체를 리싸이클러뷰에 적용시킨다.
        }

        else {
            wadapter = new WritingAdapter(this, list);//앞서 만든 리스트를 어뎁터에 적용시켜 객체를 만든다.
            rcv.setAdapter(wadapter);// 그리고 만든 겍체를 리싸이클러뷰에 적용시킨다.
        }

        if(switchn ==1)
        {
            list.add(new ItemForm("example2",R.drawable.logo_image,title,content));
            switchn = 0;
            wadapter = new WritingAdapter(this, list);//앞서 만든 리스트를 어뎁터에 적용시켜 객체를 만든다.
            rcv.setAdapter(wadapter);// 그리고 만든 겍체를 리싸이클러뷰에 적용시킨다.
        }
*/




        if(android.os.Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try{

            String urlStr = "http://52.78.129.27:3000/board/show";
            URL url = new URL(urlStr);

            InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
            JSONObject object = (JSONObject)JSONValue.parseWithException(isr);

            System.out.println("jObj : " + object.toString());
            System.out.println("message : " + object.get("message"));
            System.out.println("data : " + object.get("data"));


            String main = object.toString();
            JSONObject obj = (JSONObject)JSONValue.parse(main);
            JSONArray array = (JSONArray)obj.get("data");

            for(int i=0; i<array.size(); i++) {
                JSONObject j = (JSONObject) array.get(i);
                System.out.println("title " + i + "번째 : " + j.get("board_title"));
                System.out.println("id " + i + "번째 : " + j.get("user_name"));
                user_name.add(j.get("user_name").toString());
                board_title.add(j.get("board_title").toString());

                /*list.add(new ItemForm(user_name.get(i),R.drawable.jjang1,board_title.get(i),""));
                wadapter = new WritingAdapter(this, list);
                rcv.setAdapter(wadapter);*/
            }

            for(int i=array.size()-1; i>0; i--){
                list.add(new ItemForm(user_name.get(i),R.drawable.jjang1,board_title.get(i),""));
                wadapter = new WritingAdapter(this, list);
                rcv.setAdapter(wadapter);
            }


        }catch(Exception e){
            e.printStackTrace();
        }





    }


}
