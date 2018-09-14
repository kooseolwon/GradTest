package com.gradtest.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gradtest.ETC.area;
import com.gradtest.ETC.filterViewholder;
import com.gradtest.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by SM-PC on 2018-09-04.
 */

public class filterAdapter extends RecyclerView.Adapter<filterViewholder> {

    public HashMap <Integer,Boolean> items = new HashMap<>();

    private Activity activity;

    public void addHashItem(HashMap map){

        items.putAll(map);
        return ;
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public filterViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_item_layout,parent,false);
        filterViewholder viewholder01 = new filterViewholder(view);

        return viewholder01;

    }

    @Override// 여기서 최종적으로 셋텍스트하는 것들이 바로 아이템이 되는 것.
    public void onBindViewHolder(final filterViewholder holder, final int position) {


        //holder.itemView.setTag(position);
        ArrayList<area> list = new ArrayList<area>(Arrays.asList(area.values()));
        holder.region.setText(list.get(position).areaName);


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                items.put(position,!(items.get(position)));

                notifyDataSetChanged();

            }

        });//전국을 누르면 다른 것들이 취소되게!



        if(items.get(position)){
            holder.region.setBackgroundColor(0xFFFFBCBC);

        }else{
            holder.region.setBackgroundResource(R.drawable.rect);

        }



    }
    public filterAdapter(Activity activity, HashMap<Integer,Boolean> items){
        this.activity = activity;
        this.items = items;
    }


}
