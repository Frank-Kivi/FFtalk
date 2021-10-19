package com.frank.fftalk.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class MyAdapter<T,V extends ViewDataBinding> extends BaseAdapter {
    List<T> datas = new ArrayList<>();

    public void setDatas(List<T> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = createViewHolder(viewGroup);
            view = viewHolder.initView(viewGroup,i);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.setData(datas.get(i));
        return view;
    }

    protected abstract ViewHolder<T, V> createViewHolder(ViewGroup viewGroup);

    public abstract class ViewHolder<T,V extends ViewDataBinding> {
        protected V dataBinding;
        public View initView(ViewGroup viewGroup,int i){
            dataBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), getLayoutId(), viewGroup, false);
            return dataBinding.getRoot();
        }

        protected abstract int getLayoutId();

        public abstract void setData(T t);

    }
}
