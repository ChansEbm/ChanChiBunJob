package com.app.recall.presenter;

import com.app.recall.contract.SignUpContract;
import com.app.recall.entity.retrofit.BaseEntity;
import com.app.recall.model.SignUpModelImpl;

import java.util.List;

/**
 * Created by KenChan on 2016/12/16
 */

public class EmailSignUpPresenterImpl extends BasePresenter<SignUpContract.View> {
    private SignUpContract.Model model = new SignUpModelImpl();


    public void signUp(String email, String password) {

    }

    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, int what) {

    }

    @Override
    void onFailedResponse(String error, int what) {

    }


    public boolean isEmailValid(String email) {
        return model.isEmailValid(email);
    }

    public boolean isPasswordValid(String password) {
        return model.isPasswordValid(password);
    }

}

