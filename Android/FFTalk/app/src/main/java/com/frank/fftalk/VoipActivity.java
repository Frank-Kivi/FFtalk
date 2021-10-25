package com.frank.fftalk;

import static com.frank.fftalk.ChatActivity.FriendName;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.frank.fftalk.databinding.ActivityVoipBinding;

public class VoipActivity extends BaseActivity<ActivityVoipBinding> {
    public static void start(Context context,String name) {
        Intent starter = new Intent(context,VoipActivity .class);
        starter.putExtra(FriendName,name);
        context.startActivity(starter);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_voip;
    }
}