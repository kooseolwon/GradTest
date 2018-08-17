package com.gradtest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.gradtest.R;

import org.w3c.dom.Text;

/**
 * Created by SM-PC on 2018-04-08.
 */

public class ArticleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Intent intent = getIntent();
        TextView Story = (TextView)findViewById(R.id.story_txt);
        TextView Id = (TextView)findViewById(R.id.personal_NM1);
        TextView content = (TextView)findViewById(R.id.content_id);





        String a = intent.getStringExtra("story");
        String b = intent.getStringExtra("ID");
        String c = intent.getStringExtra("content");
        //int c = intent.getIntExtra("img",0);

        Story.setText(a);
        Id.setText(b);
        content.setText(c);
        //Img.setImageResource(c);


    }
}
