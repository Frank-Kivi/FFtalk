package com.frank.fftalk.util;

import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class WebSocketCenter {
    public static WebSocketCenter Singleton=new WebSocketCenter();
    private static final String TAG = "WebSocketCenter";
    public static final String URL="ws://192.168.137.1:80/";
    private WebSocketClient webSocketClient;

    public void send(Msg.Type type,Object data){
        blockingDeque.add(JsonUtil.toJson(new Msg(type,data)));
    }
    public void send(Msg.Type type,String data){
        blockingDeque.add(JsonUtil.toJson(new Msg(type,data)));
    }


    BlockingDeque<String> blockingDeque=new LinkedBlockingDeque<>();

    public void init() {
         webSocketClient=new WebSocketClient(URI.create(URL)) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.i(TAG, "onOpen() called with: handshakedata = [" + handshakedata + "]");
                ExecutorServiceUtil.executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            String take = null;
                            try {
                                take = blockingDeque.take();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            send(take);
                        }
                    }
                });
            }

            @Override
            public void onMessage(String message) {
                Log.i(TAG, "onMessage() called with: message = [" + message + "]");
                Msg msg=JsonUtil.fromJson(message,Msg.class);
                switch(msg.type){
                    case LoginResponse :{
                        handleLoginResponse(msg.data);
                    }
                    break;
                    default:{
                    }
                    break;
                }
            }

             @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.i(TAG, "onClose() called with: code = [" + code + "], reason = [" + reason + "], remote = [" + remote + "]");
            }

            @Override
            public void onError(Exception ex) {
                Log.i(TAG, "onError() called with: ex = [" + ex + "]");
            }
        };
        webSocketClient.connect();
    }

    private void handleLoginResponse(String data) {
        ServerCenter.Singleton.handleLoginResponse(data);
    }
}
