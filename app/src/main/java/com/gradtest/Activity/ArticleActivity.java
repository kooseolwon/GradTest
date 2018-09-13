package com.gradtest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gradtest.Adapter.WritingAdapter;
import com.gradtest.ETC.ItemForm;
import com.gradtest.R;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.w3c.dom.Text;

import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by SM-PC on 2018-04-08.
 */

public class ArticleActivity extends AppCompatActivity {

    int board_index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Intent intent = getIntent();
        TextView Story = (TextView)findViewById(R.id.story_txt);
        TextView Id = (TextView)findViewById(R.id.personal_NM1);
        TextView content = (TextView)findViewById(R.id.content_id);
        TextView location = (TextView)findViewById(R.id.pin_id);
        ImageView imgView = (ImageView)findViewById(R.id.boardPhoto);
        Button commentView = (Button)findViewById(R.id.commentView);


        String a = intent.getStringExtra("story");
        String b = intent.getStringExtra("ID");
        String c = intent.getStringExtra("content");
        int index = Integer.parseInt(c);

        //int c = intent.getIntExtra("img",0);

        Story.setText(a);
        Id.setText(b);
        content.setText(c);
        //Img.setImageResource(c);


        if(android.os.Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try{
            String urlStr = "";


            SharedPreferences boardIndex = getSharedPreferences("boardIndex", Activity.MODE_PRIVATE);
            if(boardIndex != null)
            {
                urlStr = "http://52.78.129.27:3000/board/detail/"+c;
                Log.i("urlStr = ",urlStr);
                board_index = Integer.parseInt(c);
            }

           // String urlStr = "http://52.78.129.27:3000/board/show";
            URL url = new URL(urlStr);

            InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
            JSONObject object = (JSONObject) JSONValue.parseWithException(isr);

            System.out.println("jObj : " + object.toString());
            System.out.println("message : " + object.get("message"));
            System.out.println("data : " + object.get("data"));


            String main = object.toString();
            JSONObject obj = (JSONObject)JSONValue.parse(main);
            JSONArray array = (JSONArray)obj.get("data");
            JSONObject j = (JSONObject)array.get(0);
            System.out.println("title  : " + j.get("board_title"));
            content.setText(j.get("board_content").toString());


            String photoURL=j.get("board_photo").toString();
            System.out.println("photo : "+photoURL);
            if(j.get("board_photo")!=null){
                System.out.println("photo~");
                imgView.setVisibility(View.VISIBLE);
                Glide.with(this).load(photoURL).into(imgView);
            }else{imgView.setVisibility(View.GONE);
                System.out.println("no photo~");}


            if(j.get("board_location")!=null) {
                String pin = j.get("board_location").toString();
                location.setText(pin);
            }else{}



        }catch(Exception e){
            e.printStackTrace();
        }



        commentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArticleActivity.this, CommentActivity.class);
                intent.putExtra("board_index",board_index);
                startActivity(intent);


            }
        });







    }
}
