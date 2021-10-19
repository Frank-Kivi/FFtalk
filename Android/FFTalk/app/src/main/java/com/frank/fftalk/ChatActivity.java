package com.frank.fftalk;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.frank.fftalk.databinding.ActivityChatBinding;

public class ChatActivity extends BaseActivity<ActivityChatBinding> {
    public static final String FriendName="FriendName";
    private String friend;


    public static void start(Context context,String name) {
        Intent starter = new Intent(context, ChatActivity.class);
        starter.putExtra(FriendName,name);
        context.startActivity(starter);
    }

    @Override
    protected void initData() {
        super.initData();
        friend = getIntent().getStringExtra(FriendName);
        Log.i(TAG, "initData: "+friend);
    }

    private static final String TAG = "ChatActivity";

    @Override
    protected ActivityChatBinding createViewBinding() {
        return ActivityChatBinding.inflate(getLayoutInflater());
    }
}