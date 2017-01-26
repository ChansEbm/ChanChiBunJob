package com.app.recall.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.app.recall.BuildConfig;
import com.app.recall.base.APP;
import com.app.recall.dialog.LoadingDialog;
import com.app.recall.entity.retrofit.BaseEntity;
import com.app.recall.entity.retrofit.UserEntity;
import com.app.recall.impl.RetrofitImpl.RecallImpl;
import com.app.recall.util.ConnectionUtils;
import com.app.recall.util.PermissionUtils;
import com.app.recall.view.IView;
import com.github.pwittchen.prefser.library.Prefser;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KenChan on 16/12/13.
 */
abstract class BasePresenter<IV extends IView> implements IPresenter {

    final String TAG = "Recall";
    final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    IV iView;
    FragmentActivity activity;
    private LoadingDialog loading;

    RecallImpl call(boolean shutoffDialog) {
        if (loading != null && !loading.isShowing() && !shutoffDialog) { loading.show(); }
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit
                .SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory
                        (RxJavaCallAdapterFactory.create()).client(okHttpClient).build();
        return retrofit.create(RecallImpl.class);
    }

    abstract void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, int
            what);

    abstract void onFailedResponse(String error, int what);

    @Override
    public void onAttach(AppCompatActivity appCompatActivity) {
        if (appCompatActivity != null) {
            if (appCompatActivity instanceof IView) { this.iView = (IV) appCompatActivity; }
            this.activity = appCompatActivity;
            loading = new LoadingDialog(appCompatActivity);
            loading.setCancelable(false);
            loading.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void onAttach(Fragment fragment) {
        if (fragment != null) {
            if (fragment instanceof IView) {
                this.iView = (IV) fragment;
            }
            this.activity = fragment.getActivity();
            loading = new LoadingDialog(this.activity);
        }
    }


    @Override
    public void onDestroy() {
        if (iView != null) {
            iView = null;
        }
        if (activity != null) {
            activity = null;
        }
        if (loading != null) { loading = null; }
    }


    @Override
    public void onRequestPermissionsResult(@NonNull Activity activity, int requestCode, @NonNull
            String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_DENIED && !ActivityCompat
                .shouldShowRequestPermissionRationale(activity, permissions[0])) {
            PermissionUtils.showPermissionDialog(activity, permissions[0]);
        }
    }


    private void dismissLoading() {
        if (loading.isShowing()) { loading.dismiss(); }
    }

    void startActivity(Class clz, int flags) {
        Intent intent = new Intent(activity, clz);
        if (flags != -1) { intent.addFlags(flags); }
        activity.startActivity(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public Context getContext() {
        return activity;
    }

    @Override
    public String getAuthorization() {
        return new Prefser(activity).get(APP.USER_TOKEN, UserEntity.TokenBean.class, new
                UserEntity.TokenBean()).getAccessToken();
    }


    class RetrofitObjEnqueue<BE extends BaseEntity> implements Callback<BE> {
        @What
        int what = CUPCAKE;

        RetrofitObjEnqueue() {
        }

        RetrofitObjEnqueue(@What int what) {
            this.what = what;
        }

        @Override
        public void onResponse(Call<BE> call, Response<BE> response) {
            boolean successful = response.isSuccessful();
            if (successful) {
                BaseEntity body = response.body();
                String json = new Gson().toJson(body);
                Logger.json(json);
                BasePresenter.this.onSuccessResponse(body, new ArrayList<BaseEntity>(), what);
            } else {
                try {
                    ResponseBody errorBody = response.errorBody();
                    if (errorBody != null) {
                        String json = errorBody.string();
                        Logger.w(json);
                        Logger.json(json);
                        Logger.d("error code:" + response.code());
                        BaseEntity baseEntity = new Gson().fromJson(json, BaseEntity.class);
                        String error = baseEntity.getError();
                        BasePresenter.this.onFailedResponse(error, CUPCAKE);
                        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            dismissLoading();
        }

        @Override
        public void onFailure(Call<BE> call, Throwable t) {
            call.cancel();
            t.printStackTrace();
            if (iView != null && !ConnectionUtils.checkedConnectionEnabled(activity)) {
                iView.networkError();
            }
            BasePresenter.this.onFailedResponse(t.getMessage(), what);
            dismissLoading();
        }
    }


    class RetrofitArrayEnqueue<BE extends BaseEntity> implements Callback<List<BE>> {
        @Array
        int what = ARRAY_CUPCAKE;

        RetrofitArrayEnqueue() {
        }

        public RetrofitArrayEnqueue(@Array int what) {
            this.what = what;
        }

        @Override
        public void onResponse(Call<List<BE>> call, Response<List<BE>> response) {
            boolean successful = response.isSuccessful();
            if (successful) {
                List<BE> body = response.body();
                String array = new Gson().toJson(body);
                Logger.json(array);
                BasePresenter.this.onSuccessResponse(new BaseEntity(), body, what);
            } else {
                try {
                    ResponseBody errorBody = response.errorBody();
                    if (errorBody != null) {
                        String json = errorBody.string();
                        Logger.w(json);
                        Logger.json(json);
                        Logger.d("error code:" + response.code());
                        BaseEntity baseEntity = new Gson().fromJson(json, BaseEntity.class);
                        String error = baseEntity.getError();
                        BasePresenter.this.onFailedResponse(error, CUPCAKE);
                        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            dismissLoading();
        }

        @Override
        public void onFailure(Call<List<BE>> call, Throwable t) {
            call.cancel();
            t.printStackTrace();
            if (iView != null && !ConnectionUtils.checkedConnectionEnabled(activity)) {
                iView.networkError();
            }
            BasePresenter.this.onFailedResponse(t.getMessage(), what);
            dismissLoading();

        }

    }
}
