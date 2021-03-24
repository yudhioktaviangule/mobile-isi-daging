package com.example.sidagin.requests.products;

import com.example.sidagin.models.products.Products;
import com.example.sidagin.requests.CreateAuthorizationHeader;
import com.example.sidagin.service.RoutingDispatcher;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailProducts {
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
    public static Call<Products> getProduct(String id,Retrofit retrofit){
        ProductApi productApi = retrofit.create(ProductApi.class);
        Call<Products> prd = productApi.getDetail(id);
        return prd;
    }
}
