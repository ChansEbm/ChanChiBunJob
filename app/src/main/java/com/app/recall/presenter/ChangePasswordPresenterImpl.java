package com.app.recall.presenter;

import android.util.Log;
import android.widget.Toast;

import com.app.recall.contract.ChangePasswordContract;
import com.app.recall.entity.retrofit.BaseEntity;
import com.app.recall.model.ChangePasswordModelImpl;

import java.util.List;

/**
 * Created by KenChan on 2016/12/24
 */

public class ChangePasswordPresenterImpl extends BasePresenter<ChangePasswordContract.View> {
    private ChangePasswordContract.Model model = new ChangePasswordModelImpl();


    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, int what) {
        Toast.makeText(activity, "Changed password success", Toast.LENGTH_SHORT).show();
    }

    @Override
    void onFailedResponse(String error, int what) {

    }

    public boolean checkPwd(String pwd) {
        return model.checkPwd(pwd);
    }

    public boolean pwdEquals(String pwd, String confirm) {
        return model.pwdEquals(pwd, confirm);
    }

    public void callPassword(String originPass, String password) {
        String authorization = model.getAuthorization(activity);
        Log.i(TAG, "callPassword: " + authorization);
        call(false).callPassword(new Pass(originPass, password), authorization).enqueue(new RetrofitObjEnqueue<>());
    }

    public class Pass {
        String oldPassword;
        String newPassword;

        Pass(String oldPassword, String newPassword) {
            this.oldPassword = oldPassword;
            this.newPassword = newPassword;
        }
    }
}