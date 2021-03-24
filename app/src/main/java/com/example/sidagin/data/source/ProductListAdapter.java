package com.example.sidagin.data.source;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sidagin.DetailProduct;
import com.example.sidagin.R;
import com.example.sidagin.models.products.Products;
import com.example.sidagin.service.RoutingDispatcher;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    Context context;
    ArrayList<Products> productsArrayList;
    String device_id;



    public ProductListAdapter(Context context, ArrayList<Products> productsArrayList,String device_id) {
        this.context = context;
        this.productsArrayList = productsArrayList;
        this.device_id=device_id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.product_adapter,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = productsArrayList.get(position).getPhoto();
        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(holder.imageButton);
        holder.textView.setText(productsArrayList.get(position).getName());
        holder.idProduk = productsArrayList.get(position).getId();

    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageButton;
        TextView textView;
        int idProduk;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.btnCekDetail);
            textView = itemView.findViewById(R.id.isi_produk);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b  =new Bundle();
                    b.putString("product.id",String.valueOf(idProduk));
                    b.putString("device_id",device_id);
                    Intent i = new Intent(context, DetailProduct.class);
                    i.putExtras(b);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }
    }
}
