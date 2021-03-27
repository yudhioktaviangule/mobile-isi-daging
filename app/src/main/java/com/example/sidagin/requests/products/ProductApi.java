package com.example.sidagin.requests.products;

import com.example.sidagin.models.products.Products;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;

import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ProductApi {

    @GET("api/product/{client_id}/get")
    Call<List<Products>> productLists(@Path("client_id")String cl_id);

    @Multipart
    @POST("api/products")
    Call<Products> uploadFile(
            @Part("ikmId") RequestBody ikmId,
            @Part("name") RequestBody name,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part photo
    );

    @GET("api/products/{id}")
    Call<Products> getDetail(
            @Path("id") String id
    );

    @DELETE("api/products/{id}")
    Call<Products> del(@Path("id") int id);

    
}
