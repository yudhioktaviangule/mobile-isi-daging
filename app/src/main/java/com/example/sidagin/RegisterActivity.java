package com.example.sidagin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sidagin.models.register.Register;
import com.example.sidagin.requests.users.RegisterRequest;
import com.example.sidagin.service.Console;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends Activity {
    Button regis,balik;
    EditText email,password,name,telepon,alamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regis = findViewById(R.id.btnDoRegister);
        balik = findViewById(R.id.btnRegisterClose);
        email = findViewById(R.id.textEmailRegister);
        password = findViewById(R.id.textPasswordRegister);
        name = findViewById(R.id.textIKMNameRegister);
        telepon = findViewById(R.id.textIKMTelepon);
        alamat = findViewById(R.id.textAlamatNameRegister);

        events();
    }

    private void events(){
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progressDialog.show();
                Bundle i = getIntent().getExtras();
                String device = i.getString("device_id","no");
                RegisterRequest rr = new RegisterRequest();
                Call<Register> call = rr.registerMe(device,email.getText().toString(),password.getText().toString(),name.getText().toString(),alamat.getText().toString(),telepon.getText().toString(),name.getText().toString()).getResult();
                call.enqueue(new Callback<Register>() {
                    @Override
                    public void onResponse(Call<Register> call, Response<Register> response) {
                        if(!response.isSuccessful()){
                            Console.err("ERR:"+response.code());
                          //  progressDialog.dismiss();
                            return;
                        }
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {
                        Log.d(TAG, "onFailure: FAILURE TO SEND "+t.getMessage());
                        //progressDialog.dismiss();
                    }
                });
            }
        });
        balik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }
}