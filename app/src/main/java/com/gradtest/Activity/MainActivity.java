package com.gradtest.Activity;

import android.content.Context;
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
    List<String> index, time;
    String date, id, title, item, content;
    int flag = 0;
    int switch_num = 0;
    int switchn = 0;
    ArrayList area;
    public static Context CONTEXT;
    StringBuilder str;
    ArrayList data;



    // 밑 부분은 필터 누르고 갖고온 결과를 처리해주는 부분임.


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println(area);
        System.out.println("data입니다옹" + data);
        if (resultCode == RESULT_OK) {
            flag = 1;
            Log.v("snow", "드러와쬬");
            area = data.getIntegerArrayListExtra("filterIntent");

        }
        System.out.println(area);


    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
        System.out.println("snowfield"+area);
        str = new StringBuilder();

        if(area == null){
            str.append("[]");
        }else{
            str.append("[");
                for(int i = 0; i < area.size();i++){
                    str.append(area.get(i));
                    if(area.size()-1 != i){
                        str.append(',');
                    }
                }
            str.append("]");

        }



        String arrString = str.toString();

        System.out.println("onResume" + arrString);
        List<String> user_name = new ArrayList<String>();
        List<String> board_title = new ArrayList<String>();
        index = new ArrayList<String>();
        time = new ArrayList<String>();
        rcv = (RecyclerView) findViewById(R.id.recycler);
        llm = new LinearLayoutManager(this);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(llm);
        Button btn_w = (Button) findViewById(R.id.writing_btn);
        btn_w.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = getIntent();
                int uidx = intent.getIntExtra("uidx", 0);

                Intent it_w = new Intent(MainActivity.this, WritingActivity.class);
                it_w.putExtra("switch_num", switch_num);
                it_w.putExtra("userindex", uidx);

                startActivity(it_w);
            }
        });//글쓰기 버튼

        // 필터 인텐트


        Button btn_filter = (Button) findViewById(R.id.filter_btn);
        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it_filter = new Intent(MainActivity.this, filterActivity.class);
                startActivityForResult(it_filter, 0);

            }
        });




        ArrayList<ItemForm> list = new ArrayList<>();
        //data = new ArrayList();
        Call<Board> res = Net.getInstance().getNetworkService().show_board(arrString);
        res.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                if (response.isSuccessful()) {
                    Board board = response.body();
                    data = response.body().getData();
                    System.out.println("seolwonkoo"+data);
                    Log.v("seo", data.toString());


                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                Log.i("MyTag", "서버 onFailure 에러내용 : " + t.getMessage());

            }
        });

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {

            String urlStr = "http://52.78.129.27:3000/board/show?board_category="+arrString;
            System.out.println(urlStr);
            URL url = new URL(urlStr);

            InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
            JSONObject object = (JSONObject) JSONValue.parseWithException(isr);

            System.out.println("jObj : " + object.toString());
            System.out.println("message : " + object.get("message"));
            System.out.println("data : " + object.get("data"));


            String main = object.toString();


            JSONObject obj = (JSONObject) JSONValue.parse(main);
            JSONArray array = (JSONArray) obj.get("data");

            for (int i = 0; i < array.size(); i++) {
                JSONObject j = (JSONObject) array.get(i);
                System.out.println("title " + i + "번째 : " + j.get("board_title"));
                System.out.println("id " + i + "번째 : " + j.get("user_name"));
                System.out.println("time " + i + "번째 : " + j.get("board_time"));
                user_name.add(j.get("user_name").toString());
                board_title.add(j.get("board_title").toString());
                index.add(j.get("board_index").toString());
                time.add(j.get("board_time").toString());
            }

            for (int i = array.size() - 1; i > 0; i--) {
                list.add(new ItemForm(user_name.get(i), R.drawable.jjang1, board_title.get(i), index.get(i), time.get(i)));
                wadapter = new WritingAdapter(this, list);
                rcv.setAdapter(wadapter);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
