package com.svv.jys.code.module.net.exception;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by lzj on 2017/6/6.
 */

public class NetException extends Exception {

    public int netCode;

    public NetException() {
    }

    public NetException(int netCode,String message) {
        super(message);
        this.netCode = netCode;
    }

    public NetException(String message) {
        super(message);
    }

    public NetException(String message, Throwable cause) {
        super(message, cause);
    }

    public NetException(Throwable cause) {
        super(cause);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public NetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
