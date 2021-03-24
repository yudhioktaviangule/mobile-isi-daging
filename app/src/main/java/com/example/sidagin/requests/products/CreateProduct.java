package com.example.sidagin.requests.products;

import com.example.sidagin.models.products.Products;
import com.example.sidagin.requests.CreateAuthorizationHeader;
import com.example.sidagin.service.RoutingDispatcher;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateProduct {
    public static Retrofit retrofitGet(String myToken){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new CreateAuthorizationHeader(myToken).client
                .addInterceptor(interceptor)
                .build();
        Retrofit r = new Retrofit.Builder()
                .baseUrl(RoutingDispatcher.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return r;
    }

    public static Call<Products> requestProduct(
            Retrofit retrofit,
            RequestBody name,
            RequestBody description,
            MultipartBody.Part foto,
            RequestBody ikm
    ){
        ProductApi api = retrofit.create(ProductApi.class);
        Call<Products> req = api.uploadFile(ikm,name,description,foto);

        return req;
    }
}
