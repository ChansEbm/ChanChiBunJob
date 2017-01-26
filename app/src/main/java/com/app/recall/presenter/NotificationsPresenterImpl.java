package com.app.recall.presenter;

import android.support.v7.app.AppCompatActivity;

import com.app.recall.contract.NotificationsContract;
import com.app.recall.entity.retrofit.BaseEntity;

import java.util.List;

/**
 * Created by KenChan on 2016/12/26
 */

public class NotificationsPresenterImpl extends NotificationBasePresenter<NotificationsContract
        .View> {

    @Override
    public void onAttach(AppCompatActivity appCompatActivity) {
        super.onAttach(appCompatActivity);
        iView.onAttach(sound, vibrate);
    }


    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, @What int what) {

    }

    @Override
    void onFailedResponse(String error, int what) {

    }
}