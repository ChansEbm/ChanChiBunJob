package com.app.recall.ui.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.recall.R;
import com.app.recall.base.BaseActivity;
import com.app.recall.contract.PersonalInfoContract;
import com.app.recall.manager.GlideImageLoader;
import com.app.recall.pop.IconPop;
import com.app.recall.presenter.PersonalInfoPresenterImpl;
import com.app.recall.util.SDCardUtils;
import com.bumptech.glide.Glide;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.io.File;
import java.util.List;

public class PersonalInfoActivity extends BaseActivity<PersonalInfoPresenterImpl> implements
        IconPop.OnIconSelectCallback, PersonalInfoContract.View {
    private AppCompatEditText realName;
    private String ptoPath = "";

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

    private void openGallery() {
        GalleryConfig galleryConfig = new GalleryConfig.Builder().imageLoader(new
                GlideImageLoader()).iHandlerCallBack(new PersonalInfoActivity.HandlerCallback())
                .filePath(SDCardUtils.cdApplicationSpecialDirectory(this, "Photo")).isOpenCamera
                        (false).build();
        GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(this);
    }

    private void openCamera() {
        GalleryConfig galleryConfig = new GalleryConfig.Builder().imageLoader(new
                GlideImageLoader()).filePath(SDCardUtils.cdApplicationSpecialDirectory(this,
                "Photo")).iHandlerCallBack(new PersonalInfoActivity.HandlerCallback())
                .isOpenCamera(true).build();
        GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(this);
    }

    @Override
    protected PersonalInfoPresenterImpl initPresenter() {
        return new PersonalInfoPresenterImpl();
    }

    @Override
    protected void initViews() {
        realName = (AppCompatEditText) findViewById(R.id.realname);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {
        switch (id) {
            case R.id.confirm_action:
                if (isParamsReady()) {
                    String name = realName.getText().toString();
                    presenter.postAvatar(ptoPath);
                    presenter.postUsername(name);
                }
                break;
            case R.id.avatar:
                IconPop iconPop = new IconPop(this);
                iconPop.setCallback(this);
                iconPop.showAtDefaultLocation();
                break;
        }
    }

    private boolean isParamsReady() {

        if (TextUtils.isEmpty(ptoPath)) {
            Toast.makeText(this, "Please upload avatar", Toast.LENGTH_SHORT).show();
            return false;
        }

        String name = realName.getText().toString();
        if (TextUtils.isEmpty(name) || name.length() < 6) {
            realName.setError("Name error");
            return false;
        }


        return true;
    }

    @Override
    public void networkError() {

    }

    @Override
    public int contentViewId() {
        return R.layout.activity_personal_info;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        toolbar.setTitle("PersonalInfo");
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return true;
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

    @Override
    public void profileUploadSuccess() {

    }


    class HandlerCallback implements IHandlerCallBack {

        @Override
        public void onStart() {

        }

        @Override
        public void onSuccess(List<String> photoList) {
            ptoPath = photoList.get(0);
            ImageView avatar = (ImageView) findViewById(R.id.avatar);
            Glide.with(PersonalInfoActivity.this).load(new File(ptoPath)).override(150, 150).into
                    (avatar);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onFinish() {

        }

        @Override
        public void onError() {
            Toast.makeText(PersonalInfoActivity.this, "Choose photo error", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
