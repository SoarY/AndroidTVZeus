package com.soar.zeus.adapter;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.soar.zeus.BR;
import com.soar.zeus.R;
import com.soar.zeus.base.BaseAdapter;
import com.soar.zeus.bean.AndroidBean;
import com.soar.zeus.constant.Constant;
import com.soar.zeus.constant.ItemStyle;
import com.soar.zeus.event.eventbus.AmongScrollEvent;
import com.soar.zeus.event.eventbus.KeyDownEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * NAME：YONG_
 * Created at: 2019/1/7
 * Describe:
 */
public class CommonAdapter extends BaseAdapter<AndroidBean, ViewDataBinding> {

    private int adapterPos;
    private int totalCount;

    @Override
    protected int initLayoutId() {
        return R.layout.item_common;
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }

    public void setPosition(int adapterPos) {
        this.adapterPos = adapterPos;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
        switch (viewType) {
            case ItemStyle.Size.S_570_370:
                viewHolder.itemView.setLayoutParams(new FrameLayout.LayoutParams((int) parent.getContext().getResources().getDimension(R.dimen.dp_570), (int) parent.getContext().getResources().getDimension(R.dimen.dp_370)));
                break;
            case ItemStyle.Size.S_270_370:
                viewHolder.itemView.setLayoutParams(new FrameLayout.LayoutParams((int) parent.getContext().getResources().getDimension(R.dimen.dp_270), (int) parent.getContext().getResources().getDimension(R.dimen.dp_370)));
                break;
            case ItemStyle.Size.S_1770_250:
                viewHolder.itemView.setLayoutParams(new FrameLayout.LayoutParams((int) parent.getContext().getResources().getDimension(R.dimen.dp_1770), (int) parent.getContext().getResources().getDimension(R.dimen.dp_250)));
                break;
        }
        return viewHolder;
    }

    @Override
    protected void onBindView(RecyclerHolder<ViewDataBinding> holder, AndroidBean data, int position) {
        holder.binding.setVariable(BR.item, data);
        holder.itemView.setOnKeyListener((v, keyCode, event) -> onKey(position, v, keyCode, event));
        holder.itemView.setOnFocusChangeListener((v, hasFocus) -> {
            EventBus.getDefault().post(new AmongScrollEvent(v, hasFocus));
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
        });
    }

    private boolean onKey(int position, View v, int keyCode, KeyEvent event) {
        if (KeyEvent.ACTION_DOWN != event.getAction()) return false;
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (position != 0) break;
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.translate_shake_h);
                v.startAnimation(animation);
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (adapterPos == totalCount - 1) {
                    if (position == getItemCount() - 1) {
                        animation = AnimationUtils.loadAnimation(context, R.anim.translate_shake_h);
                        v.startAnimation(animation);
                        return true;
                    }
                } else {
                    if (position == getItemCount() - 1) {
                        EventBus.getDefault().post(new KeyDownEvent(KeyEvent.KEYCODE_DPAD_RIGHT, adapterPos));
                        return true;
                    }
                }
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                if (adapterPos == 0) {
                    EventBus.getDefault().post(new KeyDownEvent(KeyEvent.KEYCODE_DPAD_UP, adapterPos));
                    return true;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (adapterPos == totalCount - 1) {
                    animation = AnimationUtils.loadAnimation(context, R.anim.translate_shake_v);
                    v.startAnimation(animation);
                    return true;
                }
                break;
            case KeyEvent.KEYCODE_BACK:
                EventBus.getDefault().post(new KeyDownEvent(KeyEvent.KEYCODE_BACK));
                return true;
        }
        return false;
    }
}
