package com.soar.zeus.event;


import com.soar.zeus.base.BaseSingleLiveEvent;

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
