package com.app.recall.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.recall.R;
import com.app.recall.base.BasePlatformActivity;
import com.app.recall.contract.BindPlatformContract;
import com.app.recall.presenter.BindPlatformPresenterImpl;

import java.util.ArrayList;

public class BindPlatformActivity extends BasePlatformActivity<BindPlatformPresenterImpl>
        implements BindPlatformContract.View {
    private Toolbar toolbar;
    private ArrayList<Integer> platforms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPlatform(String userName, String token) {

    }

    @Override
    protected BindPlatformPresenterImpl initPresenter() {
        return new BindPlatformPresenterImpl();
    }

    @Override
    protected void initViews() {
        platforms = getIntent().getIntegerArrayListExtra("platforms");

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
        return R.layout.activity_bind_platform;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        this.toolbar = toolbar;
        presenter.titleWhat(platforms.get(0), toolbar);
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return true;
    }

    @Override
    public void uploadPlatformSuccess(int what) {
        this.platforms.remove(what);
        if (platforms.isEmpty()) {
            startActivity(new Intent(this, LoginActivity.class).addFlags(Intent
                    .FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            return;
        }
        presenter.titleWhat(platforms.get(0), toolbar);
        presenter.setupPlatform(platforms.get(0));
    }
}
