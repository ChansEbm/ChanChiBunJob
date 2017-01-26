package com.app.recall.entity;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.app.recall.transform.GlideCircleTransform;
import com.app.recall.util.DensityUtils;
import com.bumptech.glide.Glide;
import com.yancy.gallerypick.utils.ScreenUtils;

/**
 * Created by KenChan on 17/1/24.
 */

public class BindingFiled {
    @BindingAdapter({"loadCircleImage"})
    public static void loadCircleImage(ImageView imageView, String url) {
        if (imageView == null || TextUtils.isEmpty(url)) return;
        Glide.with(imageView.getContext()).load(url).transform(new GlideCircleTransform(imageView
                .getContext())).into(imageView);
    }

    @BindingAdapter({"loadContactInfoAvatar"})
    public static void loadContactInfoAvatar(ImageView imageView, String url) {
        if (imageView == null || TextUtils.isEmpty(url)) return;
        int screenWidth = ScreenUtils.getScreenWidth(imageView.getContext());
        int av = screenWidth / 4;
        Glide.with(imageView.getContext()).load(url).override(av, av).transform(new
                GlideCircleTransform(imageView.getContext())).into(imageView);
    }
}
