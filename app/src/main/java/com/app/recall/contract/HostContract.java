package com.app.recall.contract;

import android.support.annotation.IntDef;

import com.app.recall.view.IView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by KenChan on 16/12/13.
 */

public class HostContract {
    public interface View extends IView {
        void matcher(String userName, String profileImg, String uid);

        void nobodyFound();
    }

    public interface Presenter {}

    public interface Model {
        int PAUSE = 1;
        int DESTROY = 2;

        @IntDef({PAUSE, DESTROY})
        @Retention(RetentionPolicy.SOURCE)
        @interface LifeCircle {}

        int PLAYER_START = 0x01;
        int RECOGNITION_START = 0x02;
        int PLAYER_STOP = 0x03;
        int RECOGNITION_STOP = 0x04;

        @IntDef({PLAYER_START, RECOGNITION_START, PLAYER_STOP, RECOGNITION_STOP})
        @Retention(RetentionPolicy.SOURCE)
        @interface SinvoiceState {

        }

        void startSendUid();

        void stopSendUid();

        void startRecognition();

        void stopRecognition();

        void lifeCircle(@LifeCircle int lifeCircle);
    }


}