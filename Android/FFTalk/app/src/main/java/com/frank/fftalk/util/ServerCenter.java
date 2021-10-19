package com.frank.fftalk.util;


import android.util.Log;

public class ServerCenter {
    public static ServerCenter Singleton=new ServerCenter();
    private OnConnectStateChangeListener onConnectStateChangeListener;



    public void init(){
        WebSocketCenter.Singleton.init();
    }

    public void login(String userName) {
        WebSocketCenter.Singleton.send(Msg.Type.LoginRequest,userName);
    }

    private static final String TAG = "ServerCenter";
    public void handleLoginResponse(String data) {
        Log.i(TAG, "handleLoginResponse: "+data);
        onConnectStateChangeListener.onConnectStateChange(JsonUtil.fromJson(data,Msg.LoginStatus.class));
    }

    public interface OnConnectStateChangeListener{
        void onConnectStateChange(Msg.LoginStatus connectState);

    }
    public void setOnConnectStateChangeListener(OnConnectStateChangeListener onConnectStateChangeListener) {
        this.onConnectStateChangeListener=onConnectStateChangeListener;
    }
}
