package com.example.sidagin.service;

import android.util.Log;

import java.util.Random;

public class Console {
        public static void log(String var){
            Log.d("TRACE",var);
        }
        public static void err(String var){
            Log.d("ERROR:",var);
        }
        public static String createToken(String devid) {
        Random generator = new Random();
            StringBuilder randomStringBuilder = new StringBuilder();
            int randomLength = 256;
            String cvalue = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            char[] cv = cvalue.toCharArray();
            String tempChar="";
            for (int i = 0; i < randomLength; i++){
                Random rand = new Random();
                tempChar = ""+String.valueOf(cv[rand.nextInt(40)]);
                randomStringBuilder.append(tempChar);
            }
            String myBearer= "Bearer "+devid+"h.h"+randomStringBuilder.toString();
            Console.log("AUTH : "+myBearer);
            return myBearer;
        }
}
