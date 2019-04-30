package com.meixun.videosearch.event.eventbus;

/**
 * NAME：YONG_
 * Created at: 2019/4/26
 * Describe:
 */
public class KeyDownEvent {
    public int keyEvent;
    public int position;

    public KeyDownEvent(int keyEvent) {
        this.keyEvent = keyEvent;
    }

    public KeyDownEvent(int keyEvent, int position) {
        this.keyEvent = keyEvent;
        this.position = position;
    }
}
