package com.example.sidagin.models.register;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.FormUrlEncoded;

import static android.content.ContentValues.TAG;

public class Register {
    public Register(String tokoName,String device_id, String email, String password, String name, String alamat, String telepon){
       this.name=name;
       this.deviceId=device_id;
       this.email=email;
       this.password=password;
       this.alamat=alamat;
       this.telepon=telepon;
       this.namaIkm=tokoName;
       this.level = "ikm";
       this.aktivasi = "nonaktif";
       Log.d(TAG, "Register: "+this.deviceId);

    }
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("aktivasi")
    @Expose
    private String aktivasi;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("nama_ikm")
    @Expose
    private String namaIkm;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("telepon")
    @Expose
    private String telepon;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAktivasi() {
        return aktivasi;
    }

    public void setAktivasi(String aktivasi) {
        this.aktivasi = aktivasi;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaIkm() {
        return namaIkm;
    }

    public void setNamaIkm(String namaIkm) {
        this.namaIkm = namaIkm;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}
