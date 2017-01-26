package com.app.recall.presenter;

import com.app.recall.contract.PersonalInfoContract;
import com.app.recall.entity.retrofit.BaseEntity;
import com.app.recall.entity.retrofit.MeEntity;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by KenChan on 2017/01/25
 */

public class PersonalInfoPresenterImpl extends BasePresenter<PersonalInfoContract.View> {
    private boolean avatar, username;

    public void postAvatar(String path) {
        File file = new File(path);
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("photo", file.getName(), body);
        call(false).callUploadAvatar(part, getAuthorization()).enqueue(new RetrofitObjEnqueue<MeEntity>(CUPCAKE));
    }

    public void postUsername(String name) {
        call(false).callUserName(name, getAuthorization()).enqueue(new RetrofitObjEnqueue<MeEntity>
                (DONUT));
    }

    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, @What int what) {
        if (what == CUPCAKE) {
            avatar = true;
        } else if (what == DONUT) {
            username = true;
        }
        if (avatar && username && iView != null) {
            iView.profileUploadSuccess();
        }
    }

    @Override
    void onFailedResponse(String error, int what) {

    }
}