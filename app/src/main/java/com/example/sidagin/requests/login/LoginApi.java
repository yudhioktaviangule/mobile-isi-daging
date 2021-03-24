package com.example.sidagin.requests.login;

import com.example.sidagin.models.login.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginApi {
    @GET("api/clientlogin/{device_id}")
    Call<Login> loginWithDeviceId(@Path("device_id") String device_id);

    @FormUrlEncoded
    @POST("api/clientlogin")
    Call<Login> loginWithUsername(@Field("email") String email, @Field("password") String password, @Field("device_id") String device_id);

}
