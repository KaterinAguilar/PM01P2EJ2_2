package com.example.pm01p2ej2_2.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    @GET("posts")
    Call<List<Posts>> getPosts();

    @GET("posts/1")
    Call<Posts> getPosts1();

}
