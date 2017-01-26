package com.app.recall.presenter;

import android.app.Activity;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by KenChan on 16/12/13.
 */

public interface IPresenter {

    void onAttach(AppCompatActivity appCompatActivity);

    String getAuthorization();

    void onAttach(Fragment fragment);

    void onDestroy();

    void onRequestPermissionsResult(@NonNull Activity activity, int requestCode, @NonNull
            String[] permissions, @NonNull int[] grantResults);

    int FROYO = 110;
    int CUPCAKE = 119;
    int DONUT = 356;
    int ECLAIR = 66;
    int GINGERBREAD = 620;

    int ARRAY_FROYO = 980;
    int ARRAY_CUPCAKE = 116;
    int ARRAY_DONUT = 338;

    @IntDef({CUPCAKE, DONUT, FROYO, ECLAIR, GINGERBREAD})
    @Retention(RetentionPolicy.SOURCE)
    @interface What {

    }


    @IntDef({ARRAY_FROYO, ARRAY_CUPCAKE, ARRAY_DONUT})
    @Retention(RetentionPolicy.SOURCE)
    @interface Array {

    }


}

