package com.gradtest.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gradtest.DataType.Board;
import com.gradtest.ETC.MyLog;
import com.gradtest.Lib.FileLib;
import com.gradtest.Net.Net;
import com.gradtest.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.Date;
import java.text.SimpleDateFormat;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sm-pc on 2018-04-11.
 */

public class WritingActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_STORAGE = 1111;
    private static final int REQUEST_TAKE_ALBUM = 2222;

    Uri albumURI,photoURI,realURI;
    String mCurrentPhotoPath,path;
    TextView where_text;
    TextView pin_text,photo_text;
    Date date;
    int w_p1;
    int[] sp1;
    RadioButton btn_where1, btn_where2;
    RadioButton where1_1, where1_2;
    Spinner spinner1,spinner2;
    String where,where2_0, where2_1, where2_2, tk;
    ArrayAdapter<CharSequence> array,array2;
    EditText title,content;

    String b_title, b_content;
    File photo, imageFile;
    int b_category = 0;
    String pin,real;
    int index_temp, userindex;
    Call<Board> res;
    Boolean photoCheck, pinCheck;
    RequestBody board_title, board_content,user_index, board_category, board_location;
    MultipartBody.Part board_photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);



        SharedPreferences pinCheck1 = getSharedPreferences("pinCheck1", Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = pinCheck1.edit();
        edit.putBoolean("pinCheck",false);
        edit.commit();

        pinCheck=false;
        photoCheck = false;
        board_photos = null;
        board_location = null;


        Intent intent = super.getIntent();

        index_temp = intent.getIntExtra("uidx", 0);
        Log.d("아이디",""+index_temp);

        pin = intent.getStringExtra("pin");

        ActionBar ab = getSupportActionBar();
        ab.setIcon(R.mipmap.logo);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);


        ImageButton cam_btn = (ImageButton)findViewById(R.id.cam);
        cam_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //Intent intent_photo = new Intent(WritingActivity.this, PhotoActivity.class);
                //startActivity(intent_photo);


                photoCheck = true;

                checkPermission();
                getAlbum();

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
                SharedPreferences pinCheck1 = getSharedPreferences("pinCheck1",Activity.MODE_PRIVATE);
                if(pin != null)
                {
                    SharedPreferences.Editor edit = pinCheck1.edit();
                    edit.putBoolean("pinCheck1",true);

                }

                Intent intent_pin = new Intent(WritingActivity.this, MapActivity.class);
                startActivity(intent_pin);
                pinCheck = true;

            }
        });


        photo_text=(TextView)findViewById(R.id.photo_txt);
        if(board_photos==null) {
            photo_text.setText("[사진] : 첨부 파일 없음");
        }else{
            //photo_text.setText("[사진] : " + albumURI.toString());
        }

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

                b_category = position+1;
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
                b_category =0;
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

                b_title = title.getText().toString();
                b_content = content.getText().toString();

                final Board board = new Board();
                board.setBoard_title(b_title);
                board.setBoard_content(b_content);
                board.setBoard_category(b_category);

                Log.i("11",board.getBoard_title().toString());
                Log.i("22",board.getBoard_content().toString());
                Log.i("33",String.valueOf(board.getBoard_category()));


                SharedPreferences sh = getSharedPreferences("index",Activity.MODE_PRIVATE);
                if(sh != null)
                {
                    userindex = sh.getInt("index",0);
                    Log.i("on", String.valueOf(userindex));
                }


                if(pinCheck) {

                    SharedPreferences location = getSharedPreferences("location", Activity.MODE_PRIVATE);
                    if (location != null) {
                        board_location = RequestBody.create(MediaType.parse("text"), pin.getBytes());
                        Log.d("swsw2", "location_" + board_location.toString());
                    } else {
                        board_location = null;
                    }

                }
                else{board_location = null;}


                //board.setToken(tk);


                //RequestBody token = RequestBody.create(MediaType.parse("multipart/form-data"),tk);
                //RequestBody board_photos = null;

                //  MultipartBody.Part body = MultipartBody.Part.createFormData("file", board_photo.getName(), reqFile);

                board_title = RequestBody.create(MediaType.parse("text"),b_title.getBytes());
                board_content = RequestBody.create(MediaType.parse("text"),b_content.getBytes());
                board_category = RequestBody.create(MediaType.parse("text"), String.valueOf(b_category).getBytes());
                user_index = RequestBody.create(MediaType.parse("text"),String.valueOf(userindex).getBytes());


                if(photoCheck){

                    File imageFile = new File(real.toString());

                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/png"),imageFile);
                    board_photos = MultipartBody.Part.createFormData("board_photos",imageFile.getName(),requestFile);
                    Log.i("카메라성공",board_photos.toString());
                }

                if (!photoCheck){
                    board_photos=null;
                    Log.i("카메라실패ㅠ","");
                }
                else{}



                // Log.d("카테고리","sdf"+board.getBoard_category());
                //  Log.d("인덱스","ㄴㅇㄹ"+board.getUser_index());

                res = Net.getInstance().getNetworkService().post_board(board_title, board_content, board_category,user_index,board_location,board_photos);
                res.enqueue(new Callback<Board>() {
                    @Override
                    public void onResponse(Call<Board> call, Response<Board> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(WritingActivity.this, "글쓰기 완료!", Toast.LENGTH_SHORT).show();
                            //Log.d("swsw","d"+board.getBoard_title());
                            Intent intent = new Intent(WritingActivity.this, MainActivity.class);
                            //intent.putExtra("title",b_title);
                            //intent.putExtra("content",b_content);
                            //intent.putExtra("switch",1);

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

    private void checkPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                new AlertDialog.Builder(this).setTitle("알림").setMessage("저장소 권한이 거부되었습니다. 설정에서 해당 권한을 직접 허용하셔야 합니다.").setNeutralButton("설정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        startActivity(intent);
                    }
                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).create().show();
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_STORAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSION_STORAGE:
                for(int i=0; i<grantResults.length; i++){
                    if(grantResults[i]<0){
                        Toast.makeText(WritingActivity.this, "해당 권한을 활성화 하셔야 합니다.",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
                }
        }
    }

    private void getAlbum(){
        Log.i("getAlbum","call");
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_TAKE_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case REQUEST_TAKE_ALBUM:
                if (resultCode == Activity.RESULT_OK) {
                    if (data.getData() != null) {
                        try {
                            File albumFile = null;

                            photo = createImageFile();


                            photo = Environment.getExternalStorageDirectory();
                            path = photo.getPath();
                            Log.e("PATH" , path);

                            photoURI = data.getData();
                            real = getRealPathFromURI(photoURI);
                            Log.e("aaaaa", real);
                            albumURI = Uri.fromFile(photo);
                            photo_text.setText("[사진] : " + real.toString());
                            Log.e("TAKE_ALBUM_SUCCESS", albumURI.toString());
                            Log.e("TAKE_ALBUM_SUCCESS", photoURI.toString());


                            ImageView photoView = (ImageView)findViewById(R.id.photoView);
                            Picasso.with(this).load(photo).into(photoView);

                        } catch (IOException ex) {
                            Log.e("TAKE_ALBUM_SINGLE_ERROR", ex.toString());
                        }
                    } else {

                    }
                }break;
        }


    }

    public File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        String imageFileName = "PNG_" + timeStamp;
        File imageFile = null;
        File storageDir = new File(Environment.getExternalStorageDirectory()+"/Pictures","sw");

        if(!storageDir.exists()){
            Log.i("mCurrentPhotoPath1", storageDir.toString());
            storageDir.mkdirs();
        }

        imageFile = new File(storageDir,imageFileName);
        mCurrentPhotoPath = imageFile.getAbsolutePath();
        Log.i("Current", mCurrentPhotoPath);

        return imageFile;
    }

    private String getRealPathFromURI(Uri contentURI) {
        String filePath;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            filePath = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            filePath = cursor.getString(idx);
            cursor.close();
        }

        return filePath;

    }

}




