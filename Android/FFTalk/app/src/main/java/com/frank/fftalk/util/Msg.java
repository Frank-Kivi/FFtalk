package com.frank.fftalk.util;

public class Msg {
    public enum Type {
        LoginRequest, LoginResponse, OnlineUsers, IM;
    }

    public static class IMMsg {
        public String from;
        public String to;
        public String data;

        public IMMsg(String from, String to, String data) {
            this.from = from;
            this.to = to;
            this.data = data;
        }
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
