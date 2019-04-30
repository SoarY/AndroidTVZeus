package com.meixun.videosearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.meixun.videosearch.BR;
import com.meixun.videosearch.R;
import com.meixun.videosearch.base.BaseLazyFragment;
import com.meixun.videosearch.databinding.FragmentHomeBinding;
import com.meixun.videosearch.event.eventbus.AmongScrollEvent;
import com.meixun.videosearch.event.eventbus.KeyDownEvent;
import com.meixun.videosearch.utils.ToastUtils;
import com.meixun.videosearch.view.LinearItemDecoration;
import com.meixun.videosearch.vm.HomeViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * NAME：YONG_
 * Created at: 2018/12/11 16
 * Describe:
 */
public class HomeFragment extends BaseLazyFragment<FragmentHomeBinding, HomeViewModel> {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_home;
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

    private void initView() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);

        binding.recyclerView.addItemDecoration(new LinearItemDecoration(0, (int) context.getResources().getDimension(R.dimen.dp_30)));
        viewModel.adapter.setOnChildItemClickListener(position -> ToastUtils.showToast(position+""));
    }

    @Override
    protected void lazyData() {
        viewModel.getSampleData();
        viewModel.getHomeData();
    }

    /**
     * recyclerView中部显示
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AmongScrollEvent event) {
        if (!isVisible || event == null || !event.hasFocus || event.v == null) return;
        int[] vPos = new int[2];
        int[] rPos = new int[2];
        event.v.getLocationInWindow(vPos);
        binding.recyclerView.getLocationInWindow(rPos);
        int dy = vPos[1] - rPos[1] + (event.v.getHeight() - binding.recyclerView.getHeight()) / 2;
        binding.recyclerView.smoothScrollBy(0, dy, new DecelerateInterpolator());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(KeyDownEvent event) {
        if (!isVisible || event == null) return;
        switch (event.keyEvent) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                View view = binding.recyclerView.getLayoutManager().findViewByPosition(event.position + 1);
                isRecyclerScroll(view);
                if (view != null)
                    view.post(view::requestFocus);
                break;
            case KeyEvent.KEYCODE_BACK:
                binding.recyclerView.scrollToPosition(0);
                break;
        }
    }

    private void isRecyclerScroll(View view) {
        if (view instanceof ViewGroup)
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View childAt = ((ViewGroup) view).getChildAt(i);
                if (childAt instanceof RecyclerView)
                    ((RecyclerView) childAt).scrollToPosition(0);
            }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
