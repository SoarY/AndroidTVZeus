package com.soar.zeus.retrofit;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;


/**
 * 自定义异常处理
 */
public class ExceptionEngine {

    public static APIException handleException(Throwable e) {
        APIException ex;
        if (!NetworkUtils.isNetworkConnected()) {
            ex = new APIException(e, ERROR.ERROR_NET);
            ex.setDisplayMessage("当前网络不可用,请检查你的网络设置");
            return ex;
        } else if (e instanceof HttpException) {//HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new APIException(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case CODE.UNAUTHORIZED:
                    ex.setDisplayMessage("操作未授权");
                    break;
                case CODE.FORBIDDEN:
                    ex.setDisplayMessage("请求被拒绝");
                    break;
                case CODE.NOT_FOUND:
                    ex.setDisplayMessage("资源不存在");
                    break;
                case CODE.REQUEST_TIMEOUT:
                    ex.setDisplayMessage("服务器执行超时");
                    break;
                case CODE.INTERNAL_SERVER_ERROR:
                    ex.setDisplayMessage("服务器内部错误");
                    break;
                case CODE.SERVICE_UNAVAILABLE:
                    ex.setDisplayMessage("服务器不可用");
                    break;
                default:
                    ex.setDisplayMessage("网络错误");
                    break;
            }
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex = new APIException(e, ERROR.NETWORD_ERROR);
            ex.setDisplayMessage("连接失败");
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new APIException(e, ERROR.PARSE_ERROR);
            ex.setDisplayMessage("解析错误");
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new APIException(e, ERROR.NETWORD_ERROR);
            ex.setDisplayMessage("连接失败");//均视为网络错误
            return ex;
        } else if (e instanceof SocketTimeoutException) {
            ex = new APIException(e, ERROR.TIMEOUT_ERROR);
            ex.setDisplayMessage("连接超时,请重试");
            return ex;
        } else if (e instanceof ServerException) {//服务器返回的错误
            ServerException resultException = (ServerException) e;
            ex = new APIException(resultException, resultException.getCode());
            ex.setDisplayMessage(resultException.getMsg());
            return ex;
        } else {
            ex = new APIException(e, ERROR.UNKNOWN);
            ex.setDisplayMessage("服务器异常,请稍后再试试哦");
            return ex;//未知错误
        }
    }

    /**
     * 对应HTTP的状态码
     */
    public class CODE {
        private static final int UNAUTHORIZED = 401;
        private static final int FORBIDDEN = 403;
        private static final int NOT_FOUND = 404;
        private static final int REQUEST_TIMEOUT = 408;
        private static final int INTERNAL_SERVER_ERROR = 500;
        private static final int SERVICE_UNAVAILABLE = 503;
    }

    /**
     * 约定异常
     * <p>
     * 具体规则需要与服务端或者领导商讨定义
     */
    public class ERROR {
        /**
         * 默认DEFAULT_ERROR
         */
        public static final int DEFAULT_ERROR = 0001;
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;

        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 1006;

        /**
         * 无网络
         */
        public static final int ERROR_NET = 1007;
    }
}
