package com.frank.fftalk.util;

import java.util.ArrayList;
import java.util.List;

public class IMCenter {
    public static IMCenter Singleton=new IMCenter();

    public List<String> getOnlineUsers() {
        return onlineUsers;
    }

    private List<String> onlineUsers=new ArrayList<>();
    public void updateOnlineUsers(List<String> object) {
        onlineUsers.clear();
        for (String s : object) {
            if (!s.equals(ServerCenter.Singleton.userName)) {
                onlineUsers.add(s);
            }
        }
        MessageEvent.post(MessageEvent.Type.OnlineUserChange,onlineUsers);
    }


    public void onReceiveMsg(Msg.IMMsg fromJson) {
        MessageEvent.post(MessageEvent.Type.IM,fromJson);
    }

    public void send(Msg.IMMsg imMsg) {
        ServerCenter.Singleton.sendMsg(imMsg);
    }
}
