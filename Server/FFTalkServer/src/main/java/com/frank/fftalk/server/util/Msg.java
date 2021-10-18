package com.frank.fftalk.server.util;

public class Msg {
    public enum Type {
        LoginRequest, LoginResponse;
    }

    public enum LoginStatus {
        Success, Failure;
    }


    public Type type;
    public String data;
}
