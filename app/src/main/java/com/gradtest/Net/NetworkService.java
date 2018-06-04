package com.gradtest.Net;

import com.gradtest.DataType.Board;
import com.gradtest.DataType.User;
import com.gradtest.DataType.User_login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

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
    @POST("board/write")
    Call<Board> post_board(@Body Board board);

    /*
    @POST("board/write")
    Call<Res_write> board_write(@Body Req_write board);*/

}