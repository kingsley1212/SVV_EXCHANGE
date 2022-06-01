package com.svv.jys.code.module.net.exception;

/**
 * Created by lzj on 2017/6/14.
 *
 * 没登录异常
 */
public class NeedLoginException extends Exception {

    public NeedLoginException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public NeedLoginException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        // TODO Auto-generated constructor stub
    }

    public NeedLoginException(String detailMessage) {
        super(detailMessage);
        // TODO Auto-generated constructor stub
    }

    public NeedLoginException(Throwable throwable) {
        super(throwable);
        // TODO Auto-generated constructor stub
    }



}
