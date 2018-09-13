package com.gradtest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gradtest.Adapter.CommentAdapter;
import com.gradtest.DataType.Board;
import com.gradtest.DataType.Comment;
import com.gradtest.DataType.Comment_regist;
import com.gradtest.ETC.ItemForm;
import com.gradtest.ETC.MyLog;
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

/**
 * Created by sm-pc on 2018-06-04.
 */

public class CommentActivity extends AppCompatActivity {
    RecyclerView rcv;
    LinearLayoutManager llm;
    CommentAdapter cadapter;
    int board_index,user_index;

    List<String> user_name = new ArrayList<String>();
    List<String> comment_content = new ArrayList<String>();
    List<String> time = new ArrayList<String>();
    List<String> comment_index = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Intent intent = getIntent();
        board_index = intent.getIntExtra("board_index",1);

        rcv = (RecyclerView)findViewById(R.id.recycler_comment);
        llm = new LinearLayoutManager(this);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(llm);

        ArrayList<ItemForm> list = new ArrayList<>();

        Call<Comment> res = Net.getInstance().getNetworkService().show_comment(board_index);
        res.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if(response.isSuccessful()){
                    Comment comment = response.body();
                    ArrayList data = response.body().getData();
                    Log.v("seo",data.toString());


                } else{
                    int statusCode = response.code();
                    Log.i("MyTag","응답코드 : "+statusCode);
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.i("MyTag", "서버 onFailure 에러내용 : " +t.getMessage());

            }
        });


        if(android.os.Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {

            String urlStr = "http://52.78.129.27:3000/comment/show/";
            URL url = new URL(urlStr + String.valueOf(board_index));

            InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
            JSONObject object = (JSONObject) JSONValue.parseWithException(isr);

            System.out.println("jObj : " + object.toString());
            System.out.println("message : " + object.get("message"));
            System.out.println("data : " + object.get("data"));

            String main = object.toString();
            JSONObject obj = (JSONObject)JSONValue.parse(main);
            JSONArray array = (JSONArray)obj.get("data");


            for(int i=0; i<array.size(); i++){
                JSONObject j = (JSONObject) array.get(i);
                user_name.add(j.get("user_name").toString());
                comment_content.add(j.get("comment_content").toString());
                comment_index.add(j.get("comment_index").toString());
                time.add(j.get("comment_time").toString());

                list.add(new ItemForm(user_name.get(i),R.drawable.jjang1,comment_content.get(i),comment_index.get(i),time.get(i)));
                cadapter = new CommentAdapter(this,list);
                rcv.setAdapter(cadapter);

            }


        }catch(Exception e){
            e.printStackTrace();
        }


        final EditText editComment = (EditText)findViewById(R.id.editComment);
        Button commentRegist = (Button)findViewById(R.id.commentRegist);


        SharedPreferences sh = getSharedPreferences("index", Activity.MODE_PRIVATE);
        if(sh!=null) {
           user_index = sh.getInt("index",0);
        }


       // content_temp, board_index, user_index

        final Comment_regist comment_regist = new Comment_regist();


        commentRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content_temp = editComment.getText().toString();
                comment_regist.setComment_content(content_temp);
                Log.i("컨텐츠", content_temp);
                comment_regist.setBoard_index(board_index);
                Log.i("인덱스", String.valueOf(board_index));
                comment_regist.setUser_index(user_index);
                Log.i("인덱스2", String.valueOf(user_index));

                Call<Comment_regist> res2 = Net.getInstance().getNetworkService().post_comment(comment_regist);
                res2.enqueue(new Callback<Comment_regist>() {
                    @Override
                    public void onResponse(Call<Comment_regist> call, Response<Comment_regist> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(CommentActivity.this, "덧글쓰기 완료!", Toast.LENGTH_SHORT).show();
                            finish();

                            Intent i = new Intent(CommentActivity.this, CommentActivity.class);
                            i.putExtra("board_index",board_index);
                            startActivity(i);
                        } else {
                            MyLog.d("comment 통신", "실패 1 response 내용이 없음" + response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<Comment_regist> call, Throwable t) {
                            MyLog.d("Join 통신", "실패 3 통신 에러" +t.getLocalizedMessage());
                        }


                });

            }
        });





    }
}
