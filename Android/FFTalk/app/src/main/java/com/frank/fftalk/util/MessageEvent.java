package com.frank.fftalk.util;

import org.greenrobot.eventbus.EventBus;

public class MessageEvent<T> {

   public enum Type{
        LoginResponse,OnlineUserChange;
    }
  public   Type type;
    public   T data;

    public MessageEvent(Type type, T data) {
        this.type = type;
        this.data = data;
    }

    static void post(Type type, Object data){
        EventBus.getDefault().post(new MessageEvent(type,data));
    }
}
