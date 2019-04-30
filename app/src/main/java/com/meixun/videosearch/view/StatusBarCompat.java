package com.meixun.videosearch.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * NAME：YONG_
 * Created at: 2017/11/8 20
 * Describe: android5.0  api21以上.
 */
public class StatusBarCompat {

    /**
     * APP style状态栏默认颜色colorPrimaryDark
     * 设置其他状态栏颜色.
     */
    public static void setStatus(Activity activity,int color) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return;
        activity.getWindow().setStatusBarColor(color);
    }

    /**
     * 设置状态栏透明,无View进行占位(布局会被顶上一个状态栏,若全背景图也可忽略此高度）
     */
    public static void setTransparentStatus(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return;
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * 设置状态栏透明,也可设置其他颜色（不建议用此方法设置其他颜色,一般用于Color.TRANSPARENT操作，此方法传入ViewGroup 进行通知栏高度占位）
     */
    public static void setTransparentStatus(Activity activity,ViewGroup contentView,int color) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return;
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);

        View statusBarView = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        //改变颜色时避免重复添加statusBarView
        if (statusBarView != null && statusBarView.getMeasuredHeight() == getStatusBarHeight(activity))
        {
            statusBarView.setBackgroundColor(color);
            return;
        }
        statusBarView = new View(activity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(activity));
        statusBarView.setBackgroundColor(color);
        contentView.addView(statusBarView, lp);
    }

    public static int getStatusBarHeight(Context context)
    {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
        {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
