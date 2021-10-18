package com.frank.fftalk.server;

import com.frank.fftalk.server.util.Msg;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FFTalkServer {


    public static void main(String[] args) {
        WebSocketCenter.Singleton.init();
    }
}
