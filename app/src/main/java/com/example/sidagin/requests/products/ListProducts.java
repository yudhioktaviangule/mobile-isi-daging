package com.example.sidagin.requests.products;

import com.example.sidagin.models.products.Products;
import com.example.sidagin.requests.CreateAuthorizationHeader;
import com.example.sidagin.service.Console;
import com.example.sidagin.service.RoutingDispatcher;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class   ListProducts {
    Call<List<Products>> products;
    String token = "";
    public Call<List<Products>> createRequest(String ikmId){
        String token = this.token;
        HttpLoggingInterceptor injector = new HttpLoggingInterceptor();
        injector.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new CreateAuthorizationHeader(token).client.addInterceptor(injector).build();

        Call<List<Products>> myList;
        Retrofit rtfit = new Retrofit.Builder()
                .baseUrl(RoutingDispatcher.getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductApi api = rtfit.create(ProductApi.class);
        myList = api.productLists(ikmId);
        return myList;
    }

    public ListProducts(String deviceId){
        token = Console.createToken(deviceId);
    }

    

}
