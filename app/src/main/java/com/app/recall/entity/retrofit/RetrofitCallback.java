package com.app.recall.entity.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by KenChan on 17/1/3.
 */

public class RetrofitCallback<T extends BaseEntity> implements Callback<T> {


    @Override
    public void onResponse(Call<T> call, Response<T> response) {

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
