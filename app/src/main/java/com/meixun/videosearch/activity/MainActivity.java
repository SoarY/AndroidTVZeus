package com.meixun.videosearch.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.meixun.videosearch.R;
import com.meixun.videosearch.adapter.ContentPagerAdapter;
import com.meixun.videosearch.base.BaseActivity;
import com.meixun.videosearch.base.BaseViewModel;
import com.meixun.videosearch.constant.RouteConstants;
import com.meixun.videosearch.databinding.ActivityMainBinding;
import com.meixun.videosearch.databinding.LayTabBinding;
import com.meixun.videosearch.event.eventbus.KeyDownEvent;
import com.meixun.videosearch.event.eventbus.MeKeyDownEvent;
import com.meixun.videosearch.fragment.HomeFragment;
import com.meixun.videosearch.fragment.MeFragment;
import com.meixun.videosearch.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@Route(path = RouteConstants.Rule.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity<ActivityMainBinding, BaseViewModel> {

    private View tabView;
    private long mExitTime;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toSplash();
        initView();
    }

    private void toSplash() {
        ARouter.getInstance()
                .build(RouteConstants.Rule.SPLASH_ACTIVITY)
                .navigation();
    }

    private void initView() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);

        ContentPagerAdapter pagerAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(getString(R.string.tab_me), MeFragment.newInstance());
        pagerAdapter.addFragment(getString(R.string.tab_home), HomeFragment.newInstance());
        pagerAdapter.addFragment(getString(R.string.tab_abc), HomeFragment.newInstance());
        pagerAdapter.addFragment(getString(R.string.tab_abc), HomeFragment.newInstance());
        pagerAdapter.addFragment(getString(R.string.tab_abc), HomeFragment.newInstance());
        pagerAdapter.addFragment(getString(R.string.tab_abc), HomeFragment.newInstance());
        pagerAdapter.addFragment(getString(R.string.tab_abc), HomeFragment.newInstance());
        binding.viewPager.setOffscreenPageLimit(pagerAdapter.getCount());
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        initTab(pagerAdapter);
    }

    private void initTab(ContentPagerAdapter pagerAdapter) {
        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            LayTabBinding mTabBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.lay_tab, null, false);
            mTabBinding.tvTitle.setText(pagerAdapter.getPageTitle(i));
            binding.tabLayout.getTabAt(i).setCustomView(mTabBinding.getRoot());
            ViewGroup tab = binding.tabLayout.getTabAt(i).view;
            tab.setBackgroundColor(Color.TRANSPARENT);
            tab.setTag(i);

            tab.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    tabView = v;
                    binding.tabLayout.getTabAt((Integer) v.getTag()).select();
                    mTabBinding.tvTitle.setTextColor(getResources().getColor(R.color.colorTabText));
                } else {
                    boolean onlyFocus = isOnlyFocus();
                    mTabBinding.tvTitle.setTextColor(getResources().getColor(onlyFocus ? R.color.colorTabText : R.color.colorAccent));
                    mTabBinding.tabIndicator.setVisibility(onlyFocus ? View.INVISIBLE : View.VISIBLE);
                }
            });

            tab.setOnClickListener(v -> ToastUtils.showToast((Integer) v.getTag() + ""));

            tab.setOnKeyListener((v, keyCode, event) -> {
                if ((event.getAction() != KeyEvent.ACTION_DOWN)) return false;
                switch (keyCode) {
                    case KeyEvent.KEYCODE_DPAD_RIGHT:
                        if ((Integer) v.getTag() == binding.tabLayout.getTabCount() - 1)
                            return true;
                        break;
                }
                return false;
            });
        }
    }

    private boolean isOnlyFocus() {
        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            ViewGroup view = binding.tabLayout.getTabAt(i).view;
            if (view.hasFocus())
                return true;
        }
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MeKeyDownEvent event) {
        if (event == null) return;
        switch (event.keyEvent) {
            case KeyEvent.KEYCODE_DPAD_UP:
                tabView.requestFocus();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(KeyDownEvent event) {
        if (event == null) return;
        switch (event.keyEvent) {
            case KeyEvent.KEYCODE_DPAD_UP:
                if (event.position == 0)
                    tabView.requestFocus();
                break;
            case KeyEvent.KEYCODE_BACK:
                tabView.requestFocus();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (!tabView.hasFocus()) {
                    tabView.requestFocus();
                    return true;
                } else {
                    if ((System.currentTimeMillis() - mExitTime) > 2000) {
                        ToastUtils.showToast(getString(R.string.app_exit));
                        mExitTime = System.currentTimeMillis();
                    } else {
                        finish();
                        System.exit(0);
                    }
                    return true;
                }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
