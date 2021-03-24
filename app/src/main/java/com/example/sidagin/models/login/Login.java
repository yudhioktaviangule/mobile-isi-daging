package com.example.sidagin.models.login;

import com.example.sidagin.models.ikm.Ikm;
import com.example.sidagin.models.users.Users;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("user")
    @Expose
    private Users user;
    @SerializedName("ikm")
    @Expose
    private Ikm ikm;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("code")
    @Expose
    private int code;
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Ikm getIkm() {
        return ikm;
    }

    public void setIkm(Ikm ikm) {
        this.ikm = ikm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
