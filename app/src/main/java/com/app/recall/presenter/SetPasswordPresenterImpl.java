package com.app.recall.presenter;

import android.content.Intent;
import android.widget.Toast;

import com.app.recall.base.APP;
import com.app.recall.contract.SetPasswordContract;
import com.app.recall.entity.retrofit.BaseEntity;
import com.app.recall.entity.retrofit.params.Password;
import com.app.recall.entity.retrofit.PhoneSignUpSuccessEntity;
import com.app.recall.ui.login.AllPlatformActivity;
import com.github.pwittchen.prefser.library.Prefser;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by KenChan on 2016/12/19
 */

public class SetPasswordPresenterImpl extends BasePresenter<SetPasswordContract.View> {

    public void signUpWithPhone(String phone, String code, String password) {
        //        clearAllParams().put("password", password).put("code", code);
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE, new Gson().toJson(new Password
                (code, password)));
        call(false).callPhoneSignUp("86" + phone, requestBody).enqueue(new RetrofitObjEnqueue<PhoneSignUpSuccessEntity>(CUPCAKE));
    }


    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, @What int what) {
        PhoneSignUpSuccessEntity successEntity = (PhoneSignUpSuccessEntity) response;
        Toast.makeText(activity, "Sign up success", Toast.LENGTH_SHORT).show();
        startActivity(AllPlatformActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                .FLAG_ACTIVITY_CLEAR_TASK);
        Prefser prefser = new Prefser(activity.getApplicationContext());
        prefser.put(APP.SIGNUPINFO, successEntity);
    }

    @Override
    void onFailedResponse(String error, int what) {

    }
}