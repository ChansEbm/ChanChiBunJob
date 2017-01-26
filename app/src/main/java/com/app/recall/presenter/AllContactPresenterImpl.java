package com.app.recall.presenter;

import com.app.recall.contract.AllContactContract;
import com.app.recall.entity.retrofit.BaseEntity;

import java.util.List;

/**
 * Created by KenChan on 2016/12/23
 */

public class AllContactPresenterImpl extends BasePresenter<AllContactContract.View> {

    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, int what) {

    }

    @Override
    void onFailedResponse(String error, int what) {

    }
}