package com.app.recall.contract;

import com.app.recall.view.IView;

/**
 * Created by KenChan on 16/12/26.
 */

public class NotificationsContract {
    public interface View extends IView {
        void onAttach(boolean sound, boolean vibrate);
    }

    public interface Presenter {}

    public interface Model {}


}