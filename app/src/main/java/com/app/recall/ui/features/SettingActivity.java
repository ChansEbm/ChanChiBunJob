package com.app.recall.ui.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.recall.R;
import com.app.recall.base.BaseActivity;
import com.app.recall.presenter.SettingPresenterImpl;

public class SettingActivity extends BaseActivity<SettingPresenterImpl> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected SettingPresenterImpl initPresenter() {
        return new SettingPresenterImpl();
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
            case R.id.account_info:
                startActivity(new Intent(this, AccountInfoActivity.class));
                break;
            case R.id.user_info:
                startActivity(new Intent(this, UserInfoActivity.class));
                break;
            case R.id.notification:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.help:
                //help
                break;
            case R.id.about:
                //about
                break;
        }
    }

    @Override
    public void networkError() {

    }

    @Override
    public int contentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        toolbar.setTitle("Setting");
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return true;
    }
}
