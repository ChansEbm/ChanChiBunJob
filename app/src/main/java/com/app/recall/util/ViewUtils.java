package com.app.recall.util;

import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ChanZeeBm on 2016/3/16.
 * 封装view的特定动作
 */
public class ViewUtils {
    public final static int LEFT = 0x001;
    public final static int RIGHT = 0x002;
    public final static int TOP = 0x003;
    public final static int BOTTOM = 0x004;

    protected static void deleteDrawable(TextView textView) {
        if (textView == null) { return; }
        textView.setCompoundDrawables(null, null, null, null);
    }

    protected static void setCompoundDrawable(TextView textView, int resId, int side) {
        if (textView == null) { return; }
        Drawable drawable = textView.getContext().getResources().getDrawable(resId);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        } else { return; }
        switch (side) {
            case LEFT:
                textView.setCompoundDrawables(drawable, null, null, null);
                break;
            case RIGHT:
                textView.setCompoundDrawables(null, null, drawable, null);
                break;
            case TOP:
                textView.setCompoundDrawables(null, drawable, null, null);
                break;
            case BOTTOM:
                textView.setCompoundDrawables(null, null, null, drawable);
                break;
        }
    }

    public static boolean isEdtEmpty(EditText editText) {
        if (editText == null) { return true; }
        final String string = editText.getText().toString();
        return TextUtils.isEmpty(string);
    }

    public static void clearEdt(EditText editText) {
        editText.setText("");
    }

    private static CountDownTimer countDownTimer;//计时器

    //验证码倒计时
    public static void startCountDown(final Button button, final String start, final String end,
                                      long millions) {
        if (button == null) { return; }
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(millions, 10) {
                @Override
                public void onTick(long millisUntilFinished) {
                    button.setEnabled(false);
                    //                    button.setTextColor(
                    //                            button.getResources().getColor(R.color
                    // .color_text_light_gravy));
                    button.setText((millisUntilFinished / 1000) + start);
                }

                @Override
                public void onFinish() {
                    button.setEnabled(true);
                    //                    button.setTextColor(button.getResources().getColor(R
                    // .color.color_white));
                    button.setText(end);
                }
            };
        }
        countDownTimer.start();
    }

    public static void endCountDown() {
        if (countDownTimer != null) {
            countDownTimer.onFinish();
            countDownTimer = null;
        }
    }


}
