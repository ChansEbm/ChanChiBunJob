package com.app.recall.ui.features;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.recall.R;
import com.app.recall.base.BaseActivity;
import com.app.recall.presenter.OtherPlatformsPresenterImpl;

public class OtherPlatforms extends BaseActivity<OtherPlatformsPresenterImpl> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected OtherPlatformsPresenterImpl initPresenter() {
        return new OtherPlatformsPresenterImpl();
    }

    @Override
    protected void initViews() {

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
        return R.layout.activity_other_platforms;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {

    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return false;
    }
}
