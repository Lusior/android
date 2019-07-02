package com.longdian.util;

import android.util.Log;

public class LogUtil {

    private static String TAG = "energy_tag";

    public static void d(String msg) {
        msg += " [ThreadName=" + Thread.currentThread().getName() + "]";
        Log.d(TAG, msg);
    }

    /**
     * 为了方便之后动态日志level，所以增加此方法
     *
     * @param msg
     */
    public static void print(String msg) {
        msg += " [ThreadName=" + Thread.currentThread().getName() + "]";
        Log.d(TAG, msg);
    }

/*    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void w(String msg) {
        Log.w(TAG, msg);
    }*/

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

    public static void e(Throwable tr) {
        Log.e(TAG, tr.getMessage(), tr);
    }

    public static void e(String msg, Throwable tr) {
        Log.e(TAG, msg, tr);
    }

    /**
     * ***************** 下面是带TAG方法
     *********************/

/*    public static void d(String tag, String msg) {
        Log.d(tag, msg + "-" + Thread.currentThread().getName());
    }

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        Log.e(tag, msg, tr);
    }*/
}
