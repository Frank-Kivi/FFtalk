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
        logger.info(data);
        if (userSession.containsKey(data)) {
            send(webSocket, Msg.Type.LoginResponse, Msg.LoginStatus.Failure);
        } else {
            send(webSocket, Msg.Type.LoginResponse, Msg.LoginStatus.Success);
            userSession.put(data, webSocket);
            webSocket.setAttachment(data);

            broadcast(Msg.Type.OnlineUsers, userSession.keySet());
        }
    }

    private void broadcast(Msg.Type type, Object data) {
        String text = JsonUtil.toJson(new Msg(type, data));
        logger.info("broadcast:" + text);
        webSocketServer.broadcast(text);
    }

    private void broadcast(Msg.Type type, String data) {
        webSocketServer.broadcast(JsonUtil.toJson(new Msg(type, data)));
    }

    private void send(WebSocket webSocket, String s) {
        webSocket.send(s);
    }

    private void send(WebSocket webSocket, Msg.Type type, Object data) {
        send(webSocket, JsonUtil.toJson(new Msg(type, data)));
    }

    private void send(WebSocket webSocket, Msg.Type type, String data) {
        send(webSocket, JsonUtil.toJson(new Msg(type, data)));
    }

    public void init() {
        webSocketServer = new WebSocketServer() {
            @Override
            public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
                logger.info("onOpen");
            }

            @Override
            public void onClose(WebSocket webSocket, int i, String s, boolean b) {
                String data = webSocket.getAttachment();
                userSession.remove(data);
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
                    case IM: {
                        handleIM(msg.data, webSocket, s);
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

    private void handleIM(String data, WebSocket webSocket, String s) {
        Msg.IMMsg imMsg = JsonUtil.fromJson(data, Msg.IMMsg.class);
        WebSocket dest = userSession.get(imMsg.from);
        if (dest != null) {
            send(dest, s);
        }
    }
}
