package com.meixun.videosearch.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class NoScrollViewPager extends ViewPager {
    private boolean noScroll = true;

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    /**
     * 屏蔽ViewPage按键左右滑动
     */
    @Override
    public boolean executeKeyEvent(@NonNull KeyEvent event) {
        if (noScroll && event.getAction() == KeyEvent.ACTION_DOWN)
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    return false;
            }
        return super.executeKeyEvent(event);
    }

//    /**
//     * 屏蔽ViewPage Touch左右滑动
//     */
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent arg0) {
//        if (noScroll)
//            return false;
//        else
//            return super.onInterceptTouchEvent(arg0);
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent arg0) {
//        /* return false;//super.onTouchEvent(arg0); */
//        if (noScroll)
//            return false;
//        else
//            return super.onTouchEvent(arg0);
//    }
}
