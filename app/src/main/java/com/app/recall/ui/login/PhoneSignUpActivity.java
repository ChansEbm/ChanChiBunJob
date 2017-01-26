package com.app.recall.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.app.recall.R;
import com.app.recall.base.BaseActivity;
import com.app.recall.contract.PhoneSignUpContract;
import com.app.recall.presenter.PhoneSignUpPresenterImpl;

public class PhoneSignUpActivity extends BaseActivity<PhoneSignUpPresenterImpl> implements
                                                                                PhoneSignUpContract.View {

    private AppCompatEditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            phone.setText(savedInstanceState.getString("phone"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (phone != null && !TextUtils.isEmpty(phone.getText().toString())) {
            outState.putString("phone", phone.getText().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("phone")) {
            phone.setText(savedInstanceState.getString("phone", ""));
        }
    }

    @Override
    protected PhoneSignUpPresenterImpl initPresenter() {
        return new PhoneSignUpPresenterImpl();
    }

    @Override
    protected void initViews() {
        phone = (AppCompatEditText) findViewById(R.id.phone);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {

    }

    @Override
    public void networkError() {

    }

    @Override
    public int contentViewId() {
        return R.layout.activity_phone_sign_up;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        toolbar.setTitle("Input your phone");
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_forward, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        phone.setError(null);
        if (item.getItemId() == R.id.menu_forward) {
            String phone = this.phone.getText().toString();
            if (!TextUtils.isEmpty(phone) && phone.length() > 5) {
                presenter.sendVerificationCode(phone);
            }
        } else {
            phone.setError("Phone number invalid");
        }
        return true;
    }

    @Override
    public void sentVerificationSuccess() {
        startActivity(new Intent(this, CodeVerificationActivity.class).putExtra("phone",
                                                                                phone.getText().toString()));
    }
}
