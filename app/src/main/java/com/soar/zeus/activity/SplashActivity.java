package com.soar.zeus.activity;

import android.os.Bundle;
import android.os.Handler;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.soar.zeus.R;
import com.soar.zeus.base.BaseActivity;
import com.soar.zeus.base.BaseViewModel;
import com.soar.zeus.constant.ConstantsImageUrl;
import com.soar.zeus.constant.RouteConstants;
import com.soar.zeus.databinding.ActivitySplashBinding;
import com.soar.zeus.utils.CommonUtils;
import com.soar.zeus.view.StatusBarCompat;

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
                .downloadOnly(CommonUtils.dip2px(getResources().getDimension(R.dimen.dp_1920)), CommonUtils.dip2px(getResources().getDimension(R.dimen.dp_1080)));
        RequestOptions options = new RequestOptions()
                .error(R.mipmap.ic_splash);
        Glide.with(context)
                .load(ConstantsImageUrl.SPLASH_1920_1080_1)
                .apply(options)
                .into(binding.ivSplash);

        new Handler().postDelayed(this::finish, DELAY_MILLIS);
    }

}
