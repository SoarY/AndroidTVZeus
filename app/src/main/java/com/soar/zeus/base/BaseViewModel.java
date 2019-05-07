package com.soar.zeus.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.soar.zeus.constant.Constant;
import com.soar.zeus.event.ActivityLiveData;
import com.soar.zeus.event.DialogLiveData;
import com.soar.zeus.event.FinishLiveEvent;
import com.soar.zeus.event.ToastLiveData;
import com.soar.zeus.view.LoadingView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * NAME：YONG_
 * Created at: 2018/12/7 11
 * Describe:
 */
public class BaseViewModel extends AndroidViewModel {

    private LifecycleProvider<ActivityEvent> lifecycle;

    public UIChangeLiveData uiLiveData = new UIChangeLiveData();

    public ObservableInt whichChild = new ObservableInt();
    public ObservableField<LoadingView.State> loadState = new ObservableField<>(LoadingView.State.done);

    public void viewState(int i, LoadingView.State state) {
        whichChild.set(i);
        loadState.set(state);
    }

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 注入RxLifecycle生命周期
     */
    public void injectLifecycleProvider(LifecycleProvider<ActivityEvent> lifecycle) {
        this.lifecycle = lifecycle;
    }

    public LifecycleProvider<ActivityEvent> getLifecycleProvider() {
        return lifecycle;
    }

    /**
     * Focus焦点框
     */
    public View.OnFocusChangeListener onFocusListener = (v, hasFocus) -> {
        if (hasFocus)
            v.animate()
                    .scaleX(Constant.scaleX)
                    .scaleY(Constant.scaleY)
                    .setDuration(Constant.duration).start();
        else
            v.animate()
                    .scaleX(Constant.scale)
                    .scaleY(Constant.scale)
                    .setDuration(Constant.duration).start();
    };

    public class UIChangeLiveData {
        public ActivityLiveData activityEvent = new ActivityLiveData();
        public FinishLiveEvent finishEvent = new FinishLiveEvent();
        public ToastLiveData toastEvent = new ToastLiveData();
        public DialogLiveData dialogEvent = new DialogLiveData();
    }

    /**
     * 若有引用到Activity,比如Context等
     *
     * 在Activity销毁时,ViewModel重写此方法释放引用
     */
    public void onDestroy() {
    }
}
