package com.gradtest.ETC;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sm-pc on 2018-05-28.
 */

public class ImageItem {
    public int seq;
    @SerializedName("info_seq")public int infoSeq;
    @SerializedName("file_name")public String fileName;
    @SerializedName("reg_date")public int regDate;


    @Override
    public String toString() {
        return "ImageItem{" +", infoSeq=" + infoSeq + ", fileName='" + fileName + '\'' + ", regDate'"+regDate+'\''+'}';
    }
}
