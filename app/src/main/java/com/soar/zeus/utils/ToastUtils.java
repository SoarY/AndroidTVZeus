package com.soar.zeus.utils;

import android.os.Handler;
import android.widget.Toast;

import com.soar.zeus.MyApplication;


public class ToastUtils {

    private static Toast mToast = null;

    /**
     * 在子线程中弹吐司
     */
    public static void showToastOnThread(String text) {
        new Handler().post(() -> showToast(text));
    }

    /**
     * 防止重复弹出toast
     *
     * Toast引用被static所以不要传入activity的context(防止内存泄露)
     */
    public static void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

}
