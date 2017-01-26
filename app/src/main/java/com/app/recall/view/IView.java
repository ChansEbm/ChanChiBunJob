package com.app.recall.view;

import android.support.v7.widget.Toolbar;

/**
 * Created by KenChan on 16/12/13.
 */

public interface IView {

    void networkError();

    int startsBarHeight();

    int contentViewId();

    void toolbarPrepared(Toolbar toolbar);

    boolean isSetupToolbarDefaultConfig();

}
