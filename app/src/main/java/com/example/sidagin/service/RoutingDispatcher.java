package com.example.sidagin.service;
import java.util.Random;

public class RoutingDispatcher {
    private static String baseUrl = "http://192.168.42.44:8000";
  //private static String baseUrl = "https://sidagins.extraorgeggs.xyz";
    public static String getBaseUrl() {
        return baseUrl;
    }
}
