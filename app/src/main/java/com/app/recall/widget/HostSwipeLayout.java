package com.app.recall.widget;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.app.recall.R;

/**
 * Created by KenChan on 17/1/4.
 */

public class HostSwipeLayout extends FrameLayout {
    private ViewDragHelper helper;
    private View captureView = null;

    private Point originPoint = new Point();
    private static final String TAG = "recall";
    private Callback callback;

    public HostSwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        helper = ViewDragHelper.create(this, 0.5f, new ViewDragHelperCallback());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return helper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        helper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (helper.continueSettling(true)) invalidate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        captureView = findViewById(R.id.content);
        originPoint.x = captureView.getLeft();
        originPoint.y = captureView.getTop();
    }

    class ViewDragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == captureView;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return Math.min(0, Math.max(top, -child.getHeight()));
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            Log.i(TAG, "onViewReleased: " + getHeight() + ",bottom:" + releasedChild.getBottom());
            if (releasedChild.getBottom() <= getHeight() - (getHeight() / 5) && callback
                    .isPlatformSelected()) {
                Log.i(TAG, "onViewReleased: out");
                helper.settleCapturedViewAt(originPoint.x, -releasedChild.getHeight());
                invalidate();
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) callback.onLayoutDone();
                    }
                }, 300);
            } else {
                helper.settleCapturedViewAt(originPoint.x, originPoint.y);
                invalidate();
                Toast.makeText(getContext(), "Please at least select one platform to send", Toast
                        .LENGTH_SHORT).show();
                Log.d(TAG, "onViewReleased() returned: ");
            }
            super.onViewReleased(releasedChild, xvel, yvel);
        }

    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        boolean isPlatformSelected();

        void onLayoutDone();
    }

}
