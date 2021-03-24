package com.example.sidagin.requests.jsonplaceholders;

import com.example.sidagin.JSONPlaceholder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JApi {
    //posts
    //https://jsonplaceholder.typicode.com/
    @GET("posts")
    Call<List<JSONPlaceholder>> getData();
}
