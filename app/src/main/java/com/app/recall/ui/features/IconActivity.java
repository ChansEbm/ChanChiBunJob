package com.app.recall.ui.features;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.recall.R;
import com.app.recall.base.BaseActivity;
import com.app.recall.manager.GlideImageLoader;
import com.app.recall.pop.IconPop;
import com.app.recall.presenter.IconPresenterImpl;
import com.app.recall.util.DensityUtils;
import com.app.recall.util.SDCardUtils;
import com.bumptech.glide.Glide;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.io.File;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

public class IconActivity extends BaseActivity<IconPresenterImpl> implements IconPop
        .OnIconSelectCallback {
    private PhotoView photoView;
    private IconPop iconPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == IconPop.CAMERA) {
            if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                openCamera();
            }
        } else if (requestCode == IconPop.ALBUM) {
            if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                openGallery();
            }
        }
        presenter.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected IconPresenterImpl initPresenter() {
        return new IconPresenterImpl();
    }

    @Override
    protected void initViews() {
        photoView = (PhotoView) findViewById(R.id.photoView);
        int dip = DensityUtils.dip2px(this, 300);
        Glide.with(this).load("http://pic10.nipic.com/20101103/5063545_000227976000_2.jpg")
                .override(dip, dip).into(photoView);

        iconPop = new IconPop(this);
        iconPop.setCallback(this);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {

    }

    @Override
    public void networkError() {

    }

    @Override
    public int contentViewId() {
        return R.layout.activity_icon;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        toolbar.setTitle("Icon");
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_edit) {
            iconPop.showAtDefaultLocation();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void callback(@IconPop.Icon int what) {
        switch (what) {
            case IconPop.ALBUM:
                openGallery();
                break;
            case IconPop.CAMERA:
                openCamera();
                break;
        }
    }

    private void openGallery() {
        GalleryConfig galleryConfig = new GalleryConfig.Builder().imageLoader(new
                GlideImageLoader()).iHandlerCallBack(new HandlerCallback()).filePath(SDCardUtils
                .cdApplicationSpecialDirectory(this, "Photo")).isOpenCamera(false).build();
        GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(this);
    }

    private void openCamera() {
        GalleryConfig galleryConfig = new GalleryConfig.Builder().imageLoader(new
                GlideImageLoader()).filePath(SDCardUtils.cdApplicationSpecialDirectory(this,
                "Photo")).iHandlerCallBack(new HandlerCallback()).isOpenCamera(true).build();
        GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(this);
    }

    class HandlerCallback implements IHandlerCallBack {

        @Override
        public void onStart() {

        }

        @Override
        public void onSuccess(List<String> photoList) {
            String path = photoList.get(0);
            presenter.postIcon(path);

            int density = DensityUtils.dip2px(IconActivity.this, 300);
            Glide.with(IconActivity.this).load(new File(path)).override(density, density).into
                    (photoView);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onFinish() {

        }

        @Override
        public void onError() {
            Toast.makeText(IconActivity.this, "Choose photo error", Toast.LENGTH_SHORT).show();
        }
    }
}
