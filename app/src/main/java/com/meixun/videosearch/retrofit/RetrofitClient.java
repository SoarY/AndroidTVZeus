package com.meixun.videosearch.retrofit;


import android.os.Build;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.net.ssl.SSLSocketFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * YONG_
 */
public class RetrofitClient {

    private static volatile RetrofitClient mInstance;
    private HashMap<String, API> apis = new HashMap<>();

    private RetrofitClient() {
        getApi();
    }

    public static RetrofitClient getInstance() {
        if (mInstance == null)
            synchronized (RetrofitClient.class) {
                if (mInstance == null)
                    mInstance = new RetrofitClient();
            }
        return mInstance;
    }

    public API getApi() {
        return getApi(APIMain.API_PLAY_ANDROID);
    }

    public API getApi(String urlMain) {
        if (!apis.containsKey(urlMain)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(urlMain)
                    .client(getOkHttpClient())
                    .build();
            API api = retrofit.create(API.class);
            apis.put(urlMain, api);
        }
        return apis.get(urlMain);
    }

    /**
     * 设置Android4.4及以下的系统支持HTTPS相关协议（v1.1和v1.2）
     */
    public static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            SSLSocketFactory factory = new SSLSocketFactoryCompat();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH && Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT_WATCH)
                builder.sslSocketFactory(factory);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return builder.build();
    }

    /**
     * 订阅
     */
    public <T> void toSubscribe(LifecycleProvider<ActivityEvent> lifecycle, Observable<T> o, Observer<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(s);
    }
}
