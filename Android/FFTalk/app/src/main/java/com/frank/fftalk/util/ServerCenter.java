package com.frank.fftalk.util;


import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class ServerCenter {
    public static ServerCenter Singleton=new ServerCenter();

    public void init(){
        WebSocketCenter.Singleton.init();
    }

    public void login(String userName) {
        this.userName=userName;
        WebSocketCenter.Singleton.send(Msg.Type.LoginRequest,userName);
    }
    public String userName;
    private static final String TAG = "ServerCenter";
    public void handleLoginResponse(String data) {
        Log.i(TAG, "handleLoginResponse: "+data);
        MessageEvent.post(MessageEvent.Type.LoginResponse,JsonUtil.fromJson(data, Msg.LoginStatus.class));
    }

    public void sendMsg(Msg.IMMsg imMsg) {
        WebSocketCenter.Singleton.send(Msg.Type.IM,imMsg);
    }
}
