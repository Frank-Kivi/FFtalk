package com.frank.fftalk.util;

import com.google.gson.Gson;

public class JsonUtil {
    private  static Gson gson=new Gson();
    public static String toJson(Object obj){
        return gson.toJson(obj);
    }
}
