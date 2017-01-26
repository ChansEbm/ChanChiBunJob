package com.app.recall.presenter;

import android.support.annotation.AnimRes;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.app.recall.R;
import com.app.recall.contract.SendMyNameCardContract;
import com.app.recall.entity.retrofit.BaseEntity;

import java.util.List;

/**
 * Created by KenChan on 2017/01/04
 */

public class SendMyNameCardPresenterImpl extends BasePresenter<SendMyNameCardContract.View> {


    public void startTopAnimation(View view, @AnimRes int res) {
        Animation animation = AnimationUtils.loadAnimation(activity, res);
        animation.setDuration(580);
        animation.setFillAfter(true);
        animation.setInterpolator(res == R.anim.anim_top_in ? new LinearOutSlowInInterpolator() :
                new FastOutLinearInInterpolator());
        if (view == null) {
            return;
        }
        view.clearAnimation();
        view.startAnimation(animation);
    }

    public void searchUID(String uid) {
        call(false).callHandshake(uid, getAuthorization()).enqueue(new RetrofitObjEnqueue<>());
    }


    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, @What int what) {
        if (what == CUPCAKE) {

        }
    }

    @Override
    void onFailedResponse(String error, int what) {
        if (iView != null) {
            iView.handshakeFailed();
        }
    }
}