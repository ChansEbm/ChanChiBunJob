package com.app.recall.model;

import android.os.Message;

import com.app.recall.contract.LoginContract;
import com.app.recall.presenter.LoginPresenterImpl;
import com.libra.sinvoice.SinVoicePlayer;
import com.libra.sinvoice.SinVoiceRecognition;

import java.io.UnsupportedEncodingException;

/**
 * Created by KenChan on 2016/12/21
 */

public class LoginModelImpl implements LoginContract.Model {
    @Override
    public void startSendUid(String uid) {

    }
    //    private final static int MSG_SET_RECG_TEXT = 1;
    //    private final static int MSG_RECG_START = 2;
    //    private final static int MSG_RECG_END = 3;
    //    private final static int MSG_PLAY_TEXT = 4;
    //
    //    private SinVoicePlayer mSinVoicePlayer;
    //    private SinVoiceRecognition mRecognition;
    //    private LoginPresenterImpl loginPresenter;
    //
    //    public LoginModelImpl(LoginPresenterImpl loginPresenter) {
    //        this.loginPresenter = loginPresenter;
    //    }
    //
    //    @Override
    //    public void startSendUid(String uid) {
    //
    //    }
    //
    //    @Override
    //    public void onSinVoicePlayStart() {
    //
    //    }
    //
    //    @Override
    //    public void onSinVoicePlayEnd() {
    //
    //    }
    //
    //    @Override
    //    public void onSinToken(int[] tokens) {
    //
    //    }
    //
    //    @Override
    //    public void onSinVoiceRecognitionStart() {
    //
    //    }
    //
    //    @Override
    //    public void onSinVoiceRecognition(char ch) {
    //
    //    }
    //
    //    @Override
    //    public void onSinVoiceRecognitionEnd(int result) {
    //
    //    }
    //
    //    private class RecHandler extends android.os.Handler {
    //        private StringBuilder builder = new StringBuilder();
    //
    //        @Override
    //        public void handleMessage(Message msg) {
    //            switch (msg.what) {
    //                case MSG_SET_RECG_TEXT:
    //                    builder.append((char) msg.arg1);
    //                    break;
    //                case MSG_RECG_START:
    //                    builder.delete(0, builder.length());
    //                    break;
    //                case MSG_RECG_END:
    //                    if (builder.length() > 0) {
    //                        byte[] bytes = new byte[builder.length()];
    //                        for (int i = 0; i < bytes.length; i++) {
    //                            bytes[i] = (byte) builder.charAt(i);
    //
    //                        }
    //                        try {
    //                            String text = new String(bytes, "UTF-8");
    //
    //                        } catch (UnsupportedEncodingException e) {
    //                            e.printStackTrace();
    //                        }
    //                    }
    //                    break;
    //                case MSG_PLAY_TEXT:
    //                    break;
    //            }
    //        }
    //    }
}