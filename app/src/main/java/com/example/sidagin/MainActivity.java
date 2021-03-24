package com.example.sidagin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sidagin.models.ikm.Ikm;
import com.example.sidagin.models.login.Login;
import com.example.sidagin.models.users.Users;
import com.example.sidagin.requests.login.LoginRequest;
import com.example.sidagin.service.AdrId;
import com.example.sidagin.service.Console;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    Button login,regBtn;
    String devid;
    String TAG="MainActivity_TAG";
    private final int REQUEST_CODE = 123;
    EditText username,password;
    boolean isProcessing=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        devid = new AdrId().deviceId(getApplicationContext());
        login = (Button) findViewById(R.id.btnLogin);
        regBtn = (Button) findViewById(R.id.btnRegisterUser);
        username = (EditText)findViewById(R.id.textUsernameLogin);
        password = (EditText)findViewById(R.id.textPasswordLogin);
        doPerm();



    }
    
    private void doPerm() {

        if(haveStoragePermission()){
            loginWithDevId(devid);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE){
            if((grantResults.length>0)&&(grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED)){
                Snackbar.make(getApplicationContext(),null,"GRANTED ALL PERMISSION",Snackbar.LENGTH_SHORT).show();

            }
        }
    }

    private boolean haveStoragePermission() {
        int perm = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)+ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.READ_EXTERNAL_STORAGE);
        if(perm== PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(),"BERHASIL MENGIJINKAN",Toast.LENGTH_SHORT).show();
            return true;

        }else{
           if(
                   ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)||
                   ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)
           ){
               AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
               builder.setTitle("Izikan Aplikasi");
               builder.setTitle("Izinkan Aplikasi untuk mengakses Galeri");
               builder.setPositiveButton("Izinkan", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       ActivityCompat.requestPermissions(
                               MainActivity.this,
                               new String[]{
                                       Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                       Manifest.permission.READ_EXTERNAL_STORAGE
                               },
                               REQUEST_CODE
                       );
                       return;
                   }
               });
               builder.setNegativeButton("Tolak",null);
               AlertDialog alert = builder.create();

               alert.show();
                return true;
           }else{
               ActivityCompat.requestPermissions(
                       MainActivity.this,
                       new String[]{
                               Manifest.permission.WRITE_EXTERNAL_STORAGE,
                               Manifest.permission.READ_EXTERNAL_STORAGE
                       },
                       REQUEST_CODE
               );
               return true;
           }
        }

    }


    private void loginWithDevId(String devid) {
        boolean isLoggedIn = true;
        LoginRequest lr = new LoginRequest();
        Call<Login> login =lr.loginWithDeviceId(devid);
        login.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.isSuccessful()){
                    if(response.body().getCode()==200){
                        nextActivivi(response);

                    }else{
                        eventsAction(devid);
                        Toast.makeText(getApplicationContext(),"Device tidak ditemukan",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Oops... ada yang error tu",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Tidak dapat menghubungi server",Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void eventsAction(String device_id){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isProcessing){
                    doLogin(username.getText().toString(),password.getText().toString(),devid);
                }else{
                    Toast.makeText(getApplicationContext(),"Silahkan tunggu request selesai",Toast.LENGTH_SHORT).show();
                }
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(getApplicationContext(),RegisterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("device_id",device_id);
                inten.putExtras(bundle);
                startActivity(inten);
            }
        });
    }

    private void doLogin(String username, String password, String devid) {
        LoginRequest lr = new LoginRequest();
        Call<Login> login = lr.loginWithUname(username,password,devid);
        login.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.isSuccessful()){
                    isProcessing=false;
                    Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    if(response.body().getCode()==200){
                        nextActivivi(response);

                    }else{
                        Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }else{
                    return;
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Console.err(t.getMessage());
            }
        });
    }

    private void nextActivivi(Response<Login> response) {
        Users user = response.body().getUser();
        Ikm ikm = response.body().getIkm();
        Bundle bdl = new Bundle();
        bdl.putString("user.id",String.valueOf(user.getId()));
        bdl.putString("user.email",user.getEmail());
        bdl.putString("user.aktivasi",user.getAktivasi());
        bdl.putString("ikm.id",String.valueOf(ikm.getId()));
        bdl.putString("ikm.name",String.valueOf(ikm.getName()));
        bdl.putString("ikm.telepon",String.valueOf(ikm.getHandphone()));
        bdl.putString("ikm.alamat",String.valueOf(ikm.getAlamat()));
        bdl.putString("device_id",devid);
        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
        i.putExtras(bdl);
        startActivity(i);
        finish();
    }
}