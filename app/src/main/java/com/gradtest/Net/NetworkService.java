package com.gradtest.Net;

import com.gradtest.DataType.Board;
import com.gradtest.DataType.Comment;
import com.gradtest.DataType.Comment_regist;
import com.gradtest.DataType.User;
import com.gradtest.DataType.User_login;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by sm-pc on 2018-05-30.
 */

public interface NetworkService {

    //회원가입
    @POST("/login/signup")
    Call<User> post_user(@Body User user);

    /*
    @POST("login/signup")
    Call<Res_join> signup(@Body Req_join user);*/

    //로그인
    @POST("login/signin")
    Call<User_login> post_login(@Body User_login user_login);

    /*
    @POST("login/signin")
    Call<Res_login> signin(@Body Req_login user_login);*/

    //게시물작성

/*
    @Multipart
    @POST("board/write")
    Call<Board> post_board(@Header ("token") String user_token, @Body Board board);
*/


    @Multipart
    @POST("board/write")
    Call<Board> post_board( @Part("board_title")RequestBody board_title,
                            @Part("board_content")RequestBody board_content,
                            @Part("board_category")RequestBody board_category,
                            @Part("user_index")RequestBody user_index,
                            @Part("board_location")RequestBody board_location,
                            @Part MultipartBody.Part board_photos);

    /*
    @POST("board/write")
    Call<Res_write> board_write(@Body Req_write board);*/

    //게시물보기
    @GET("board/show")
    Call<Board> show_board();

    @POST("comment/write")
    Call<Comment_regist> post_comment(@Body Comment_regist comment_regist);

    @GET("comment/show/{board_index}")
    Call<Comment> show_comment(
            @Path("board_index") int board_index);

    @DELETE("board/delete")
    Call deletePost(@Path("board_index")int board_index);



}