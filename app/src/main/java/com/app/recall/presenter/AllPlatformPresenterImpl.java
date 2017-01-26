package com.app.recall.presenter;

import com.app.recall.contract.AllPlatformContract;
import com.app.recall.entity.retrofit.BaseEntity;

import java.util.List;

/**
 * Created by KenChan on 2017/01/25
 */

public class AllPlatformPresenterImpl extends BasePresenter<AllPlatformContract.View> {

    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, @What int what) {

    }

    @Override
    void onFailedResponse(String error, int what) {
        
    }
}