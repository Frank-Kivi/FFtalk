package com.frank.fftalk.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.List;

public abstract class MyAdapter<T,V extends ViewBinding> extends BaseAdapter {
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
            view = viewHolder.viewBinding.getRoot();
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.setData(datas.get(i));
        return view;
    }

    protected abstract ViewHolder<T, V> createViewHolder(ViewGroup viewGroup);

    public abstract class ViewHolder<T,V extends ViewBinding> {
        protected V viewBinding;

        public ViewHolder(V viewBinding) {
            this.viewBinding = viewBinding;
        }

        public abstract void setData(T t);

    }
}
