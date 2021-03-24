package com.example.sidagin.requests;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CreateAuthorizationHeader {
    String token;
    public CreateAuthorizationHeader(String _token){
        token=_token;
    }
    public OkHttpClient.Builder client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization",token).build();

            return chain.proceed(newRequest);
        }
    });

}
