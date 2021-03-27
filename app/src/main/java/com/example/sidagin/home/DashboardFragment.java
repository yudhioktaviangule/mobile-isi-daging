package com.example.sidagin.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sidagin.R;
import com.example.sidagin.data.source.ProductListAdapter;
import com.example.sidagin.models.products.Products;
import com.example.sidagin.requests.products.ListProducts;
import com.example.sidagin.service.Console;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    String device_id = "";
    String ikm = "";
    public Context AppContext;
    ArrayList<Products> Produk=new ArrayList<>();
    ProductListAdapter adapter;
    ProgressDialog pdx;
    public DashboardFragment(Context appContext){
        AppContext=appContext;
    }
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle myfBundle = getActivity().getIntent().getExtras();
        this.ikm = myfBundle.getString("ikm.id");
        this.device_id = myfBundle.getString("device_id");
        View inflet =inflater.inflate(R.layout.fr_dashboard,container,false);
        this.pdx = new ProgressDialog(inflet.getContext());
        this.pdx.setCancelable(false);
        this.pdx.setMessage("Mengambil data Produk");
        productsList(inflet);
        return inflet;

    }
    private void productsList(View inflet){
        this.pdx.show();
        Call<List<Products>> list = new ListProducts(this.device_id).createRequest(this.ikm);
        recyclerView = inflet.findViewById(R.id.rcview_prouct);
        adapter = new ProductListAdapter(AppContext,this.Produk,this.device_id);
        recyclerView.setLayoutManager(new GridLayoutManager(AppContext,2));
        recyclerView.setAdapter(adapter);

        list.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                 List<Products> listProd = response.body();
                 if(listProd.size()>0){
                     for(Products prod:listProd){
                         Produk.add(prod);
                     }
                     pdx.dismiss();
                     adapter.notifyDataSetChanged();
                 }else{
                     pdx.dismiss();
                 }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                pdx.dismiss();
                Toast.makeText(AppContext,"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
