package com.gradtest.Net;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by sm-pc on 2018-05-28.
 */

public interface RemoteService {
    String BASE_URL = "http://127.0.0.1:3000";
    String IMAGE_URL = BASE_URL + "/img/";

    @POST("/board/info/image")
    Call<ResponseBody> uploadImage(@Part("info_seq") RequestBody infoSeq, @Part MultipartBody.Part file);
}
