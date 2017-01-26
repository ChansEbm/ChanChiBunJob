package com.app.recall.pop;

import android.Manifest;
import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.app.recall.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by KenChan on 17/1/3.
 */

public class IconPop extends BasePopupWindow {
    public static final int CAMERA = 93;
    public static final int ALBUM = 391;
    private OnIconSelectCallback callback = null;

    public IconPop(Context context) {
        super(context);
        rootView.findViewById(R.id.camera).setOnClickListener(this);
        rootView.findViewById(R.id.album).setOnClickListener(this);
        rootView.findViewById(R.id.cancel).setOnClickListener(this);
    }

    @Override
    public int getPopupLayout() {
        return R.layout.pop_icon;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                if (checkCameraPermission()) {
                    if (callback != null) callback.callback(CAMERA);
                } else
                    ActivityCompat.requestPermissions(appCompatActivity, new String[]{Manifest
                            .permission.CAMERA}, CAMERA);
                break;
            case R.id.album:
                if (checkAlbumPermission()) {
                    if (callback != null) callback.callback(ALBUM);

                } else
                    ActivityCompat.requestPermissions(appCompatActivity, new String[]{Manifest
                            .permission.READ_EXTERNAL_STORAGE}, ALBUM);
                break;
        }
        dismiss();
    }


    public void setCallback(OnIconSelectCallback callback) {
        this.callback = callback;
    }

    @IntDef({CAMERA, ALBUM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Icon {

    }

    public interface OnIconSelectCallback {
        void callback(@Icon int what);
    }
}
