package com.app.recall.model;

import android.content.Context;
import android.text.TextUtils;

import com.app.recall.base.APP;
import com.app.recall.contract.ChangePasswordContract;
import com.app.recall.entity.retrofit.UserEntity;
import com.github.pwittchen.prefser.library.Prefser;

/**
 * Created by KenChan on 2016/12/24
 */

public class ChangePasswordModelImpl implements ChangePasswordContract.Model {


    @Override
    public boolean checkPwd(String pwd) {
        return !TextUtils.isEmpty(pwd) && pwd.length() >= 6;
    }

    @Override
    public boolean pwdEquals(String pwd, String confirm) {
        return TextUtils.equals(pwd, confirm);
    }

    @Override
    public String getAuthorization(Context context) {
        return new Prefser(context).get(APP.USER_TOKEN, UserEntity.TokenBean.class, new
                UserEntity.TokenBean()).getAccessToken();
    }
}