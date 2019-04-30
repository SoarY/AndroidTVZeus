package com.meixun.videosearch.event;


import com.meixun.videosearch.base.BaseSingleLiveEvent;

/**
 * NAME：YONG_
 * Created at: 2018/12/21 13
 * Describe:
 */
public class ActivityLiveData extends BaseSingleLiveEvent<String> {
    public void toActivity(String act) {
        postValue(act);
    }
}
