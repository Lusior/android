package com.longdian.service;

/**
 * Created by Administrator on 2017/3/15.
 */

public class HoolaiException extends Exception {

    public static int NETWORK_EXCEPTION = -1;
    private int code;

    public HoolaiException(int code, Throwable e) {
        super(e.getMessage(), e);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
