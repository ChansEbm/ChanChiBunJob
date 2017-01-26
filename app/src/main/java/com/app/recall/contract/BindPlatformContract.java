package com.app.recall.contract;

import android.support.v7.widget.Toolbar;

import com.app.recall.presenter.IPresenter;
import com.app.recall.view.IView;

/**
 * Created by KenChan on 17/1/26.
 */

public class BindPlatformContract {
    public interface View extends IView {
        void uploadPlatformSuccess(int what);
    }

    public interface Presenter extends IPresenter {
        void titleWhat(int what, Toolbar toolbar);

    }

    public interface Model {}


}