package com.frank.fftalk.util;

public class Msg {
    public static enum Type{
        LoginRequest,LoginResponse;
    }

    public Type type;
    public String data;
}
