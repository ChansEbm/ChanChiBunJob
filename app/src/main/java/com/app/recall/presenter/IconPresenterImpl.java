package com.app.recall.presenter;

import com.app.recall.base.APP;
import com.app.recall.contract.IconContract;
import com.app.recall.entity.retrofit.BaseEntity;
import com.app.recall.entity.retrofit.MeEntity;
import com.github.pwittchen.prefser.library.Prefser;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by KenChan on 2017/01/03
 */

public class IconPresenterImpl extends BasePresenter<IconContract.View> {

    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, @What int what) {
        if (what == CUPCAKE) {
            MeEntity me = (MeEntity) response;
            new Prefser(activity).put(APP.ME, me);
        }
    }

    @Override
    void onFailedResponse(String error, int what) {

    }

    public void postIcon(String path) {
        File file = new File(path);
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("photo", file.getName(), body);
        call(false).callUploadAvatar(part, getAuthorization()).enqueue(new RetrofitObjEnqueue<MeEntity>());
    }
}