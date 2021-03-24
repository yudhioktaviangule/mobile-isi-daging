package com.example.sidagin;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sidagin.models.products.Products;
import com.example.sidagin.requests.products.DetailProducts;

public class DetailProduct extends AppCompatActivity {
    TextView namaProduk,deskripsiProduk;
    ImageView image;
    Button tombol;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_product);
        initialize();
    }

    private void initialize() {
        image = findViewById(R.id.imageViewDetProduk);
        namaProduk = findViewById(R.id.textDetailNamaProd);
        deskripsiProduk = findViewById(R.id.textDetailDescription);
        pd = new ProgressDialog(DetailProduct.this);
        tombol = findViewById(R.id.btnDetailKembli);
        networking();
        tombol.setOnClickListener(listenKembali);
    }

    private final View.OnClickListener listenKembali = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };



    private void networking() {
        Bundle bdl = getIntent().getExtras();
        String token = bdl.getString("device_id");
        String productId = bdl.getString("product.id");
        Retrofit retrofit = DetailProducts.retrofitGet(token);
        pd.setTitle("Ambil Data");
        pd.setMessage("Mengambil data");
        pd.show();
        Call<Products> interfes = DetailProducts.getProduct(productId,retrofit);
        interfes.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                String url = "";
                if (response.isSuccessful()){
                    url = response.body().getPhoto();
                    Glide.with(DetailProduct.this)
                            .asBitmap()
                            .load(url)
                            .into(image);
                    namaProduk.setText(response.body().getName());
                    deskripsiProduk.setText(response.body().getDescription());
                }
                pd.dismiss();
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });
    }
}