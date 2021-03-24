package com.example.sidagin.home;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sidagin.HomeActivity;
import com.example.sidagin.R;
import com.example.sidagin.models.products.Products;
import com.example.sidagin.requests.products.CreateProduct;
import com.example.sidagin.service.Console;
import com.google.android.material.snackbar.Snackbar;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;

public class ProductAddFragment extends Fragment {
    Context AppContext;
    ImageView imageView;
    EditText nama,deskripsi;
    Button btn;
    String token;
    File fileUri;
    private String ikm;

    public String getCacheDir() {
        return cacheDir;
    }

    String cacheDir = "";
    private View iflate;
    private final int CODE_IMAGE_GALLERY = 1;
    private final String SAMPLE_CROPPED_IMG_NAME="SidaginCroppedImage";
    private final int UCROP_REQUEST=100;
    private ProgressDialog progressDialog;
    MultipartBody.Part XFile;
    public ProductAddFragment(Context context){
        AppContext = context;
    }

    private View.OnClickListener imageViewClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivityForResult(new Intent()
                    .setAction(Intent.ACTION_GET_CONTENT)
                    .setType("image/*")
                    , CODE_IMAGE_GALLERY);
        }
    };

    private View.OnClickListener kirimProduk = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String namap,deskp;
            RequestBody name,description,ikmIda;

            namap = nama.getText().toString();
            deskp = deskripsi.getText().toString();
            if(namap.equals("")||deskp.equals("")| fileUri==null){
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Data Kosong");
                builder.setMessage("Nama Produk, deskripsi produk atau File Photo masih kosong, Harap isi isian Fotonya");
                builder.setPositiveButton("OK Saya Mengerti",null);

                AlertDialog alert  = builder.create();

                alert.show();
            }else{
                ikmIda = RequestBody.create(MultipartBody.FORM,ikm);
                name = RequestBody.create(MultipartBody.FORM,namap);
                description = RequestBody.create(MultipartBody.FORM,deskp);
                File file = new File(fileUri.getPath());
                Uri fileUris = Uri.fromFile(file);
                String type = getActivity().getContentResolver().getType(fileUris);
                Retrofit retro = CreateProduct.retrofitGet(token);
                Call<Products> productsCall = CreateProduct.requestProduct(retro,name,description,XFile,ikmIda);
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Sedang mengirim data Produk");
                progressDialog.setTitle("Simpan Data Produk");
                progressDialog.show();
                productsCall.enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                           // Snackbar.make(getActivity().getApplicationContext(),null,"Simpan data Berhasil",Snackbar.LENGTH_SHORT).show();
                            Toast.makeText(getActivity().getApplicationContext(),"DONE",Toast.LENGTH_SHORT).show();
                            kosongkan();
                        }
                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        //Snackbar.make(getActivity().getApplicationContext(),null,"Simpan data Gagal",Snackbar.LENGTH_SHORT).show();
                        Toast.makeText(getActivity().getApplicationContext(),"FAIL"+t.getMessage(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        kosongkan();
                    }
                });
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        iflate =  inflater.inflate(R.layout.fr_tambah_product,container,false);
        init();
        return iflate;
    }


    private void kosongkan(){
        nama.setText("");
        deskripsi.setText("");
        imageView.setImageResource(R.drawable.ic_image_orange);
    }

    private void init() {
        imageView = iflate.findViewById(R.id.imgViewUploadGbr);
        imageView.setOnClickListener(imageViewClick);
        Bundle bunda = getArguments();
        cacheDir = bunda.getString("cache.dir");
         ikm = bunda.getString("ikm.id");
        token = bunda.getString("device_id");
        Console.err("IKM:"+ikm);
        nama = iflate.findViewById(R.id.namaBarang);
        deskripsi = iflate.findViewById(R.id.deskripsi_barang);
        btn = iflate.findViewById(R.id.btnKirimDataPrd);
        btn.setOnClickListener(kirimProduk);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CODE_IMAGE_GALLERY && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            if(imageUri!=null){
                this.startCrop(imageUri);
            }
        }else if(requestCode== UCROP_REQUEST  && resultCode == RESULT_OK){
            Console.log("REMODISIUM");
            Uri ImageResultCOmp = UCrop.getOutput(data);
            if(ImageResultCOmp!=null){
                RequestBody filePart;
                Console.err("INI GAMBAR LOH"+ImageResultCOmp.getPath());
                imageView.setImageURI(ImageResultCOmp);
                fileUri = new File(ImageResultCOmp.getPath());
//                getActivity().getContentResolver().getType(ImageResultCOmp)

                filePart = RequestBody.create(

                        MediaType.parse("image/jpg"),
                        fileUri
                );
                XFile = MultipartBody.Part.createFormData("photo",fileUri.getName(),filePart);
                Console.log(XFile.toString());
            }
        }
    }

    private void startCrop(@NonNull Uri uri){

        String destinationFileName = SAMPLE_CROPPED_IMG_NAME+".jpg";

        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(getActivity().getCacheDir(),destinationFileName)));
        uCrop.withAspectRatio(1,1);
        /*uCrop.withAspectRatio(3,4);
        uCrop.useSourceImageAspectRatio();
        uCrop.withAspectRatio(2,3);
        uCrop.withAspectRatio(16,9);*/
        uCrop.withMaxResultSize(300,300);
        uCrop.withOptions(this.getCropOptions());
        uCrop.start(getActivity().getApplicationContext(),ProductAddFragment.this,UCROP_REQUEST);
    }
    private UCrop.Options getCropOptions(){
        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(70);
        //options.setCompressionFormat(Bitmap.CompressFormat.PNG);
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setHideBottomControls(false);
        options.setFreeStyleCropEnabled(false);
        options.setStatusBarColor(getResources().getColor(R.color.brightTurq2));
        options.setToolbarColor(getResources().getColor(R.color.brightTurq2));
        options.setToolbarTitle("Crop Gambar");
        return options;
    }




}
