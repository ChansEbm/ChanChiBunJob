package com.app.recall.presenter;

import com.app.recall.contract.PhoneSignUpContract;
import com.app.recall.entity.retrofit.BaseEntity;

import java.util.List;

/**
 * Created by KenChan on 2016/12/16
 */

public class PhoneSignUpPresenterImpl extends BasePresenter<PhoneSignUpContract.View> {

    public void sendVerificationCode(String phone) {
        call(false).callVerificationCode("86" + phone).enqueue(new RetrofitObjEnqueue<>(CUPCAKE));
    }

    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, int what) {
        if (iView != null) {
            iView.sentVerificationSuccess();
        }
    }

    @Override
    void onFailedResponse(String error, int what) {

    }

}