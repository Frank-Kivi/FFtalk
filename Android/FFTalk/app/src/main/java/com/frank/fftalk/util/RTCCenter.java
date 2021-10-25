package com.frank.fftalk.util;

import org.webrtc.MediaConstraints;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;

import java.net.URISyntaxException;

public class RTCCenter {
    public static RTCCenter Singleton=new RTCCenter();

    public void init(){
        PeerConnectionFactory.InitializationOptions options = new PeerConnectionFactory.InitializationOptions();
        PeerConnectionFactory.initialize(options);initializeAndroidGlobals(listener, true, true,
                params.videoCodecHwAcceleration, mEGLcontext);
        factory = new PeerConnectionFactory();
        MessageHandler messageHandler = new MessageHandler();

        try {
            client = IO.socket(host);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        client.on("id", messageHandler.onId);
        client.on("message", messageHandler.onMessage);
        client.connect();

        iceServers.add(new PeerConnection.IceServer("stun:23.21.150.121"));
        iceServers.add(new PeerConnection.IceServer("stun:stun.l.google.com:19302"));

        pcConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveAudio", "true"));
        pcConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveVideo", "true"));
        pcConstraints.optional.add(new MediaConstraints.KeyValuePair("DtlsSrtpKeyAgreement", "true"));
    }
}
