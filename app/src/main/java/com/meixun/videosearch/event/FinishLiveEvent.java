package com.meixun.videosearch.event;

import android.content.Intent;

import com.meixun.videosearch.base.BaseSingleLiveEvent;


/**
 * NAME：YONG_
 * Created at: 2018/12/21 13
 * Describe:
 */
public class FinishLiveEvent extends BaseSingleLiveEvent<FinishLiveEvent.Result> {
    public static final int FINISH=1;
    public static final int FINISH_RESULT=2;

    public void finish(){
        postValue(new Result(FINISH));
    }

    public void finishResult(int resultCode, Intent intent){
        postValue(new Result(FINISH_RESULT,resultCode,intent));
    }

    public class Result{
        public int state;
        public int resultCode;
        public Intent intent;

        public Result(int state) {
            this.state=state;
        }

        public Result(int state, int resultCode, Intent intent) {
            this.state=state;
            this.resultCode=resultCode;
            this.intent=intent;
        }
    }
}
