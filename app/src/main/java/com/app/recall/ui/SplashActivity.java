package com.app.recall.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.app.recall.R;
import com.app.recall.base.BaseActivity;
import com.app.recall.presenter.SplashPresenterImpl;
import com.app.recall.ui.login.BindPlatformActivity;
import com.app.recall.ui.login.LoginActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends BaseActivity<SplashPresenterImpl> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        //        startActivity(new Intent(this, HostActivity.class).addFlags(Intent
        //                .FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }


    @Override
    protected SplashPresenterImpl initPresenter() {
        return new SplashPresenterImpl();
    }

    @Override
    protected void initViews() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent.setClass(this, BindPlatformActivity.class));
        //        Log.i(TAG, "initViews: " + presenter.checkState());
        //        if (presenter.checkState()) {
        //            presenter.login();
        //        } else {
        //            intent.setClass(this, LoginActivity.class);
        //            startActivity(intent);
        //        }
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
        return R.layout.activity_splash;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {

    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return false;
    }
}
