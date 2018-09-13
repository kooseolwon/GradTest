package com.gradtest.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.gradtest.Lib.BitmapLib;
import com.gradtest.Lib.FileLib;
import com.gradtest.Lib.GoLib;
import com.gradtest.ETC.ImageItem;
import com.gradtest.ETC.MyLog;
import com.gradtest.ETC.MyToast;
import com.gradtest.R;
import com.gradtest.Lib.RemoteLib;
import com.gradtest.Net.RemoteService;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;


/**
 * Created by sm-pc on 2018-05-28.
 */

public class PhotoActivity extends Fragment implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    public static final String INFO_SEQ = "INFO_SEQ";

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;

    Activity context;
    int infoSeq;

    File imageFile;
    String imageFilename;
    ImageView infoImage;
    ImageItem imageItem;

    boolean isSavingImage = false;

    public static PhotoActivity newInstance(int infoSeq){
        Bundle bundle = new Bundle();
        bundle.putInt(INFO_SEQ,infoSeq);

        PhotoActivity p = new PhotoActivity();
        p.setArguments(bundle);

        return p;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!=null){
            infoSeq = getArguments().getInt(INFO_SEQ);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=this.getActivity();
        View v = inflater.inflate(R.layout.activity_photo,container,false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageItem = new ImageItem();
        //imageItem.infoSeq = infoSeq;

        imageFilename = infoSeq + "_" + String.valueOf(System.currentTimeMillis());
        imageFile = FileLib.getInstance().getImageFile(context,imageFilename);

        infoImage = (ImageView)view.findViewById(R.id.imageView);
        Button imageRegister = (Button)view.findViewById(R.id.register_photo);

        imageRegister.setOnClickListener(this);

        view.findViewById(R.id.save_btn).setOnClickListener(this);
        view.findViewById(R.id.back_btn2).setOnClickListener(this);

    }

    private void getImageFromCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        context.startActivityForResult(intent,PICK_FROM_CAMERA);
    }

    private void getImageFromAlbum(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        context.startActivityForResult(intent, PICK_FROM_ALBUM);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.register_photo) {
            showImageDialog(context);
        }else if(v.getId()==R.id.save_btn){
            saveImage();
        }else if(v.getId()==R.id.back_btn2){
            GoLib.getInstance().goBackFragment(getFragmentManager());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            if(requestCode==PICK_FROM_CAMERA){
                Picasso.with(context).load(imageFile).into(infoImage);

            }else if(requestCode==PICK_FROM_ALBUM&&data!=null){
                Uri dataUri = data.getData();

                if(dataUri!=null){
                    Picasso.with(context).load(dataUri).into(infoImage);

                    Picasso.with(context).load(dataUri).into(new Target(){
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            BitmapLib.getInstance().saveBitmapToFileThread(imageUploadHandler, imageFile, bitmap);
                            isSavingImage=true;
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {
                        }
                    });
                }
            }
        }
    }

    private void setImageItem(){
        imageItem.fileName = imageFilename + ".png";
    }

    private void saveImage(){
        if(isSavingImage){
            MyToast.s(context,R.string.no_image_ready);
            return;
        }
        MyLog.d(TAG,"ImageFile.length() "+imageFile.length());

        if(imageFile.length()==0){
            MyToast.s(context, R.string.no_image_selected);
            return;
        }

        setImageItem();

        RemoteLib.getInstance().uploadImage(infoSeq,imageFile,finishHandler);
        isSavingImage = false;
    }

    public void showImageDialog(Context context){
        new AlertDialog.Builder(context).setTitle(R.string.title_image_register).setSingleChoiceItems(R.array.camera_album_category,-1,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    getImageFromCamera();
                }else{
                    getImageFromAlbum();
                }
                dialog.dismiss();
            }
        }).show();
    }

    Handler imageUploadHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isSavingImage = false;
            setImageItem();
            Picasso.with(context).invalidate(RemoteService.IMAGE_URL + imageItem.fileName);
        }
    };

    Handler finishHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            context.finish();
        }
    };
}
