package com.meixun.videosearch.event;


import com.meixun.videosearch.base.BaseSingleLiveEvent;

/**
 * NAME：YONG_
 * Created at: 2018/12/21 14
 * Describe:
 */
public class DialogLiveData extends BaseSingleLiveEvent<DialogLiveData.DialogInfo> {

    public void show(String message){
        postValue(new DialogInfo(true,message));
    }

    public void hide(){
        postValue(new DialogInfo());
    }

    public class DialogInfo{
        public boolean isShow;
        public String message;

        public DialogInfo() {}

        public DialogInfo(boolean isShow, String message) {
            this.isShow=isShow;
            this.message=message;
        }
    }
}
