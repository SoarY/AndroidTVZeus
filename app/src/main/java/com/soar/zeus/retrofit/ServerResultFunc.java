package com.soar.zeus.retrofit;


import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 拦截服务器返回的错误状态码，剥离ui真正需要的resultData
 */
public class ServerResultFunc<T> implements Function<BaseResultBean<T>, T> {

    @Override
    public T apply(@NonNull BaseResultBean<T> baseResult) throws Exception {
        if (baseResult.errorCode != 0)
            throw new ServerException(baseResult.errorCode, baseResult.errorMsg);
        //rxjava2  map操作符不在支持null,服务器返回数据为null时候，统一用Object接受.  也可参考https://www.jianshu.com/p/f1957c9c2240
        return baseResult.data == null ? (T) new Object() : baseResult.data;
    }
}
