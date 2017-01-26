package com.app.recall.contract;

import android.support.annotation.IntDef;

import com.app.recall.view.IView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by KenChan on 16/12/21.
 */

public class LoginContract {
    public interface View extends IView {

    }

    public interface Presenter {

    }

    public interface Model {
        void startSendUid(String uid);
    }


}