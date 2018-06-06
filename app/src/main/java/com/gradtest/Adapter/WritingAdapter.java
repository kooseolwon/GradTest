package com.gradtest.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gradtest.Activity.ArticleActivity;
import com.gradtest.ETC.ItemForm;
import com.gradtest.R;

import java.util.ArrayList;

/**
 * Created by SM-PC on 2018-04-13.
 */

public class WritingAdapter extends RecyclerView.Adapter<WritingAdapter.MyViewholder> {
    private Activity activity;
    private ArrayList<ItemForm> datalist;

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        MyViewholder viewholder = new MyViewholder(view);


        return viewholder;
    }

    public int getItemCount() {
        return datalist.size();
    }

    @Override
    public void onBindViewHolder(WritingAdapter.MyViewholder holder, int position) {

        ItemForm data = datalist.get(position);
        holder.personalId.setText(data.getId());

        holder.writtingTxt.setText(data.getTxt());



    }


    public class MyViewholder extends RecyclerView.ViewHolder
    {

        TextView writtingTxt;
        TextView personalId;
        String wrTxt;


        public MyViewholder(View itemview){
            super(itemview);




            ;
            writtingTxt = (TextView) itemview.findViewById(R.id.person_id1);
            personalId = (TextView) itemview.findViewById(R.id.person_id);




            itemview.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
//                    Log.v("TWICE",Integer.toString(getAdapterPosition()));
//                    Log.v("kooseolwon",wrTxt);
                    Log.v("koooo",datalist.get(getAdapterPosition()).getTxt());






                    Intent intent = new Intent(v.getContext(), ArticleActivity.class);
                    intent.putExtra("story",datalist.get(getAdapterPosition()).getTxt());
                    intent.putExtra("ID",datalist.get(getAdapterPosition()).getId());
                  ///  intent.putExtra("img",datalist.get(getAdapterPosition()).getImageNumber());


                    v.getContext().startActivity(intent);




                }
            });




        }

    }

    public WritingAdapter(Activity activity, ArrayList<ItemForm> datalist){
        this.activity = activity;
        this.datalist = datalist;


    }


}
