package com.app.recall.ui.features;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.recall.R;
import com.app.recall.base.BaseActivity;
import com.app.recall.presenter.ChangePasswordPresenterImpl;

public class ChangePasswordActivity extends BaseActivity<ChangePasswordPresenterImpl> {
    private AppCompatEditText origin;
    private AppCompatEditText password;
    private AppCompatEditText confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected ChangePasswordPresenterImpl initPresenter() {
        return new ChangePasswordPresenterImpl();
    }

    @Override
    protected void initViews() {
        origin = (AppCompatEditText) findViewById(R.id.origin_pass);
        password = (AppCompatEditText) findViewById(R.id.new_pass);
        confirm = (AppCompatEditText) findViewById(R.id.confirm_pass);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {
        if (id == R.id.post_action) {
            postAction();
        }
    }

    @Override
    public void networkError() {

    }

    @Override
    public int contentViewId() {
        return R.layout.activity_change_password;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        toolbar.setTitle("Password");
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return true;
    }

    private void postAction() {
        View focus = null;
        String origin = this.origin.getText().toString();
        String password = this.password.getText().toString();
        String confirm = this.confirm.getText().toString();

        if (!presenter.checkPwd(origin)) {
            focus = this.origin;
            this.origin.setError("invalid origin password");
        }
        if (!presenter.checkPwd(password)) {
            focus = this.password;
            this.password.setError("invalid password");
        }

        if (!presenter.checkPwd(confirm)) {
            focus = this.confirm;
            this.confirm.setError("invalid password");
        }
        if (!presenter.pwdEquals(password, confirm)) {
            focus = this.confirm;
            this.confirm.setError("password not equals");
        }

        if (focus != null) {
            focus.requestFocus();
            return;
        }

        presenter.callPassword(origin, password);

    }


}
