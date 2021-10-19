package com.frank.fftalk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewbinding.ViewBinding;

import com.frank.fftalk.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class BaseActivity<V extends ViewDataBinding> extends AppCompatActivity {
    protected V dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding= DataBindingUtil.setContentView(this,getLayoutId());
        initData();
    }

    protected abstract int getLayoutId();

    protected boolean enableEventBus() {
        return false;
    }

    protected void initData() {
    }


    @Override
    public void onStart() {
        super.onStart();
        if (enableEventBus()) {
        EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (enableEventBus() ) {
        EventBus.getDefault().unregister(this);
        }
    }
}
