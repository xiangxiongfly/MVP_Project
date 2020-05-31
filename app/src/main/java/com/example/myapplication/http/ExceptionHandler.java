package com.example.myapplication.http;

import android.net.ParseException;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;

import retrofit2.HttpException;

public class ExceptionHandler {
    private static final String TAG = "ExceptionHandler";
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static String handleException(Throwable e) {
        String errorMsg;
        int errorCode = -1;

        if (e instanceof ApiException) {
            //处理用户错误
            ApiException apiException = (ApiException) e;
            errorMsg = apiException.getErrorMsg();
            errorCode = apiException.getErrorCode();
            handleServerException(errorCode, errorMsg);
        }
        //处理其他异常
        else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    errorMsg = "网络错误";
                    break;
            }
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            errorMsg = "解析错误";
        } else if (e instanceof ConnectException) {
            errorMsg = "网络连接失败,请稍后重试";
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            e.printStackTrace();
            errorMsg = "证书验证失败";
        } else if (e instanceof ConnectTimeoutException) {
            errorMsg = "网络连接超时";
        } else if (e instanceof java.net.SocketTimeoutException) {
            errorMsg = "连接超时";
        } else {
            errorMsg = "网络连接异常,请稍后重试";
        }
        return errorMsg;
    }

    /**
     * 处理指定的异常信息
     */
    private static void handleServerException(int errorCode, String errorMsg) {
        switch (errorCode) {
            default:
                break;
        }
    }
}
