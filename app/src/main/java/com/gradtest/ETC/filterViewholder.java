package com.gradtest.ETC;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gradtest.R;

/**
 * Created by SM-PC on 2018-09-10.
 */

public class filterViewholder extends RecyclerView.ViewHolder {

        public TextView region;

        public  filterViewholder(View itemview){
            super(itemview);
            region = (TextView) itemview.findViewById(R.id.textview_area_name);
        }

    }

