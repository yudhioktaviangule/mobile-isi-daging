package com.example.sidagin.requests.jsonplaceholders;

import com.example.sidagin.JSONPlaceholder;
import com.example.sidagin.service.RoutingDispatcher;
import com.example.sidagin.service.Console;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JReqeuest {
    String baseUrl="";

    public List<JSONPlaceholder> getJsonPlaceholders() {
        return jsonPlaceholders;
    }

    List<JSONPlaceholder> jsonPlaceholders;
    public JReqeuest(){
        baseUrl =RoutingDispatcher.getBaseUrl();
    }
    public JReqeuest exec(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        JApi jApi = retrofit.create(JApi.class);
        Call<List<JSONPlaceholder>> call = jApi.getData();

        call.enqueue(new Callback<List<JSONPlaceholder>>() {
            @Override
            public void onResponse(Call<List<JSONPlaceholder>> call, Response<List<JSONPlaceholder>> response) {
                if(!response.isSuccessful()){
                   Console.err("ERROR CODE:"+response.code());
                }
                jsonPlaceholders = response.body();
                for (JSONPlaceholder jsonPlaceholder : jsonPlaceholders){
                    Console.log("title : "+jsonPlaceholder.getTitle());

                }
            }

            @Override
            public void onFailure(Call<List<JSONPlaceholder>> call, Throwable t) {
                Console.err(t.getMessage());
            }
        });
        return this;
    }

}
