package com.frank.fftalk;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.frank.fftalk.util.ServerCenter;

public class MainActivity extends AppCompatActivity implements ServerCenter.OnConnectStateChangeListener {

    private EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void login(View view) {
        String s = userName.getText().toString().trim();
        ServerCenter.Singleton.init();
        ServerCenter.Singleton.setOnConnectStateChangeListener(this);
        ServerCenter.Singleton.login(s);
    }

    private void initView() {
        userName = (EditText) findViewById(R.id.userName);
    }

    @Override
    public void onConnectStateChange(ConnectState connectState) {
        if (connectState==ConnectState.Success){

        }
    }
}