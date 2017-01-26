package com.app.recall.presenter;

import android.Manifest;
import android.app.Activity;
import android.os.CountDownTimer;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.app.recall.R;
import com.app.recall.contract.HostContract;
import com.app.recall.entity.retrofit.BaseEntity;
import com.app.recall.entity.retrofit.ReceiverOtherUserEntity;
import com.app.recall.model.HostModelImpl;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by KenChan on 2016/12/13
 */

public class HostPresenter extends NotificationBasePresenter<HostContract.View> {
    private static final String TAG = "HostPresenter";
    private HostContract.Model model;
    private RecallTimer timer;

    public HostPresenter() {
        model = new HostModelImpl(this);
        timer = new RecallTimer(10 * 1000, 1000);
    }

    public void startTopAnimation(final View view, @AnimRes final int res) {
        Animation animation = AnimationUtils.loadAnimation(activity, res);
        final int height = view.getHeight();
        final int top = view.getTop();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (res == R.anim.anim_top_in && view.getTop() < 0) {
                    view.layout(0, 0, view.getWidth(), view.getHeight());
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (res == R.anim.anim_top_out) {
                    view.layout(0, -view.getHeight(), view.getWidth(), view.getHeight());
                    //                    RelativeLayout.LayoutParams params = (RelativeLayout
                    // .LayoutParams) view
                    //                            .getLayoutParams();
                    //                    params.setMargins(0, -height, 0, 0);
                    //                    view.setLayoutParams(params);
                }
                Log.i(TAG, "onAnimationEnd: " + (res == R.anim.anim_top_out));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation.setDuration(580);
        animation.setFillAfter(true);
        animation.setInterpolator(res == R.anim.anim_top_in ? new LinearOutSlowInInterpolator() :
                new FastOutLinearInInterpolator());
        view.clearAnimation();
        view.startAnimation(animation);
    }

    private void sinvoiceState(@HostContract.Model.SinvoiceState int state) {
        switch (state) {
            case HostContract.Model.PLAYER_START:
                model.startSendUid();
                break;
            case HostContract.Model.PLAYER_STOP:
                model.stopSendUid();
                break;
            case HostContract.Model.RECOGNITION_START:
                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)
                        == PermissionChecker.PERMISSION_GRANTED) {
                    model.startRecognition();
                } else {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission
                            .RECORD_AUDIO}, 0);
                }
                break;
            case HostContract.Model.RECOGNITION_STOP:
                model.stopRecognition();
                break;
        }

        if (state == HostContract.Model.RECOGNITION_START) {
            timer.start();
        } else if (state == HostContract.Model.PLAYER_STOP || state == HostContract.Model
                .RECOGNITION_STOP) {
            timer.cancel();
        }
    }

    public void clearSinvoice() {
        sinvoiceState(HostContract.Model.PLAYER_STOP);
        sinvoiceState(HostContract.Model.RECOGNITION_STOP);
    }

    public void startSinvoice() {
        sinvoiceState(HostContract.Model.RECOGNITION_START);
        sinvoiceState(HostContract.Model.PLAYER_START);
    }

    public void lifeCircle(@HostContract.Model.LifeCircle int lifeCircle) {
        model.lifeCircle(lifeCircle);
    }

    public void findUser(String userId) {
        if (TextUtils.isEmpty(userId)) return;
        clearSinvoice();
        timer.cancel();
        Toast.makeText(activity, userId, Toast.LENGTH_SHORT).show();
        call(true).callUserBaseInfo(userId).enqueue(new RetrofitObjEnqueue<ReceiverOtherUserEntity>
                (CUPCAKE));
    }


    @Override
    public void onRequestPermissionsResult(@NonNull Activity activity, int requestCode, @NonNull
            String[] permissions, @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionsResult: " + permissions[0] + ",result:" + grantResults[0]);
        super.onRequestPermissionsResult(activity, requestCode, permissions, grantResults);
        if (TextUtils.equals(permissions[0], Manifest.permission.RECORD_AUDIO) && grantResults[0]
                == PermissionChecker.PERMISSION_GRANTED) {
            model.startRecognition();
        } else {
            clearSinvoice();
            Toast.makeText(activity, "you have to grantee the permission", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, int what) {
        if (what == CUPCAKE) {//find user success
            ReceiverOtherUserEntity body = (ReceiverOtherUserEntity) response;
            if (body != null) {
                com.orhanobut.logger.Logger.json(new Gson().toJson(body));
                if (iView != null) {
                    iView.matcher(body.getUsername(), body.getProfilephoto(), body.getUid());
                }
            }
            if (vibrate && vibrator.hasVibrator()) vibrator.vibrate(600);
        }
    }

    @Override
    void onFailedResponse(String error, int what) {
        if (what == CUPCAKE && iView != null) {
            iView.nobodyFound();
        }
    }

    private class RecallTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and
         *                          {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        RecallTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            clearSinvoice();
            iView.nobodyFound();
        }

    }


}