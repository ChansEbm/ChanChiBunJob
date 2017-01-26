package com.app.recall.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.app.recall.R;
import com.app.recall.base.APP;
import com.app.recall.base.BasePlatformActivity;
import com.app.recall.contract.LoginContract;
import com.app.recall.presenter.LoginPresenterImpl;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.orhanobut.logger.Logger;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BasePlatformActivity<LoginPresenterImpl> implements
        LoginContract.View, GoogleApiClient.OnConnectionFailedListener {
    private AppCompatEditText account;
    private AppCompatEditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPlatformSuccess(String name, String userName, String token) {

    }

    @Override
    protected void onPlatformFailed(String name) {

    }

    @Override
    protected LoginPresenterImpl initPresenter() {
        return new LoginPresenterImpl();
    }

    @Override
    protected void initViews() {
        account = (AppCompatEditText) findViewById(R.id.account);
        password = (AppCompatEditText) findViewById(R.id.password);
        SignInButton button = (SignInButton) findViewById(R.id.sign_button);
        button.setSize(SignInButton.SIZE_STANDARD);
        button.setOnClickListener(this);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {
        switch (id) {
            case R.id.phone_sign_up_button:
                presenter.phoneSignUp();
                break;
            case R.id.facebook_sign_up_button:
                presenter.facebookSignUp();
                break;
            case R.id.sign_in_button:
                if (check()) {
                    presenter.accountSign(account.getText().toString(), password.getText()
                            .toString());
                    //                    startActivity();
                    //                    presenter.startActivity(HostActivity.class, -1);
                }
                break;
            case R.id.sign_button:
                auth(APP.GOOGLEPLUS);
                break;


        }
    }

    @Override
    public void networkError() {

    }

    @Override
    public int contentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        toolbar.setTitle("Login");
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return false;
    }

    private boolean check() {
        View focus = null;
        account.setError(null);
        password.setError(null);
        String account = this.account.getText().toString();
        String password = this.password.getText().toString();
        if (TextUtils.isEmpty(account) || account.length() < 3) {
            focus = this.account;
            this.account.setError("account invalid");
        }

        if (TextUtils.isEmpty(password) || password.length() < 5) {
            focus = this.password;
            this.password.setError("password length invalid");
        }

        if (focus != null) {
            focus.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        String errorMessage = connectionResult.getErrorMessage();
        Logger.d("onConnectionFailed: " + errorMessage);
    }

    //    class RecallTimer extends CountDownTimer {
    //
    //
    //        /**
    //         * @param millisInFuture    The number of millis in the future from the call
    //         *                          to {@link #start()} until the countdown is done and
    //         *                          {@link #onFinish()}
    //         *                          is called.
    //         * @param countDownInterval The interval along the way to receive
    //         *                          {@link #onTick(long)} callbacks.
    //         */
    //        public RecallTimer(long millisInFuture, long countDownInterval) {
    //            super(millisInFuture, countDownInterval);
    //        }
    //
    //        @Override
    //        public void onTick(long millisUntilFinished) {
    //            Log.i(TAG, "onTick: " + millisUntilFinished);
    //        }
    //
    //        @Override
    //        public void onFinish() {
    //            Log.i(TAG, "onFinish: ");
    //        }
    //    }
}

