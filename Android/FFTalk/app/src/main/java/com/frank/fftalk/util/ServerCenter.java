package com.frank.fftalk.util;


public class ServerCenter {
    public static ServerCenter Singleton=new ServerCenter();
    private OnConnectStateChangeListener onConnectStateChangeListener;



    public void init(){
        WebSocketCenter.Singleton.init();
    }

    public void login(String userName) {
        Msg msg = new Msg();
        msg.type= Msg.Type.LoginRequest;
        msg.data=userName;
        WebSocketCenter.Singleton.send(msg);
    }

    public interface OnConnectStateChangeListener{
        void onConnectStateChange(ConnectState connectState);

        public enum ConnectState{
            Success,Failure;
        }
    }
    public void setOnConnectStateChangeListener(OnConnectStateChangeListener onConnectStateChangeListener) {
        this.onConnectStateChangeListener=onConnectStateChangeListener;
    }
}
