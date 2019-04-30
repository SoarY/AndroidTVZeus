package com.meixun.videosearch.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;

/**
 * NAME：YONG_
 * Created at: 2019/4/30
 * Describe: 防止RecyclerView快速移动焦点跑飞
 */
public class FocusStableLinearLayoutManager extends LinearLayoutManager {

    private int orientation = LinearLayoutManager.VERTICAL;

    public FocusStableLinearLayoutManager(Context context) {
        super(context);
    }

    public FocusStableLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.orientation = orientation;
    }

    public FocusStableLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public View onInterceptFocusSearch(View focused, int direction) {
        int currentPosition = getPosition(getFocusedChild());//这里要用这个方法
        int count = getItemCount();
        int lastVisiblePosition = findLastVisibleItemPosition();
        if (orientation == LinearLayoutManager.VERTICAL) {
            switch (direction) {
                case View.FOCUS_DOWN:
                    currentPosition++;
                    break;
                case View.FOCUS_UP:
                    currentPosition--;
                    break;
            }
        } else {
            switch (direction) {
                case View.FOCUS_RIGHT:
                    currentPosition++;
                    break;
                case View.FOCUS_LEFT:
                    currentPosition--;
                    break;
            }
        }
        if (currentPosition < 0 || currentPosition > count) {
            return focused;
        } else {
            if (currentPosition > lastVisiblePosition) {
                scrollToPosition(currentPosition);
            }
        }
        return super.onInterceptFocusSearch(focused, direction);
    }
}
