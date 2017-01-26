package com.app.recall.ui.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.recall.R;
import com.app.recall.base.BaseActivity;
import com.app.recall.contract.AccountInfoContract;
import com.app.recall.presenter.AccountInfoPresenterImpl;

public class AccountInfoActivity extends BaseActivity<AccountInfoPresenterImpl> implements
        AccountInfoContract.View {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected AccountInfoPresenterImpl initPresenter() {
        return new AccountInfoPresenterImpl();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {
        switch (id) {
            case R.id.password:
                startActivity(new Intent(this, ChangePasswordActivity.class));
                break;
            case R.id.icon:
                startActivity(new Intent(this, IconActivity.class));
                break;
        }
    }

    @Override
    public void networkError() {

    }

    @Override
    public int contentViewId() {
        return R.layout.activity_account_info;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        toolbar.setTitle("Account Info");
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return true;
    }
}
