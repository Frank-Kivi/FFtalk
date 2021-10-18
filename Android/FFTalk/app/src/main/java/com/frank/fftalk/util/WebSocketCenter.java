package com.frank.fftalk.util;

import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class WebSocketCenter {
    public static WebSocketCenter Singleton=new WebSocketCenter();
    private static final String TAG = "WebSocketCenter";
    public static final String URL="ws://192.168.137.1:80/";
    private WebSocketClient webSocketClient;

    public void send(Msg msg){
        blockingDeque.add(JsonUtil.toJson(msg));
    }


    BlockingDeque<String> blockingDeque=new LinkedBlockingDeque<>();

    public void init() {
         webSocketClient=new WebSocketClient(URI.create(URL)) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.d(TAG, "onOpen() called with: handshakedata = [" + handshakedata + "]");
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

            @Override
            public void onMessage(String message) {
                Log.d(TAG, "onMessage() called with: message = [" + message + "]");
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.d(TAG, "onClose() called with: code = [" + code + "], reason = [" + reason + "], remote = [" + remote + "]");
            }

            @Override
            public void onError(Exception ex) {
                Log.d(TAG, "onError() called with: ex = [" + ex + "]");
            }
        };
        webSocketClient.connect();
    }
}
