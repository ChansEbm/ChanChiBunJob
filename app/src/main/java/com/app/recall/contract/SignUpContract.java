package com.app.recall.contract;

import com.app.recall.view.IView;

/**
 * Created by KenChan on 16/12/16.
 */

public class SignUpContract {

    public interface View extends IView {}

    public interface Presenter {}

    public interface Model {
        boolean isEmailValid(String email);

        boolean isPasswordValid(String password);
    }


}