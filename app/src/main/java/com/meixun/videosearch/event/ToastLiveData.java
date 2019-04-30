package com.meixun.videosearch.event;


import com.meixun.videosearch.base.BaseSingleLiveEvent;

/**
 * NAME：YONG_
 * Created at: 2018/12/21 11
 * Describe:
 */
public class ToastLiveData extends BaseSingleLiveEvent<String> {
    public void show(String message) {
        postValue(message);
    }
}
