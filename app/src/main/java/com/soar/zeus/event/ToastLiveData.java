package com.soar.zeus.event;


import com.soar.zeus.base.BaseSingleLiveEvent;

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
