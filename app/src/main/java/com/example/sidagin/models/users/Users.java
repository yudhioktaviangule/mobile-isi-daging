package com.example.sidagin.models.users;
import com.example.sidagin.models.ikm.Ikm;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Users {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("ikm_id")
    @Expose
    private int ikmId;
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
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose

    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIkmId() {
        return ikmId;
    }

    public void setIkmId(int ikmId) {
        this.ikmId = ikmId;
    }

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
