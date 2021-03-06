package com.frank.fftalk;

import android.view.View;
import android.widget.Toast;

import com.frank.fftalk.databinding.ActivityMainBinding;
import com.frank.fftalk.util.MessageEvent;
import com.frank.fftalk.util.Msg;
import com.frank.fftalk.util.ServerCenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends BaseActivity<ActivityMainBinding> {

    public void login(View view) {
        String s = dataBinding.userName.getText().toString().trim();
        ServerCenter.Singleton.init();
        ServerCenter.Singleton.login(s);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        dataBinding.userName.setText("66666");
    }


    private static final String TAG = "LoginActivity";

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.type== MessageEvent.Type.LoginResponse) {
            Msg.LoginStatus loginStatus= (Msg.LoginStatus) event.data;
            if (loginStatus== Msg.LoginStatus.Success){
                MainActivity.start(this);
            }else {
                Toast.makeText(this, "登录失败，稍后重试", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected boolean enableEventBus() {
        return true;
    }
}