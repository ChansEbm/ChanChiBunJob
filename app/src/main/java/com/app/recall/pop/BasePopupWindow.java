package com.app.recall.pop;

import android.Manifest;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.app.recall.R;


/**
 * Created by ChanZeeBm on 2015/11/18.
 */
public abstract class BasePopupWindow extends PopupWindow implements PopupWindow
        .OnDismissListener, View.OnClickListener {
    protected AppCompatActivity appCompatActivity;//附上的窗体
    protected Context context;
    protected View rootView;

    public BasePopupWindow(Context context) {
        this.context = context;
        this.appCompatActivity = (AppCompatActivity) context;
        rootView = LayoutInflater.from(context).inflate(getPopupLayout(), null, false);
        setOnDismissListener(this);//设置窗体消失监听器
        setFocusable(true);//设置点击窗体外允许消失
        setBackgroundDrawable(new BitmapDrawable());//设置背景颜色为无,目的是...
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);//设置宽度
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);//设置高度
        setContentView(rootView);//设置显示的布局
        setAnimationStyle(R.style.PopupAnim);//设置进出动画
    }


    /**
     * 当窗体消失的时候,把背景颜色恢复
     */
    @Override
    public void onDismiss() {
        setWindowBackground(appCompatActivity, 1f);
    }

    /**
     * 当窗体出现的时候,把背景设为60%透明度
     *
     * @param parent
     * @param gravity
     * @param x
     * @param y
     */
    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        setWindowBackground(appCompatActivity, 0.6f);
        super.showAtLocation(parent, gravity, x, y);
    }

    public void showAtDefaultLocation() {
        setWindowBackground(appCompatActivity, 0.6f);
        super.showAtLocation(appCompatActivity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    /**
     * 获取子类布局Id
     *
     * @return 子类布局Id
     */
    public abstract int getPopupLayout();


    public void setWindowBackground(AppCompatActivity appCompatActivity, float alpha) {
        Window window = appCompatActivity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = alpha;
        window.setAttributes(layoutParams);
    }

    boolean checkCameraPermission() {
        return PermissionChecker.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                PermissionChecker.PERMISSION_GRANTED;
    }

    boolean checkAlbumPermission() {
        return PermissionChecker.checkSelfPermission(context, Manifest.permission
                .READ_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED;
    }
}
