package com.app.recall.presenter;

import com.app.recall.contract.SettingContract;
import com.app.recall.entity.retrofit.BaseEntity;

import java.util.List;

/**
 * Created by KenChan on 2016/12/24
 */

public class SettingPresenterImpl extends BasePresenter<SettingContract.View> {

    @Override
    void onSuccessResponse(BaseEntity response, List<? extends BaseEntity> entities, int what) {

    }

    @Override
    void onFailedResponse(String error, int what) {
        
    }
}