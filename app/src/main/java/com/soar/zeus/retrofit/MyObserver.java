package com.soar.zeus.retrofit;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 继承Subscriber_重写onError方法
 */
public abstract class MyObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof APIException)
            onError((APIException) e);
        else
            onError(new APIException(e, ExceptionEngine.ERROR.DEFAULT_ERROR));//其他默认code 0001
    }

    @Override
    public void onComplete() {
    }

    /**
     * 自定义错误回调
     */
    protected abstract void onError(APIException ex);
}
