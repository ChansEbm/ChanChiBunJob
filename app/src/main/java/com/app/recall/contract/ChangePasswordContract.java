package com.app.recall.contract;

import android.content.Context;

import com.app.recall.view.IView;

/**
 * Created by KenChan on 16/12/24.
 */

public class ChangePasswordContract {
    public interface View extends IView {}

    public interface Presenter {}

    public interface Model {
        boolean checkPwd(String pwd);

        boolean pwdEquals(String pwd, String confirm);

        String getAuthorization(Context context);
    }


}