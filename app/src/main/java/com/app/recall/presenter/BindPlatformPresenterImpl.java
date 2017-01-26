package com.app.recall.presenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.app.recall.base.APP;
import com.app.recall.contract.BindPlatformContract;
import com.app.recall.entity.retrofit.BaseEntity;
import com.app.recall.entity.retrofit.PlatformsEntity;
import com.app.recall.entity.retrofit.params.Platforms;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.github.pwittchen.prefser.library.Prefser;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by KenChan on 2017/01/26
 */

public class BindPlatformPresenterImpl extends BasePresenter<BindPlatformContract.View>
        implements BindPlatformContract.Presenter {

    private Prefser prefser;
    private int what = -1;

    @Override
    public void onAttach(AppCompatActivity appCompatActivity) {
        super.onAttach(appCompatActivity);
        prefser = new Prefser(appCompatActivity);
    }

    @Override
    public void titleWhat(int what, Toolbar toolbar) {
        switch (what) {
            case APP.EMAIL_WHAT:
                toolbar.setTitle("Bind Email");
                break;
            case APP.FACEBOOK_WHAT:
                toolbar.setTitle("Bind Facebook");
                break;
            case APP.TWITTER_WHAT:
                toolbar.setTitle("Bind Twitter");
                break;
            case APP.GOOGLE_WHAT:
                toolbar.setTitle("Bind Google+");
                break;
            case APP.QQ_WHAT:
                toolbar.setTitle("Bind QQ");
                break;
            case APP.WECHAT_WHAT:
                toolbar.setTitle("Bind Wechat");
                break;
            case APP.PHONE_WHAT:
                toolbar.setTitle("Bind Phone");
                break;
            case APP.WHATSAPP_WHAT:
                toolbar.setTitle("Bind Whatsapp");
                break;
        }
    }

    @Override
    public void setupPlatform(int what) {
        this.what = what;
        switch (what) {
            case APP.FACEBOOK_WHAT:
                new FacebookPlatform().facebookAuth();
                break;
            case APP.GOOGLE_WHAT:

                break;
        }
    }

    public void uploadPlatform(String name, String username, String token) {
        Logger.i(getAuthorization());
        List<Platforms> platforms = new ArrayList<>();
        platforms.add(new Platforms(name, username, token));
        String plat = new Gson().toJson(platforms);

        RequestBody body = RequestBody.create(MEDIA_TYPE, plat);
        call(false).callUploadPlatforms(body, getAuthorization()).enqueue(new
                RetrofitArrayEnqueue<PlatformsEntity>());
    }

    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, int what) {

        if (what == ARRAY_CUPCAKE) {
            List<PlatformsEntity> platforms = new ArrayList<>();
            platforms.addAll((Collection<? extends PlatformsEntity>) entities);
            prefser.put(APP.PLATFORMS, platforms);
            Log.i(TAG, "onSuccessResponse: " + entities.size());
            if (iView != null) iView.uploadPlatformSuccess(this.what);
        }
    }

    @Override
    void onFailedResponse(String error, int what) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()) {
            new FacebookPlatform().onActivityResult(requestCode, resultCode, data);
        }
    }

    private class FacebookPlatform implements FacebookCallback<LoginResult> {
        CallbackManager callbackManager = CallbackManager.Factory.create();

        FacebookPlatform() {
            LoginManager.getInstance().registerCallback(callbackManager, this);
        }

        void facebookAuth() {
            AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            if (currentAccessToken != null && !currentAccessToken.isExpired()) {

            } else
                LoginManager.getInstance().logInWithReadPermissions(activity, Collections
                        .singletonList("public_profile"));
        }

        void onActivityResult(int requestCode, int resultCode, Intent data) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            AccessToken.setCurrentAccessToken(accessToken);

            Profile currentProfile = Profile.getCurrentProfile();
            Profile.setCurrentProfile(currentProfile);

            String name = currentProfile.getName();
            uploadPlatform("facebook", name, accessToken.getToken());
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    }


}