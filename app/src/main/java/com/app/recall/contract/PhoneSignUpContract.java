package com.app.recall.contract;

import com.app.recall.view.IView;

/**
 * Created by KenChan on 16/12/16.
 */

public class PhoneSignUpContract {
    public interface View extends IView {

        void sentVerificationSuccess();
    }

    public interface Presenter {}

    public interface Model {}


}