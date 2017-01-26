package com.app.recall.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.app.recall.R;
import com.app.recall.drawable.MaterialProgressDrawable;

/**
 * Created by KenChan on 16/12/19.
 */

public class LoadingDialog extends Dialog {
    private MaterialProgressDrawable drawable;

    public LoadingDialog(Context context) {
        super(context, R.style.dialogDefaultStyle);
        View loadingView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null,
                                                                false);
        setContentView(loadingView);
        ImageView imageView = (ImageView) loadingView.findViewById(R.id.image);
        drawable = new MaterialProgressDrawable(context, imageView);
        drawable.setBackgroundColor(0xFFFAFAFA);
        drawable.setColorSchemeColors(ContextCompat.getColor(context, R.color.colorAccent),
                                      ContextCompat.getColor(context, R.color.colorPrimaryDark),
                                      ContextCompat.getColor(context, R.color.black_overlay));
        drawable.setProgressRotation(0.9f);
        drawable.setStartEndTrim(0f, 0.9f);
        drawable.setAlpha(255);
        drawable.updateSizes(MaterialProgressDrawable.LARGE);
        imageView.setImageDrawable(drawable);
    }

    @Override
    public void show() {
        super.show();
        if (!drawable.isRunning()) { drawable.start(); }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (drawable.isRunning()) { drawable.stop(); }
    }
}
