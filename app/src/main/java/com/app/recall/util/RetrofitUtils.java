package com.app.recall.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KenChan on 16/12/13.
 */

public class RetrofitUtils<T> {
    private Retrofit retrofit;

    public RetrofitUtils() {
        retrofit = new Retrofit.Builder().baseUrl("http://kf.szlb.cc/").addConverterFactory(
                GsonConverterFactory.create()).build();
    }

    public T callT(Class<T> clz) {
        return retrofit.create(clz);
    }


    public void destroy() {
        retrofit = null;
    }

}
