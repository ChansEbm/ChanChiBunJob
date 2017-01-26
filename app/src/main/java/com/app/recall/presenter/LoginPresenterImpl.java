package com.app.recall.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.app.recall.base.APP;
import com.app.recall.contract.LoginContract;
import com.app.recall.entity.RememberUsernamePwdEntity;
import com.app.recall.entity.retrofit.BaseEntity;
import com.app.recall.entity.retrofit.MeEntity;
import com.app.recall.entity.retrofit.UserEntity;
import com.app.recall.manager.ActivityManager;
import com.app.recall.ui.features.HostActivity;
import com.app.recall.ui.login.LoginActivity;
import com.app.recall.ui.login.PhoneSignUpActivity;
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

import java.util.Collections;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by KenChan on 16/12/21.
 */

public class LoginPresenterImpl extends BasePresenter<LoginContract.View> implements
        FacebookCallback<LoginResult> {
    private CallbackManager callbackManager;
    private RememberUsernamePwdEntity rememberUsernamePwdEntity = new RememberUsernamePwdEntity
            (false, "", "");

    public LoginPresenterImpl() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, this);
    }

    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, @What int what) {
        Prefser prefser = new Prefser(activity);

        if (what == CUPCAKE) {//login
            UserEntity login = (UserEntity) response;
            Log.i(TAG, "onSuccessResponse AccessToken: " + login.getToken().getAccessToken());
            prefser.remove(APP.USER_INFO);
            prefser.remove(APP.USER_TOKEN);
            prefser.put(APP.USER_INFO, login.getUser());
            prefser.put(APP.USER_TOKEN, login.getToken());
            Log.e(TAG, "onSuccessResponse: " + getAuthorization());
            call(false).callMe(getAuthorization()).enqueue(new RetrofitObjEnqueue<MeEntity>(DONUT));
        } else if (what == DONUT) {
            MeEntity meEntity = (MeEntity) response;
            prefser.put(APP.ME, meEntity);
            startActivity(HostActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent
                    .FLAG_ACTIVITY_NEW_TASK);
            ActivityManager.getActivityManager().finishActivity(LoginActivity.class);
        }
        prefser.put(APP.SAVE_LOGIN_STATE, rememberUsernamePwdEntity);
    }

    @Override
    void onFailedResponse(String error, int what) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode())
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void facebookSignUp() {
        rememberUsernamePwdEntity.setPlatform(true);
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        if (currentAccessToken != null && !currentAccessToken.isExpired()) {
            facebookLog(currentAccessToken.getUserId(), currentAccessToken.getToken());
        } else
            LoginManager.getInstance().logInWithReadPermissions(activity, Collections
                    .singletonList("public_profile"));
    }

    public void phoneSignUp() {
        startActivity(PhoneSignUpActivity.class, -1);
    }

    public void accountSign(String userName, String password) {
        rememberUsernamePwdEntity.setUserName(userName);
        rememberUsernamePwdEntity.setPassword(password);
        rememberUsernamePwdEntity.setPlatform(false);
        UserEntity.Params src = new UserEntity.Params("", "", password, userName, "", "");
        RequestBody body = RequestBody.create(MEDIA_TYPE, new Gson().toJson(src));
        call(false).callLogin(body).enqueue(new RetrofitObjEnqueue<UserEntity>(CUPCAKE));
    }

    private void facebookLog(String facebookId, String token) {
        UserEntity.Params params = new UserEntity.Params("", facebookId, "", "", token, "");
        RequestBody body = RequestBody.create(MEDIA_TYPE, new Gson().toJson(params));
        call(false).callLogin(body).enqueue(new RetrofitObjEnqueue<UserEntity>(CUPCAKE));
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        String userId = loginResult.getAccessToken().getUserId();
        String token = loginResult.getAccessToken().getToken();
        facebookLog(userId, token);

        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        AccessToken.setCurrentAccessToken(currentAccessToken);
        Profile currentProfile = Profile.getCurrentProfile();
        Profile.setCurrentProfile(currentProfile);

        Log.d(TAG, "onSuccess: " + userId);
        Log.e(TAG, "onSuccess: " + token);

        Log.d(TAG, "onSuccess() returned: " + (AccessToken.getCurrentAccessToken() == null) + "," +
                "profile:" + Profile.getCurrentProfile());

        //        loginBy(LoginContract.Presenter.FACEBOOK, userId, token);
        //        Log.i(TAG, "onSuccess:tttttt-----> " + token);
        //        if (loginResult.getAccessToken() != null) {
        //            //可以直接利用上面注释的来直接获取id 但是需要获取特殊信息 例如Location的时候 就需要用这句来获取
        //            GraphRequest.newMeRequest(loginResult.getAccessToken(), this).executeAsync();
        //        }
    }


    @Override
    public void onCancel() {
        Toast.makeText(activity, "facebook sign up failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        Toast.makeText(activity, "facebook sign up error", Toast.LENGTH_SHORT).show();
        error.printStackTrace();
        String message = error.getMessage();
        Logger.e(message);
    }
}
