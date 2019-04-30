package com.meixun.videosearch.retrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.meixun.videosearch.MyApplication;


/**
 * NAME：YONG_
 * Created at: 2019/2/12
 * Describe:
 */
public class NetworkUtils {

    /**
     * 判断网络是否连通
     */
    public static boolean isNetworkConnected() {
        try {
            ConnectivityManager cm = (ConnectivityManager) MyApplication.getContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            return info != null && info.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
