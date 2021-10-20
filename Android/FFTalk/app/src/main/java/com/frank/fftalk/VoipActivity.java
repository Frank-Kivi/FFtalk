package com.frank.fftalk;

import static com.frank.fftalk.ChatActivity.FriendName;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class VoipActivity extends AppCompatActivity {
    public static void start(Context context,String name) {
        Intent starter = new Intent(context,VoipActivity .class);
        starter.putExtra(FriendName,name);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voip);
    }
}