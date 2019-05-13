package com.vit.vitapp.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    private List<T> list = new ArrayList();

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
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
        holder.bindData(list.get(i));
    }

    @Override
    public abstract int getItemViewType(int position);

    @Override
    public int getItemCount() {
        return list.size();
    }

    public abstract BaseOnClickItemListener getListener();
}
