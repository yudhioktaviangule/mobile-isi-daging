package com.example.sidagin.requests.users;

import com.example.sidagin.models.register.Register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterUserApi {

    @POST("api/users")
    Call<Register> registerUser(
            @Body Register register
            );
}
