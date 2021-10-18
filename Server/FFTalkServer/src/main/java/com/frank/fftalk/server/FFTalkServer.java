package com.frank.fftalk.server;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FFTalkServer {
    final static Logger logger = LoggerFactory.getLogger(FFTalkServer.class);

    public static void main(String[] args) {
        new WebSocketServer() {
            @Override
            public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {

            }

            @Override
            public void onClose(WebSocket webSocket, int i, String s, boolean b) {

            }

            @Override
            public void onMessage(WebSocket webSocket, String s) {

            }

            @Override
            public void onError(WebSocket webSocket, Exception e) {

            }

            @Override
            public void onStart() {
                logger.info("onStart");
            }
        }.start();
    }

}
