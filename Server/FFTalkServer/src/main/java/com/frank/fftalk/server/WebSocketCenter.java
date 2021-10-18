package com.frank.fftalk.server;

import com.frank.fftalk.server.util.JsonUtil;
import com.frank.fftalk.server.util.Msg;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class WebSocketCenter {
    final Logger logger = LoggerFactory.getLogger(WebSocketCenter.class);
    public static WebSocketCenter Singleton = new WebSocketCenter();
    private WebSocketServer webSocketServer;

    Map<String, WebSocket> userSession = new HashMap<>();

    private void handleUserLogin(String data, WebSocket webSocket) {
        if (userSession.containsKey(data)) {
            Msg msg = new Msg();
            msg.type = Msg.Type.LoginResponse;
            msg.data = Msg.LoginStatus.Failure.name();
            send(webSocket, msg);
        } else {
            Msg msg = new Msg();
            msg.type = Msg.Type.LoginResponse;
            msg.data = Msg.LoginStatus.Success.name();
            send(webSocket, msg);
        }
    }

    private void send(WebSocket webSocket, Msg msg) {
        webSocket.send(JsonUtil.toJson(msg));
    }

    public void init() {
        webSocketServer = new WebSocketServer() {
            @Override
            public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
                logger.info("onOpen");
            }

            @Override
            public void onClose(WebSocket webSocket, int i, String s, boolean b) {

            }

            @Override
            public void onMessage(WebSocket webSocket, String s) {
                logger.info("onMessage:" + s);
                Msg msg = JsonUtil.fromJson(s, Msg.class);
                switch (msg.type) {
                    case LoginRequest: {
                        handleUserLogin(msg.data, webSocket);
                    }
                    break;
                    default: {
                    }
                    break;
                }

            }

            @Override
            public void onError(WebSocket webSocket, Exception e) {

            }

            @Override
            public void onStart() {
                logger.info("onStart");
            }
        };
        webSocketServer.start();
    }
}
