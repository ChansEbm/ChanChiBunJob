package com.app.recall.presenter;

import android.app.Service;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;

import com.app.recall.base.APP;
import com.app.recall.entity.Configuration;
import com.app.recall.view.IView;
import com.github.pwittchen.prefser.library.Prefser;

/**
 * Created by KenChan on 17/1/9.
 */

abstract class NotificationBasePresenter<IV extends IView> extends BasePresenter<IV> {
    private Configuration.Notification notification = new Configuration.Notification(false, false);
    private Prefser prefser;
    boolean sound = false;
    boolean vibrate = false;
    Vibrator vibrator;

    @Override
    public void onAttach(AppCompatActivity appCompatActivity) {
        super.onAttach(appCompatActivity);
        prefser = new Prefser(appCompatActivity);
        notification = prefser.get(APP.NOTIFICATION, Configuration.Notification.class,
                notification);
        this.sound = notification.isSound();
        this.vibrate = notification.isVibrate();

        vibrator = (Vibrator) appCompatActivity.getSystemService(Service.VIBRATOR_SERVICE);
    }

    public void changeSound(boolean sound) {
        this.sound = sound;
        notification.setSound(sound);
        prefser.put(APP.NOTIFICATION, notification);
    }

    public void changeVibrate(boolean vibrate) {
        this.vibrate = vibrate;
        notification.setVibrate(vibrate);
        prefser.put(APP.NOTIFICATION, notification);
    }

    public void disableNotification() {
        this.vibrate = false;
        this.sound = false;
        notification.setSound(false);
        notification.setVibrate(false);
        prefser.put(APP.NOTIFICATION, notification);
    }
}
