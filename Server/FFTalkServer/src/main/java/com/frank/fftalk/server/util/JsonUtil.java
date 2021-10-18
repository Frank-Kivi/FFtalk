package com.frank.fftalk.server.util;

import com.google.gson.Gson;

public class JsonUtil {
    private static Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String s, Class<T> msgClass) {
        return gson.fromJson(s, msgClass);
    }
}
