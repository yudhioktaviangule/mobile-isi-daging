package com.example.sidagin.service;

import android.content.Context;
import android.provider.Settings;

public class AdrId {
    public String deviceId(Context context){
        String id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return id;
    }
}
