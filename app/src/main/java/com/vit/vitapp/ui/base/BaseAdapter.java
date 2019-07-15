package com.vit.vitapp.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseAdapter<T> extends ListAdapter<T, BaseViewHolder<T>> {

    protected BaseAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(viewType, viewGroup, false);
        ViewDataBinding binding = DataBindingUtil.bind(view);
        return new BaseViewHolder<>(binding, getListener());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> holder, int i) {
        holder.bindData(getItem(i));
    }

    @Override
    public abstract int getItemViewType(int position);

    public abstract BaseOnClickItemListener getListener();
}
