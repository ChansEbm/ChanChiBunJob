package com.app.recall.presenter;

import android.widget.Toast;

import com.app.recall.contract.CodeVerificationContract;
import com.app.recall.entity.retrofit.BaseEntity;

import java.util.List;

/**
 * Created by KenChan on 2016/12/19
 */

public class CodeVerificationPresenterImpl extends BasePresenter<CodeVerificationContract.View> {

    public void sendVerificationCode(String phone) {
        call(false).callVerificationCode("86" + phone).enqueue(new RetrofitObjEnqueue<>(CUPCAKE));
    }

    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, int what) {
        Toast.makeText(activity, "Sent success", Toast.LENGTH_SHORT).show();
    }

    @Override
    void onFailedResponse(String error, int what) {

    }
}