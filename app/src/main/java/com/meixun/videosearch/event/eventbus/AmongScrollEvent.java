package com.meixun.videosearch.event.eventbus;

import android.view.View;

/**
 * NAME：YONG_
 * Created at: 2019/4/26
 * Describe:
 */
public class AmongScrollEvent {
    public View v;
    public boolean hasFocus;

    public AmongScrollEvent(View v, boolean hasFocus) {
        this.v = v;
        this.hasFocus = hasFocus;
    }
}
