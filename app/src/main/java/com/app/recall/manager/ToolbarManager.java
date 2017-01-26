package com.app.recall.manager;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;


import com.app.recall.R;

import java.lang.reflect.Field;

/**
 * Created by ChanZeeBm on 2015/9/8.
 * 看名字
 */
public class ToolbarManager {
    private static ToolbarManager manager;

    public static ToolbarManager getInstance() {
        if (manager == null) { manager = new ToolbarManager(); }
        return manager;
    }

    public  void registerNavId(@NonNull Toolbar toolbar) {
        ImageButton imageButton;
        try {
            Field f = toolbar.getClass().getDeclaredField("mNavButtonView");
            f.setAccessible(true);
            imageButton = (ImageButton) f.get(toolbar);
            imageButton.setId(R.id.nav);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    //    private TextView title;
    //    private AppCompatActivity appCompatActivity;
    //    private Toolbar toolbar;
    //
    //    //初始化
    //    public ToolbarManager(AppCompatActivity appCompatActivity) {
    //        this.appCompatActivity = appCompatActivity;
    //        toolbar = (Toolbar) appCompatActivity.findViewById(R.id.toolbar);
    //
    //        if (toolbar == null) {
    //            return;
    //        }
    //        toolbar.setTitle("");
    //        appCompatActivity.setSupportActionBar(toolbar);
    //        title = (TextView) toolbar.findViewById(R.id.title);
    //
    //    }
    //
    //    public ToolbarManager(Fragment fragment, Toolbar toolbar) {
    //        appCompatActivity = (AppCompatActivity) fragment.getActivity();
    //        this.toolbar = toolbar;
    //        if (toolbar == null) {
    //        }
    //        appCompatActivity.setSupportActionBar(toolbar);
    //        this.toolbar.setTitle("");
    //        title = (TextView) toolbar.findViewById(R.id.title);
    //    }
    //
    //    //设置居中标题
    //    public ToolbarManager setTitle(String text) {
    //        title.setText(text);
    //        return this;
    //    }
    //
    //    //设置标题背景
    //    public ToolbarManager setTitleBackgroundColor(int color) {
    //        toolbar.setBackgroundColor(title.getResources().getColor(color));
    //        return this;
    //    }
    //
    //    //设置居中标题
    //    public ToolbarManager setTitle(int resId) {
    //        title.setText(resId);
    //        return this;
    //    }
    //
    //    //设置ToolBar自带主标题
    //    public ToolbarManager setToolBarTitle(String toolBarTitle) {
    //        if (!TextUtils.isEmpty(toolBarTitle)) { toolbar.setTitle(toolBarTitle); } else {
    //            toolbar.setTitle("");
    //        }
    //        toolbar.setTitleTextColor(ContextCompat.getColor(appCompatActivity, android.R.color
    // .white));
    //        return this;
    //    }
    //
    //    //设置ToolBar自带主标题
    //    public ToolbarManager setToolBarTitle(int resId) {
    //        if (resId != 0) { toolbar.setTitle(resId); } else { setToolBarTitle(""); }
    //        toolbar.setTitleTextColor(ContextCompat.getColor(appCompatActivity, android.R.color
    // .white));
    //        return this;
    //    }
    //
    //    //设置ToolBar自带主标题字体大小
    //    public ToolbarManager setToolBarTitleSize(int size) {
    //        TextView textView = getTitleTextView();
    //        textView.setTextSize(size);
    //        return this;
    //    }
    //
    //    //设置ToolBar自带副标题
    //    public ToolbarManager setToolBarSubTile(String subTile) {
    //        if (!TextUtils.isEmpty(subTile)) { toolbar.setSubtitle(subTile); } else {
    //            toolbar.setSubtitle("");
    //        }
    //        toolbar.setSubtitleTextColor(
    //                ContextCompat.getColor(appCompatActivity, android.R.color.white));
    //        return this;
    //    }
    //
    //    //设置ToolBar自带副标题
    //    public ToolbarManager setToolBarSubTile(int resId) {
    //        if (resId != 0) { toolbar.setSubtitle(resId); } else { setToolBarSubTile(""); }
    //        toolbar.setSubtitleTextColor(
    //                ContextCompat.getColor(appCompatActivity, android.R.color.white));
    //        return this;
    //    }
    //
    //    //设置ToolBar自带副标题字体大小
    //    public ToolbarManager setToolBarSubTitleSize(int size) {
    //        TextView textView = getTitleSubTextView();
    //        textView.setTextSize(size);
    //        return this;
    //    }
    //
    //    //隐藏居中标题
    //    public ToolbarManager hideTitle() {
    //        toolbar.setVisibility(View.GONE);
    //        return this;
    //    }
    //
    //    public ToolbarManager hideTitleBar() {
    //        if (toolbar != null) { toolbar.setVisibility(View.GONE); }
    //        return this;
    //    }
    //
    //    public ToolbarManager showTitleBar() {
    //        if (toolbar != null) { toolbar.setVisibility(View.VISIBLE); }
    //        return this;
    //    }
    //
    //
    //    //设置Nav图标
    //    public ToolbarManager setNavigationIcon(int resId) {
    //        if (resId != 0) {
    //            toolbar.setNavigationIcon(resId);
    //        }
    //        return this;
    //    }
    //
    //    //设置Nav图标
    //    public ToolbarManager setNavigationIcon(Drawable drawable) {
    //        toolbar.setNavigationIcon(drawable);
    //        return this;
    //    }
    //
    //    //设置Nav监听器
    //    public ToolbarManager setNavigationListener(View.OnClickListener listener) {
    //        setSupportActionBar();
    //        if (toolbar.getNavigationIcon() == null) {
    //        } else {
    //            toolbar.setNavigationOnClickListener(listener);
    //        }
    //        return this;
    //    }
    //
    //    //设置ToolBar为ActionBar
    //    public ToolbarManager setSupportActionBar() {
    //        if (appCompatActivity != null) {
    //            if (appCompatActivity.getSupportActionBar() == null) {
    //                appCompatActivity.setSupportActionBar(toolbar);
    //            }
    //        }
    //        return this;
    //    }
    //
    //    //标题默认样式
    //    public ToolbarManager defaultToolBar(View.OnClickListener onClickListener) {
    //        setNavigationIcon(
    //                R.drawable.ic_arrow_back_white_24dp).setNavButtonId().setNavigationListener(
    //                onClickListener);
    //        return this;
    //    }
    //
    //    public int getToolBarHeight() {
    //        return toolbar.getHeight();
    //    }
    //
    //    public Toolbar getToolbar() {
    //        return toolbar;
    //    }
    //
    //    //通过反射获得Title主标题
    //    private TextView getTitleTextView() {
    //        TextView textView = null;
    //        try {
    //            Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
    //            f.setAccessible(true);
    //            textView = (TextView) f.get(toolbar);
    //        } catch (NoSuchFieldException | IllegalAccessException e) {
    //            e.printStackTrace();
    //        }
    //        return textView;
    //    }
    //
    //    //通过反射获得Title副标题
    //    private TextView getTitleSubTextView() {
    //        TextView textView = null;
    //        try {
    //            Field f = toolbar.getClass().getDeclaredField("mSubtitleTextView");
    //            f.setAccessible(true);
    //            textView = (TextView) f.get(toolbar);
    //        } catch (NoSuchFieldException | IllegalAccessException e) {
    //            e.printStackTrace();
    //        }
    //        return textView;
    //    }
    //
    //    private ToolbarManager setNavButtonId() {
    //        ImageButton imageButton;
    //        try {
    //            Field f = toolbar.getClass().getDeclaredField("mNavButtonView");
    //            f.setAccessible(true);
    //            imageButton = (ImageButton) f.get(toolbar);
    //            imageButton.setId(R.id.nav);
    //        } catch (NoSuchFieldException | IllegalAccessException e) {
    //            e.printStackTrace();
    //        }
    //        return this;
    //    }

}
