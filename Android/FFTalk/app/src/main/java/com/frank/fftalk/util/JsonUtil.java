package com.frank.fftalk.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtil {
    private static Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String s, Class<T> msgClass) {
        return gson.fromJson(s, msgClass);
    }
    public static <T> T fromJson(String s, Type msgClass) {
        return gson.fromJson(s, msgClass);
    }
}
