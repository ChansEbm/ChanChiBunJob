package com.app.recall.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.app.recall.R;
import com.app.recall.manager.ActivityManager;
import com.app.recall.manager.ToolbarManager;
import com.app.recall.presenter.IPresenter;
import com.app.recall.receiver.NetworkChangedReceiver;
import com.app.recall.util.DensityUtils;
import com.app.recall.view.IView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by KenChan on 16/12/13.
 */

public abstract class BaseActivity<IP extends IPresenter> extends AppCompatActivity implements
        IView, View.OnClickListener {
    public final String TAG = "recall";
    protected IP presenter;
    protected NetworkChangedReceiver networkChangedReceiver = new NetworkChangedReceiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentViewId());
        registerEventBus();
        preparePresenter();
        initViews();
        initEvents();
        setupToolbar();
        addActivityStack();
        translatableStatus();
        registerNetworkChangeReceiver();
    }

    private void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            try {
                EventBus.getDefault().register(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (isSetupToolbarDefaultConfig()) {
            toolbarPrepared(toolbar);
            setSupportActionBar(toolbar);
            if (toolbar != null) {
                toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable
                        .ic_arrow_back_white_24dp));
                toolbar.setNavigationOnClickListener(this);
                ToolbarManager.getInstance().registerNavId(toolbar);
            } else {
                throw new RuntimeException("If isSetupToolbarDefaultConfig return true ,you " +
                        "should make sure your " + getClass().getSimpleName() + " include" +
                        " Toolbar");
            }
        } else {
            if (toolbar != null) {
                toolbarPrepared(toolbar);
                setSupportActionBar(toolbar);
            }
        }
    }

    private void addActivityStack() {
        ActivityManager.getActivityManager().addActivity(this);
    }

    private void translatableStatus() {
        FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.flags = attributes.flags | WindowManager.LayoutParams
                    .FLAG_TRANSLUCENT_STATUS;
            View view = new View(this);
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout
                    .LayoutParams.MATCH_PARENT, startsBarHeight());
            decorView.addView(view, 0, params);
        }


    }


    private void registerNetworkChangeReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction("android.net.wifi.STATE_CHANGE");
        registerReceiver(networkChangedReceiver, filter);
    }

    private void preparePresenter() {
        presenter = initPresenter();
        if (presenter == null) {
            throw new RuntimeException("presenter not init success");
        }
        presenter.onAttach(this);
    }

    @Override
    public int startsBarHeight() {
        return DensityUtils.getStatusHeight(this);
    }


    protected abstract IP initPresenter();

    protected abstract void initViews();

    protected abstract void initEvents();

    protected abstract void onClick(View v, int id);

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick: " + v.getId());
        if (v.getId() == R.id.nav) {
            ActivityManager.getActivityManager().finishActivity(this);
            return;
        }
        onClick(v, v.getId());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unAttachPresenter();
        unregisterEventBus();
        unregisterReceiver(networkChangedReceiver);
        ActivityManager.getActivityManager().finishActivity(this);
    }

    private void unregisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) EventBus.getDefault().unregister(this);
    }

    private void unAttachPresenter() {
        if (presenter != null) {
            presenter.onDestroy();
        }
    }
}



