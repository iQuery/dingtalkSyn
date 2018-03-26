package com.imp.httpclient;

/**
 * Created by i_it on 2017/6/27.
 */
public class OApiException extends Exception {

    public OApiException(int errCode, String errMsg) {
        super("error code: " + errCode + ", error message: " + errMsg);
    }
}
