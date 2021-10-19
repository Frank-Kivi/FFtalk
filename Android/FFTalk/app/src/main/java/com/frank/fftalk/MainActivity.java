package com.frank.fftalk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frank.fftalk.databinding.ActivityMain2Binding;
import com.frank.fftalk.databinding.MainItemBinding;
import com.frank.fftalk.util.IMCenter;
import com.frank.fftalk.util.MessageEvent;
import com.frank.fftalk.util.MyAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMain2Binding> {


    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }
    MyAdapter<String,MainItemBinding> myAdapter;
    @Override
    protected void initData() {
        super.initData();
        myAdapter= new MyAdapter<String, MainItemBinding>() {
            @Override
            protected ViewHolder<String, MainItemBinding> createViewHolder(ViewGroup viewGroup) {
                return new ViewHolder<String, MainItemBinding>(MainItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()))) {

                    @Override
                    public void setData(String s) {
                        this.viewBinding.name.setText(s);
                        this.viewBinding.name.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ChatActivity.start(MainActivity.this,s);
                            }
                        });
                    }
                };
            }
        };
        viewBinding.listView.setAdapter(myAdapter);
        myAdapter.setDatas(IMCenter.Singleton.getOnlineUsers());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {if (event.type== MessageEvent.Type.OnlineUserChange) {
        myAdapter.setDatas((List<String>) event.data);
    }};

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    protected ActivityMain2Binding createViewBinding() {
        return ActivityMain2Binding.inflate(getLayoutInflater());
    }

}