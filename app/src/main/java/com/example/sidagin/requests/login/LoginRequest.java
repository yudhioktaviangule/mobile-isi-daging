package com.example.sidagin.requests.login;

import com.example.sidagin.models.login.Login;
import com.example.sidagin.models.users.Users;
import com.example.sidagin.service.RoutingDispatcher;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRequest {
    public Call<Login> loginWithDeviceId(String dev_id){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit rtf = new Retrofit.Builder()
                .baseUrl(RoutingDispatcher.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        LoginApi api = rtf.create(LoginApi.class);
        Call<Login> myrequest = api.loginWithDeviceId(dev_id);
        return myrequest;
    }
    public Call<Login> loginWithUname(String username,String password,String dev_id){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit rtf = new Retrofit.Builder()
                .baseUrl(RoutingDispatcher.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        LoginApi api = rtf.create(LoginApi.class);
        Call<Login> myrequest = api.loginWithUsername(username,password,dev_id);
        return myrequest;
    }
}
