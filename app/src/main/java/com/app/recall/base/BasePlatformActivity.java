package com.app.recall.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.app.recall.entity.platform.GooglePlatformEntity;
import com.app.recall.entity.platform.PlatformEntity;
import com.app.recall.entity.platform.QQProfilePlatformEntity;
import com.app.recall.entity.platform.TwitterProfilePlatformEntity;
import com.app.recall.entity.platform.WXProfilePlatformEntity;
import com.app.recall.presenter.IPresenter;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.github.pwittchen.prefser.library.Prefser;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.twitter.Twitter;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by KenChan on 17/1/11.
 */

public abstract class BasePlatformActivity<IP extends IPresenter> extends BaseActivity<IP>
        implements PlatformActionListener {
    private GoogleClientLog google;
    private FacebookLog facebook;
    private static final int GOOGLE_REQUEST_KEY = 167;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        google = new GoogleClientLog();
        facebook = new FacebookLog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        google.destroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_REQUEST_KEY && resultCode == RESULT_OK) {
            google.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode())
        {//facebook
            facebook.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(this, "You canceled the SSO platform", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        String exportData = platform.getDb().exportData();
        Prefser prefser = new Prefser(this);
        if (TextUtils.equals(Twitter.NAME, platform.getName())) {
            TwitterProfilePlatformEntity twitterProfilePlatform = new Gson().fromJson(exportData,
                    TwitterProfilePlatformEntity.class);
            twitterProfilePlatform.setAuth(platform.isAuthValid());
            prefser.put(APP.TWITTER, twitterProfilePlatform);
        } else if (TextUtils.equals(Wechat.NAME, platform.getName())) {
            WXProfilePlatformEntity wxProfilePlatformEntity = new Gson().fromJson(exportData,
                    WXProfilePlatformEntity.class);
            wxProfilePlatformEntity.setAuth(platform.isAuthValid());
            prefser.put(APP.WECHAT, wxProfilePlatformEntity);
        } else if (TextUtils.equals(QQ.NAME, platform.getName())) {
            QQProfilePlatformEntity qqProfilePlatformEntity = new Gson().fromJson(exportData,
                    QQProfilePlatformEntity.class);
            qqProfilePlatformEntity.setAuth(platform.isAuthValid());
            prefser.put(APP.TENCENT, qqProfilePlatformEntity);
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        String name = platform.getName();
        Toast.makeText(this, name + "proceed error!", Toast.LENGTH_SHORT).show();
    }

    protected boolean auth(String name) {
        if (TextUtils.equals(name, APP.GOOGLEPLUS)) {
            return google.logIn();
        } else if (TextUtils.equals(name, APP.FACEBOOK)) {
            return facebook.log();
        } else {
            Platform platform = ShareSDK.getPlatform(this, name);
            platform.setPlatformActionListener(this);
            if (!platform.isAuthValid()) platform.showUser(null);
            return platform.isAuthValid();
        }
    }

    protected abstract void onPlatform(String userName, String token);

    class GoogleClientLog implements GoogleApiClient.OnConnectionFailedListener {
        private GoogleSignInOptions options;
        private GoogleApiClient client;

        GoogleClientLog() {
            options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestProfile().build();
            client = new GoogleApiClient.Builder(BasePlatformActivity.this).addApi(Auth
                    .GOOGLE_SIGN_IN_API, options).enableAutoManage(BasePlatformActivity.this,
                    this).build();
            client.connect();
        }

        boolean logIn() {
            OptionalPendingResult<GoogleSignInResult> pendingResult = Auth.GoogleSignInApi
                    .silentSignIn(client);
            if (pendingResult.isDone()) {
                handleResult(pendingResult.get());
            } else {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(client);
                startActivityForResult(signInIntent, GOOGLE_REQUEST_KEY);
            }
            return pendingResult.isDone();
        }

        void destroy() {
            if (client != null) {
                client.disconnect();
                client.stopAutoManage(BasePlatformActivity.this);
            }
        }

        private void handleResult(GoogleSignInResult result) {
            if (result.isSuccess()) {
                GoogleSignInAccount signInAccount = result.getSignInAccount();
                if (signInAccount == null) {
                    Toast.makeText(BasePlatformActivity.this, "Get your google profile failed",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                String email = signInAccount.getEmail();
                String id = signInAccount.getId();
                GooglePlatformEntity entity = new GooglePlatformEntity(email, id);
                new Prefser(BasePlatformActivity.this).put(APP.GOOGLEPLUS, entity);
                BasePlatformActivity.this.onPlatform(signInAccount.getEmail(), signInAccount
                        .getIdToken());
            } else {
                Toast.makeText(BasePlatformActivity.this, "Get your google profile failed", Toast
                        .LENGTH_SHORT).show();
            }
        }

        void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == GOOGLE_REQUEST_KEY && resultCode == RESULT_OK) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleResult(result);
            }
        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Toast.makeText(BasePlatformActivity.this, "Google sign in connection failed", Toast
                    .LENGTH_SHORT).show();
        }
    }

    class FacebookLog implements FacebookCallback<LoginResult> {
        private CallbackManager callbackManager;

        FacebookLog() {
            callbackManager = CallbackManager.Factory.create();
            LoginManager.getInstance().registerCallback(callbackManager, this);
        }

        boolean log() {
            Profile currentProfile = Profile.getCurrentProfile();
            AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            if (currentProfile != null && currentAccessToken != null && !currentAccessToken
                    .isExpired()) {
                PlatformEntity entity = new PlatformEntity();
                entity.setName(currentProfile.getName());
                new Prefser(BasePlatformActivity.this).put(APP.FACEBOOK, entity);
                BasePlatformActivity.this.onPlatform(currentProfile.getName(), currentAccessToken
                        .getToken());
            } else {
                LoginManager.getInstance().logInWithReadPermissions(BasePlatformActivity.this,
                        Collections.singletonList("public_profile"));
            }
            return currentProfile != null;
        }

        void onActivityResult(int requestCode, int resultCode, Intent data) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken.setCurrentAccessToken(AccessToken.getCurrentAccessToken());
            Profile.setCurrentProfile(Profile.getCurrentProfile());
            BasePlatformActivity.this.onPlatform(Profile.getCurrentProfile().getName(),
                    AccessToken.getCurrentAccessToken().getToken());
        }

        @Override
        public void onCancel() {
            Toast.makeText(BasePlatformActivity.this, "Facebook auth cancel", Toast.LENGTH_SHORT)
                    .show();
        }

        @Override
        public void onError(FacebookException error) {
            Toast.makeText(BasePlatformActivity.this, "Facebook auth error", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
