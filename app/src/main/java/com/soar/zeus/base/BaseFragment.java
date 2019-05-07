package com.soar.zeus.base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.soar.zeus.event.FinishLiveEvent;
import com.soar.zeus.utils.ToastUtils;
import com.trello.navi2.component.support.NaviFragment;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * NAME：YONG_
 * Created at: 2018/12/11 16
 * Describe:
 */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends NaviFragment {

    public final String TAG = this.getClass().getSimpleName();

    public VM viewModel;
    public V binding;

    public Context context;

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, initContentView(inflater, container, savedInstanceState), container, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        context = getContext();

        //Databinding
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
                getActivity().setResult(result.resultCode, result.intent);
            getActivity().finish();
        });

        viewModel.uiLiveData.dialogEvent.observe(this, dialogInfo -> {
            //            if (dialogInfo.isShow)
            //                DialogView.showDialog(getContext(), dialogInfo.message);
            //            else
            //                DialogView.hideDialog();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
        viewModel = null;
        binding.unbind();
    }
}
