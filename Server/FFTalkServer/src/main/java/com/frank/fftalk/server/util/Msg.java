package com.frank.fftalk.server.util;

public class Msg {
    public enum Type {
        LoginRequest, LoginResponse, OnlineUsers, IM;
    }

    public class IMMsg {
        public String from;
        public String to;
        public String data;
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
