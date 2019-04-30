package com.meixun.videosearch.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.meixun.videosearch.R;


/**
 * LoadingView解决了请求网络数据时ui显示的三种状态
 * 分别为加载中，加载失败，无数据
 */
public class LoadingView extends FrameLayout implements OnClickListener {

    private LinearLayout empty;
    private LinearLayout error;
    private LinearLayout loading;
    private State state;
    private OnRetryListener listener;


    public interface OnRetryListener {
        void onRetry();
    }

    public enum State {
        ing, error, done, empty
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public LoadingView(Context context) {
        super(context);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.lay_loading, this);
        empty = (LinearLayout) findViewById(R.id.empty);
        error = (LinearLayout) findViewById(R.id.error);
        loading = (LinearLayout) findViewById(R.id.loading);
        ((AnimationDrawable) ((ImageView) findViewById(R.id.img_progress)).getDrawable()).start();
        setOnClickListener(this);
        notifyDataChanged(State.ing);
    }

    public void notifyDataChanged(State state) {
        this.state = state;
        switch (state) {
            case ing:
                setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);
                empty.setVisibility(View.GONE);
                error.setVisibility(View.GONE);
                break;
            case empty:
                setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                empty.setVisibility(View.VISIBLE);
                error.setVisibility(View.GONE);
                break;
            case error:
                setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                empty.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                break;
            case done://数据成功
                setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    public void setEmptyView(View view) {
        empty.removeAllViews();
        empty.addView(view);
    }

    public void setLoadingView(View view) {
        loading.removeAllViews();
        loading.addView(view);
    }

    public void setOnRetryListener(OnRetryListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onRetry();
        if (listener != null && (state == State.error || state == State.empty))
            listener.onRetry();
    }
}
