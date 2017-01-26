package com.app.recall.contract;

import com.app.recall.view.IView;

/**
 * Created by KenChan on 17/1/4.
 */

public class SendMyNameCardContract {


    public interface View extends IView {
        void handshakeFailed();
        void handshakeSuccess();
    }

    public interface Presenter {}

    public interface Model {}


}