package com.app.recall.model;

import com.app.recall.contract.SignUpContract;

/**
 * Created by KenChan on 2016/12/16
 */

public class SignUpModelImpl implements SignUpContract.Model {

    @Override
    public boolean isEmailValid(String email) {
        return email.contains("@");
    }

    @Override
    public boolean isPasswordValid(String password) {
        return password.length() > 6;
    }
}