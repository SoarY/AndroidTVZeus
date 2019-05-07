package com.soar.zeus.base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.KeyEvent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.soar.zeus.event.FinishLiveEvent;
import com.soar.zeus.utils.ToastUtils;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * NAME：YONG_
 * Created at: 2018/12/7 11
 * Describe:
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends NaviAppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private static final long KEY_EVENT_TIME = 100;

    private long mLastEventTime;

    public VM viewModel;
    public V binding;

    public Context context;

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(Bundle savedInstanceState);

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        context=getBaseContext();

        //Databinding
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));
        binding.setVariable(initVariableId(), viewModel = getViewModel());
        viewModel.injectLifecycleProvider(NaviLifecycle.createActivityLifecycleProvider(this));

        //注册常用liveData观察者
        registorUILiveData();
    }

    private VM getViewModel() {
        Type type = getClass().getGenericSuperclass();
        Class modelClass;
        if (type instanceof ParameterizedType)
            modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        else
            modelClass = BaseViewModel.class;//如果没有指定泛型参数，则默认使用BaseViewModel
        return (VM) ViewModelProviders.of(this).get(modelClass);
    }

    private void registorUILiveData() {
        viewModel.uiLiveData.activityEvent.observe(this, s -> ARouter.getInstance().build(s).navigation());

        viewModel.uiLiveData.toastEvent.observe(this, s -> ToastUtils.showToast(s));

        viewModel.uiLiveData.finishEvent.observe(this, result -> {
            if (result != null && result.state == FinishLiveEvent.FINISH_RESULT)
                setResult(result.resultCode, result.intent);
            finish();
        });

        viewModel.uiLiveData.dialogEvent.observe(this, dialogInfo -> {
            //            if (dialogInfo.isShow)
            //                DialogView.showDialog(this, dialogInfo.message);
            //            else
            //                DialogView.hideDialog();
        });
    }

    /**
     * 按键速度控制
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent ev) {
        if (KeyEvent.ACTION_DOWN == ev.getAction()) {
            switch (ev.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                case KeyEvent.KEYCODE_DPAD_DOWN:
                case KeyEvent.KEYCODE_DPAD_UP:
                    if (ev.getEventTime() - mLastEventTime < KEY_EVENT_TIME)
                        return true;
                    mLastEventTime = ev.getEventTime();
                    break;
            }
        } else if (KeyEvent.ACTION_UP == ev.getAction()) {
            mLastEventTime = 0;
        }
        return super.dispatchKeyEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
        viewModel = null;
        binding.unbind();
    }
}
