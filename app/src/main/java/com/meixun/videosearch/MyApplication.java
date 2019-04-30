package com.meixun.videosearch;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

public class MyApplication extends Application {

    private static Context context;
    private static MyApplication instance;

    public void onCreate() {
        super.onCreate();
        init();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    private void init() {
        instance = this;
        context = getApplicationContext();

        if (BuildConfig.DEBUG)
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.openLog();     // 打印日志
        ARouter.init(instance); // 尽可能早，推荐在Application中初始化
    }
}
