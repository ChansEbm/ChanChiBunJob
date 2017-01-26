package com.app.recall.ui.login;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.recall.R;
import com.app.recall.base.BaseActivity;
import com.app.recall.contract.SetPasswordContract;
import com.app.recall.presenter.SetPasswordPresenterImpl;

public class SetPasswordActivity extends BaseActivity<SetPasswordPresenterImpl> implements
                                                                                SetPasswordContract.View {

    private AppCompatEditText enter;
    private AppCompatEditText confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected SetPasswordPresenterImpl initPresenter() {
        return new SetPasswordPresenterImpl();
    }

    @Override
    protected void initViews() {
        enter = (AppCompatEditText) findViewById(R.id.enter_password);
        confirm = (AppCompatEditText) findViewById(R.id.confirm_password);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {

    }

    @Override
    public void networkError() {
        Toast.makeText(this, "network error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int contentViewId() {
        return R.layout.activity_set_password;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        toolbar.setTitle("Password");
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_done) {
            View focus = null;
            enter.setError(null);
            confirm.setError(null);
            String enter = this.enter.getText().toString();
            String confirm = this.confirm.getText().toString();
            if (TextUtils.isEmpty(enter) || enter.length() < 5) {
                focus = this.enter;
                this.enter.setError("password length invalid");
            }
            if (TextUtils.isEmpty(confirm) || confirm.length() < 5) {
                focus = this.confirm;
                this.confirm.setError("password length invalid");
            }

            if (!TextUtils.equals(confirm, enter)) {
                focus = this.confirm;
                this.confirm.setError("password not equals");
            }

            if (focus != null) { focus.requestFocus();}

            String phone = getIntent().getStringExtra("phone");
            String code = getIntent().getStringExtra("code");
            presenter.signUpWithPhone(phone, code, confirm);
        }

        return true;
    }
}
