package com.frank.fftalk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.frank.fftalk.databinding.ActivityChatBinding;
import com.frank.fftalk.databinding.ImItemBinding;
import com.frank.fftalk.util.IMCenter;
import com.frank.fftalk.util.MessageEvent;
import com.frank.fftalk.util.Msg;
import com.frank.fftalk.util.MyAdapter;
import com.frank.fftalk.util.ServerCenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class ChatActivity extends BaseActivity<ActivityChatBinding> {
    public static final String FriendName="FriendName";
    private String friend;
    private MyAdapter<Msg.IMMsg, ImItemBinding> myAdapter;


    public static void start(Context context,String name) {
        Intent starter = new Intent(context, ChatActivity.class);
        starter.putExtra(FriendName,name);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initData() {
        super.initData();
        friend = getIntent().getStringExtra(FriendName);
        dataBinding.friendName.setText(friend);
        Log.i(TAG, "initData: "+friend);
        myAdapter = new MyAdapter<Msg.IMMsg, ImItemBinding>() {
            @Override
            protected ViewHolder<Msg.IMMsg, ImItemBinding> createViewHolder(ViewGroup viewGroup) {
                return new ViewHolder<Msg.IMMsg, ImItemBinding>() {
                    @Override
                    protected int getLayoutId() {
                        return R.layout.im_item;
                    }

                    @Override
                    public void setData(Msg.IMMsg imMsg) {
                        dataBinding.setBean(imMsg);
                    }
                };
            }
        };
        dataBinding.listView.setAdapter(myAdapter);
    }

    private static final String TAG = "ChatActivity";
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.type== MessageEvent.Type.IM) {
            Msg.IMMsg imMsg= (Msg.IMMsg) event.data;
            if (imMsg.from.equals(friend)) {
            myAdapter.addData(imMsg);
            }
        }
    };
    @Override
    protected boolean enableEventBus() {
        return true;
    }

    public void voip(View view) {
        VoipActivity.start(this,friend);
    }

    public void send(View view) {
        String content=dataBinding.content.getText().toString().trim();
        if (!TextUtils.isEmpty(content)) {
            dataBinding.content.setText("");
            Msg.IMMsg imMsg=new Msg.IMMsg(ServerCenter.Singleton.userName, friend,content);
            IMCenter.Singleton.send(imMsg);
            myAdapter.addData(imMsg);
        }
    }
}