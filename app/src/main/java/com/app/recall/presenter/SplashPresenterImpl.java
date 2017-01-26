package com.app.recall.presenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.app.recall.base.APP;
import com.app.recall.contract.SplashContract;
import com.app.recall.entity.RememberUsernamePwdEntity;
import com.app.recall.entity.retrofit.BaseEntity;
import com.app.recall.entity.retrofit.MeEntity;
import com.app.recall.entity.retrofit.UserEntity;
import com.app.recall.manager.ActivityManager;
import com.app.recall.ui.features.HostActivity;
import com.app.recall.ui.login.LoginActivity;
import com.facebook.AccessToken;
import com.github.pwittchen.prefser.library.Prefser;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by KenChan on 2016/12/16
 */

public class SplashPresenterImpl extends BasePresenter<SplashContract.View> {
    private Prefser prefser;
    private RememberUsernamePwdEntity rememberUsernamePwdEntity;


    @Override
    public void onAttach(AppCompatActivity appCompatActivity) {
        super.onAttach(appCompatActivity);
        prefser = new Prefser(activity);
        rememberUsernamePwdEntity = prefser.get(APP.SAVE_LOGIN_STATE, RememberUsernamePwdEntity
                .class, new RememberUsernamePwdEntity(false, "", ""));
    }

    public boolean checkState() {
        if (!prefser.contains(APP.SAVE_LOGIN_STATE)) {
            return false;
        }
        if (rememberUsernamePwdEntity.isPlatform()) {
            AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            if (currentAccessToken == null || currentAccessToken.isExpired()) {
                Toast.makeText(activity, "Maybe your facebook id is expired!", Toast
                        .LENGTH_SHORT).show();
            }
            return currentAccessToken != null && !currentAccessToken.isExpired();
        } else {
            return (!TextUtils.isEmpty(rememberUsernamePwdEntity.getUserName()) && !TextUtils
                    .isEmpty(rememberUsernamePwdEntity.getPassword()));
        }
    }

    public void login() {
        if (rememberUsernamePwdEntity.isPlatform()) {
            platformLogin();
        } else {
            userNameLogin();
        }
    }

    private void platformLogin() {
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        String userId = currentAccessToken.getUserId();
        String token = currentAccessToken.getToken();
        UserEntity.Params params = new UserEntity.Params("", userId, "", "", token, "");
        RequestBody body = RequestBody.create(MEDIA_TYPE, new Gson().toJson(params));
        call(true).callLogin(body).enqueue(new RetrofitObjEnqueue<UserEntity>(CUPCAKE));
    }

    private void userNameLogin() {
        String userName = rememberUsernamePwdEntity.getUserName();
        String password = rememberUsernamePwdEntity.getPassword();
        UserEntity.Params src = new UserEntity.Params("", "", password, userName, "", "");
        RequestBody body = RequestBody.create(MEDIA_TYPE, new Gson().toJson(src));
        call(true).callLogin(body).enqueue(new RetrofitObjEnqueue<UserEntity>(CUPCAKE));
    }

    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, @What int what) {
        Prefser prefser = new Prefser(activity);
        if (what == CUPCAKE) {
            UserEntity login = (UserEntity) response;
            Log.i(TAG, "onSuccessResponse AccessToken: " + login.getToken().getAccessToken());
            prefser.remove(APP.USER_INFO);
            prefser.remove(APP.USER_TOKEN);
            prefser.put(APP.USER_INFO, login.getUser());
            prefser.put(APP.USER_TOKEN, login.getToken());
            //            startActivity(HostActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent
            //                    .FLAG_ACTIVITY_NEW_TASK);
            //            ActivityManager.getActivityManager().finishActivity(LoginActivity.class);
            Log.e(TAG, "onSuccessResponse: " + getAuthorization());
            call(false).callMe(getAuthorization()).enqueue(new RetrofitObjEnqueue<MeEntity>(DONUT));
        } else if (what == DONUT) {
            MeEntity meEntity = (MeEntity) response;
            prefser.put(APP.ME, meEntity);
            startActivity(HostActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent
                    .FLAG_ACTIVITY_NEW_TASK);
            ActivityManager.getActivityManager().finishActivity(LoginActivity.class);
        }
    }

    @Override
    void onFailedResponse(String error, int what) {

    }
}