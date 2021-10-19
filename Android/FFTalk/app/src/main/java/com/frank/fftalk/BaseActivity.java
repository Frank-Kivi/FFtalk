package com.frank.fftalk;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.frank.fftalk.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseActivity<V extends ViewBinding> extends AppCompatActivity {
    protected V viewBinding;

    protected boolean useEventBus;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding= createViewBinding();
        setContentView(viewBinding.getRoot());
        initData();
        useEventBus=useEventBus();
    }

    protected boolean useEventBus() {
        return false;
    }

    protected void initData() {
    }

    protected abstract V createViewBinding();

    @Override
    public void onStart() {
        super.onStart();
        if (useEventBus) {
        EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (useEventBus ) {
        EventBus.getDefault().unregister(this);
        }
    }
}
