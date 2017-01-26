package com.app.recall.manager;

import android.app.Activity;
import android.content.Context;

import com.app.recall.R;
import com.bumptech.glide.Glide;
import com.yancy.gallerypick.inter.ImageLoader;
import com.yancy.gallerypick.widget.GalleryImageView;

/**
 * Created by KenChan on 17/1/3.
 */

public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, Context context, String path, GalleryImageView
            galleryImageView, int width, int height) {
        Glide.with(context).load(path).placeholder(R.mipmap.gallery_pick_photo).centerCrop().into
                (galleryImageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
