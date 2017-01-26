package com.app.recall.contract;

import com.app.recall.view.IView;

/**
 * Created by KenChan on 17/1/25.
 */

public class PersonalInfoContract {
    public interface View extends IView {
        void  profileUploadSuccess();
    }

    public interface Presenter {}

    public interface Model {}


}