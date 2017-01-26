package com.app.recall.presenter;

import com.app.recall.contract.OtherPlatformsContract;
import com.app.recall.entity.retrofit.BaseEntity;

import java.util.List;

/**
 * Created by KenChan on 2016/12/26
 */

public class OtherPlatformsPresenterImpl extends BasePresenter<OtherPlatformsContract.View> {


    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, int what) {

    }

    @Override
    void onFailedResponse(String error, int what) {

    }
}