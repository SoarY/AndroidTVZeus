package com.meixun.videosearch.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * 懒加载(适用ViewPage+Fragment)
 * 单个Fragment setUserVisibleHint不会跟随hide() show()方法调用
 * ViewPage+Fragment可调是因为 FragmentStatePagerAdapter,FragmentagerAdapter系统源码也是调用了该方法.
 * 若单个Fragment想实现懒加载可复写onHiddenChanged方法自己定制(也可不使用直接继承BaseFragment)
 * 也可用三方(https://github.com/xmagicj/LazyFragment)
 */
public abstract class BaseLazyFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment<V,VM> {

    //是否可见
    protected boolean isVisible;
    // 标志位，标志Fragment已经初始化完成。
    public boolean isPrepared;

    protected abstract void lazyData();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        loadData();
    }

    /**
     * 实现Fragment数据的懒加载
     *
     * @param isVisibleToUser Fragment是否可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            loadData();
        } else {
            isVisible = false;
        }
    }

    private void loadData() {
        if (isPrepared && isVisible) {
            isPrepared = false;
            lazyData();
        }
    }
}
