package com.app.recall.model;

import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.app.recall.base.APP;
import com.app.recall.contract.HostContract;
import com.app.recall.entity.retrofit.UserEntity;
import com.app.recall.presenter.HostPresenter;
import com.github.pwittchen.prefser.library.Prefser;
import com.libra.sinvoice.Common;
import com.libra.sinvoice.SinVoicePlayer;
import com.libra.sinvoice.SinVoiceRecognition;

import java.io.UnsupportedEncodingException;

/**
 * Created by KenChan on 2016/12/13
 */

public class HostModelImpl implements HostContract.Model, SinVoiceRecognition.Listener,
        SinVoicePlayer.Listener {
    private final static int MSG_SET_RECG_TEXT = 1;
    private final static int MSG_RECG_START = 2;
    private final static int MSG_RECG_END = 3;
    private final static int MSG_PLAY_TEXT = 4;
    private static final String TAG = "recall";

    private SinVoicePlayer mSinVoicePlayer;
    private SinVoiceRecognition mRecognition;
    private HostPresenter hostPresenter;
    private RecHandler handler;

    private static String userId = "";


    public HostModelImpl(HostPresenter hostPresenter) {
        this.hostPresenter = hostPresenter;
        mSinVoicePlayer = new SinVoicePlayer();
        mSinVoicePlayer.init(hostPresenter.getContext());
        mSinVoicePlayer.setListener(this);

        mRecognition = new SinVoiceRecognition();
        mRecognition.init(hostPresenter.getContext());
        mRecognition.setListener(this);

        handler = new RecHandler(hostPresenter);
    }

    public void startSendUid() {
        if (TextUtils.isEmpty(userId)) {
            Prefser prefser = new Prefser(hostPresenter.getContext());
            UserEntity.UserBean userBean = prefser.get(APP.USER_INFO, UserEntity.UserBean.class,
                    new UserEntity.UserBean());
            userId = userBean.getUid();
        }
        if (TextUtils.isEmpty(userId)) return;
        try {
            //            byte[] bytes = userId.getBytes("UTF8");
//                        byte[] bytes = "5858aa7e4d2d6f09c3616176".getBytes("UTF8");
            byte[] bytes = userId.getBytes("UTF8");
            int len = bytes.length;
            int[] tokens = new int[len];

            int maxEncoderIndex = mSinVoicePlayer.getMaxEncoderIndex();
            for (int i = 0; i < len; ++i) {
                if (maxEncoderIndex < 255) {
                    tokens[i] = Common.DEFAULT_CODE_BOOK.indexOf(userId.charAt(i));
                } else {
                    tokens[i] = bytes[i];
                }
            }
            mSinVoicePlayer.play(tokens, len, true, 2000);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void stopSendUid() {
        mSinVoicePlayer.stop();
    }

    public void startRecognition() {
        try {
            UserEntity.UserBean userBean = new Prefser(hostPresenter.getContext()).get(APP
                    .USER_INFO, UserEntity.UserBean.class, new UserEntity.UserBean());
            if (TextUtils.isEmpty(userBean.getUid())) return;
            mRecognition.start(userBean.getUid().getBytes("UTF8").length, false);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void stopRecognition() {
        mRecognition.stop();
    }

    public void lifeCircle(@LifeCircle int lifeCircle) {
        switch (lifeCircle) {
            case HostContract.Model.DESTROY:
                mRecognition.uninit();
                mSinVoicePlayer.uninit();
                break;
            case HostContract.Model.PAUSE:
                mSinVoicePlayer.stop();
                mRecognition.stop();
                break;
        }
    }

    @Override
    public void onSinVoicePlayStart() {

    }

    @Override
    public void onSinVoicePlayEnd() {

    }

    @Override
    public void onSinToken(int[] tokens) {

    }

    @Override
    public void onSinVoiceRecognitionStart() {
        handler.sendEmptyMessage(MSG_RECG_START);
        Log.i(TAG, "onSinVoiceRecognitionStart: ");
    }

    @Override
    public void onSinVoiceRecognition(char ch) {
        handler.sendMessage(handler.obtainMessage(MSG_SET_RECG_TEXT, ch, 0));
        Log.d(TAG, "onSinVoiceRecognition: ");
    }

    @Override
    public void onSinVoiceRecognitionEnd(int result) {
        handler.sendMessage(handler.obtainMessage(MSG_RECG_END, result, 0));
        Log.e(TAG, "onSinVoiceRecognitionEnd: ");
    }

    private static class RecHandler extends android.os.Handler {
        private HostPresenter hostPresenter;
        private int count = 0;
        private char[] args = new char[100];

        RecHandler(HostPresenter hostPresenter) {
            this.hostPresenter = hostPresenter;
        }

        //5853b44e4d2d6f09c3616151
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "handleMessage: " + msg.what + ",msg.arg:" + msg.arg1);
            switch (msg.what) {
                case MSG_SET_RECG_TEXT:
                    //                    builder.append((char) msg.arg1);
                    args[count++] = (char) msg.arg1;
                    break;
                case MSG_RECG_START:
                    //                    builder.delete(0, builder.length());
                    count = 0;
                    break;
                case MSG_RECG_END:
                    if (count > 0) {
                        byte[] bytes = new byte[count];
                        for (int i = 0; i < count; i++) {
                            bytes[i] = (byte) args[i];
                        }
                        try {
                            String uid = new String(bytes, "UTF8");
                            if (!uid.equals(userId)) hostPresenter.findUser(uid);
                            Log.d(TAG, "handleMessage() returned: " + uid + ",my uid:" + userId);
                            Log.i(TAG, "handleMessage: " + uid.equals(userId));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    //                    if (builder.length() > 0 && !TextUtils.equals(builder
                    // .toString(), userId))
                    //                        hostPresenter.findUser(builder.toString());
                    //                    if (builder.length() > 0) {
                    //                        byte[] bytes = new byte[builder.length()];
                    //                        for (int i = 0; i < bytes.length; i++) {
                    //                            bytes[i] = (byte) builder.charAt(i);
                    //                        }
                    //                        try {
                    //                            String uid = new String(bytes, "UTF8");
                    //                            if (!uid.equals(userId)) hostPresenter.findUser
                    // (uid);
                    //                            Log.d(TAG, "handleMessage() returned: " + uid +
                    // ",my id :" + userId);
                    //                            Log.i(TAG, "handleMessage: " + uid.equals
                    // (userId));
                    //                        } catch (UnsupportedEncodingException e) {
                    //                            e.printStackTrace();
                    //                        }
                    //                    }
                    //                    if (builder.length() > 0) {
                    //                        byte[] bytes = new byte[builder.length()];
                    //                        for (int i = 0; i < bytes.length; i++) {
                    //                            bytes[i] = (byte) builder.charAt(i);
                    //                        }
                    //                        try {
                    //                            String text = new String(bytes, "UTF8");
                    //                            //                            if (text.equals
                    // (userId)) {
                    //                            //                                return;
                    //                            //                            }
                    //                        } catch (UnsupportedEncodingException e) {
                    //                            e.printStackTrace();
                    //                        }
                    //                    }
                    break;
                case MSG_PLAY_TEXT:
                    break;
            }
        }
    }
}