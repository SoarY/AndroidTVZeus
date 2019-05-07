package com.soar.zeus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soar.zeus.BR;
import com.soar.zeus.R;
import com.soar.zeus.base.BaseLazyFragment;
import com.soar.zeus.base.BaseViewModel;
import com.soar.zeus.databinding.FragmentMeBinding;
import com.soar.zeus.event.eventbus.MeKeyDownEvent;
import com.soar.zeus.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;


/**
 * NAME：YONG_
 * Created at: 2018/12/11 16
 * Describe:
 */
public class MeFragment extends BaseLazyFragment<FragmentMeBinding, BaseViewModel> {

    public static MeFragment newInstance() {
        return new MeFragment();
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_me;
    }

    @Override
    public int initVariableId() {
        return BR.vm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return view;
    }

    @Override
    protected void lazyData() {
    }

    private void initView() {
        binding.flItem1.setOnClickListener(onClickListener);
        binding.flItem2.setOnClickListener(onClickListener);
        binding.flItem3.setOnClickListener(onClickListener);
        binding.flItem4.setOnClickListener(onClickListener);

        binding.flItem1.setOnKeyListener(onKeyListener);
        binding.flItem2.setOnKeyListener(onKeyListener);
        binding.flItem3.setOnKeyListener(onKeyListener);
        binding.flItem4.setOnKeyListener(onKeyListener);
    }

    private View.OnClickListener onClickListener = v -> {
        ToastUtils.showToast(getString(R.string.app_login));
    };

    private View.OnKeyListener onKeyListener = (v, keyCode, event) -> {
        if (KeyEvent.ACTION_DOWN != event.getAction()) return false;
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                EventBus.getDefault().post(new MeKeyDownEvent(KeyEvent.KEYCODE_DPAD_UP));
                break;
        }
        return false;
    };
}
