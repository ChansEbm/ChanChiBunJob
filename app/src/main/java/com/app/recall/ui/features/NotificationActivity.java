package com.app.recall.ui.features;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.app.recall.R;
import com.app.recall.base.BaseActivity;
import com.app.recall.contract.NotificationsContract;
import com.app.recall.presenter.NotificationsPresenterImpl;

public class NotificationActivity extends BaseActivity<NotificationsPresenterImpl> implements
        NotificationsContract.View {
    private Switch notification;
    private Switch sound;
    private Switch vibrate;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected NotificationsPresenterImpl initPresenter() {
        return new NotificationsPresenterImpl();
    }

    @Override
    protected void initViews() {
        notification = (Switch) findViewById(R.id.notification);
        sound = (Switch) findViewById(R.id.alert_sound);
        vibrate = (Switch) findViewById(R.id.vibrate);

    }

    @Override
    protected void initEvents() {
        notification.setOnCheckedChangeListener(new SwitchChangedListener());
        sound.setOnCheckedChangeListener(new SwitchChangedListener());
        vibrate.setOnCheckedChangeListener(new SwitchChangedListener());
    }

    @Override
    protected void onClick(View v, int id) {

    }

    @Override
    public void networkError() {

    }

    @Override
    public int contentViewId() {
        return R.layout.activity_notification;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        toolbar.setTitle("Notification");
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return true;
    }

    @Override
    public void onAttach(boolean sound, boolean vibrate) {
        if (sound || vibrate) notification.setChecked(true);
        if (!notification.isChecked()) {
            this.sound.setEnabled(false);
            this.vibrate.setEnabled(false);
            return;
        }
        this.sound.setChecked(sound);
        this.vibrate.setChecked(vibrate);

    }

    class SwitchChangedListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(TAG, "onCheckedChanged: " + buttonView.getId() + ",isChecked:" + isChecked);
            switch (buttonView.getId()) {
                case R.id.notification:
                    if (isChecked) {
                        sound.setEnabled(true);
                        vibrate.setEnabled(true);
                    } else {
                        sound.setEnabled(false);
                        vibrate.setEnabled(false);
                        sound.setChecked(false);
                        vibrate.setChecked(false);
                        presenter.disableNotification();
                    }

                    break;
                case R.id.alert_sound:
                    presenter.changeSound(isChecked);
                    break;
                case R.id.vibrate:
                    presenter.changeVibrate(isChecked);
                    break;
            }
        }
    }
}
