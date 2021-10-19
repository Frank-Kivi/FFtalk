package com.frank.fftalk.util;

public class Msg {
    public enum Type {
        LoginRequest, LoginResponse, OnlineUsers;
    }

    public enum LoginStatus {
        Success, Failure;
    }

    public Msg(Type type, String data) {
        this.type = type;
        this.data = data;
    }

    public Msg(Type type, Object data) {
        this.type = type;
        this.data = JsonUtil.toJson(data);
    }

    public Type type;
    public String data;
}
