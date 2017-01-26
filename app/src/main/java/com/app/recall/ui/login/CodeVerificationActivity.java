package com.app.recall.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.recall.R;
import com.app.recall.base.BaseActivity;
import com.app.recall.contract.CodeVerificationContract;
import com.app.recall.presenter.CodeVerificationPresenterImpl;
import com.app.recall.util.ViewUtils;

public class CodeVerificationActivity extends BaseActivity<CodeVerificationPresenterImpl>
        implements CodeVerificationContract.View {
    private AppCompatEditText code;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected CodeVerificationPresenterImpl initPresenter() {
        return new CodeVerificationPresenterImpl();
    }

    @Override
    protected void initViews() {
        TextView textView = (TextView) findViewById(R.id.message);
        code = (AppCompatEditText) findViewById(R.id.code);
        phone = getIntent().getStringExtra("phone");
        textView.setText("We sent a verification to your phone:+86" + phone);
        ViewUtils.startCountDown((Button) findViewById(R.id.code_button), "s later Resent Code",
                                 "Resent", 60000);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {
        if (id == R.id.code_button) {
            presenter.sendVerificationCode(phone);
            ViewUtils.startCountDown((Button) v, "s later Resent Code", "Resent", 60000);
        }
    }

    @Override
    public void networkError() {
        Toast.makeText(this, "network error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int contentViewId() {
        return R.layout.activity_phone_code_verification;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        toolbar.setTitle("Verify code");
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
        if (item.getItemId() == R.id.menu_forward) {
            code.setError(null);
            String code = this.code.getText().toString();
            if (code.length() == 6) {
                startActivity(new Intent(this, SetPasswordActivity.class).putExtra("phone",
                                                                                   phone).putExtra(
                        "code", code));
            } else {
                this.code.setError("Invalid code");
            }
        }
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        ViewUtils.endCountDown();
    }
}
