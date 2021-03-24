package com.example.sidagin.requests.users;


import com.example.sidagin.models.register.Register;
import com.example.sidagin.requests.CreateAuthorizationHeader;
import com.example.sidagin.service.RoutingDispatcher;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterRequest {
    private RegisterUserApi registerUserApi;
    private Retrofit retro;
    private Register reg,result;
    private Call<Register> call;
    public RegisterRequest(){
        HttpLoggingInterceptor inceptor = new HttpLoggingInterceptor();
        inceptor.level(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new CreateAuthorizationHeader("").client.addInterceptor(inceptor).build();
        retro = new Retrofit.Builder().baseUrl(RoutingDispatcher.getBaseUrl())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();


    }
    private void createParam(String tokoName,String deviceId,String email, String password, String name, String alamat, String telepon){
        reg = new Register(tokoName,deviceId,email,password,name,alamat,telepon);
    }
    public RegisterRequest registerMe(String deviceId, String email, String password, String name, String alamat, String telepon, String tokoName){
        this.createParam(tokoName,deviceId,email, password, name, alamat, telepon);
        registerUserApi = retro.create(RegisterUserApi.class);
        call = registerUserApi.registerUser(reg);

        return this;
    }

    public Call<Register> getResult() {
        return call;
    }




}
