package com.vit.vitapp.utils;

import android.widget.ImageView;
import com.vit.vitapp.utils.GlideApp;


public class BindingAdapter {

    @android.databinding.BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        GlideApp.with(imageView.getContext())
                .load(imageUrl)
                .circleCrop()
                .into(imageView);
    }
}
