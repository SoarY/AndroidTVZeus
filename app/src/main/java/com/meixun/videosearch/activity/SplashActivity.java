package com.meixun.videosearch.activity;

import android.os.Bundle;
import android.os.Handler;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.meixun.videosearch.R;
import com.meixun.videosearch.base.BaseActivity;
import com.meixun.videosearch.base.BaseViewModel;
import com.meixun.videosearch.constant.ConstantsImageUrl;
import com.meixun.videosearch.constant.RouteConstants;
import com.meixun.videosearch.databinding.ActivitySplashBinding;
import com.meixun.videosearch.view.StatusBarCompat;

import java.io.File;

@Route(path = RouteConstants.Rule.SPLASH_ACTIVITY)
public class SplashActivity extends BaseActivity<ActivitySplashBinding, BaseViewModel> {

    private final static long DELAY_MILLIS = 3000;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        StatusBarCompat.setTransparentStatus(this);

        FutureTarget<File> future = Glide.with(context)
                .load(ConstantsImageUrl.SPLASH_1920_1080_1)
                .downloadOnly(1920, 1080);
        RequestOptions options = new RequestOptions()
                .error(R.mipmap.ic_splash);
        Glide.with(context)
                .load(ConstantsImageUrl.SPLASH_1920_1080_1)
                .apply(options)
                .into(binding.ivSplash);

        new Handler().postDelayed(this::finish, DELAY_MILLIS);
    }

}
