package com.meixun.videosearch.retrofit;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * NAME：YONG_
 * Created at: 2017/8/7 19
 * Describe: 异常分发
 */
public class HttpResultFunc<T> implements Function<Throwable, Observable<T>> {

    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}