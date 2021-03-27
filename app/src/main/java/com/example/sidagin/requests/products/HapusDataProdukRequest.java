package com.example.sidagin.requests.products;

import com.example.sidagin.models.products.Products;
import com.example.sidagin.requests.CreateAuthorizationHeader;
import com.example.sidagin.service.RoutingDispatcher;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HapusDataProdukRequest {
    public static Retrofit retrofit(String token){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new CreateAuthorizationHeader(token).client.addInterceptor(interceptor).build();
        Retrofit cretro = new Retrofit.Builder()
                .baseUrl(RoutingDispatcher.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return cretro;
    }

    public static Call<Products> deleteProduct(Retrofit retrofits,int id){
        ProductApi productApi = retrofits.create(ProductApi.class);
        Call<Products> productsCall = productApi.del(id);
        return productsCall;
    }
}
