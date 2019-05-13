package com.vit.vitapp.ui.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.vit.vitapp.BR;


public class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;
    private BaseOnClickItemListener listener;

    public BaseViewHolder(ViewDataBinding binding, BaseOnClickItemListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bindData(T t) {
        binding.setVariable(BR.item, t);
        if(listener != null) {
            binding.setVariable(BR.listener, listener);
        }
        binding.executePendingBindings();
    }
}
