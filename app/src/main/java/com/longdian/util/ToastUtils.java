//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.longdian.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static void showToast(Context context, int resID) {
        Toast.makeText(context, resID, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

//    private static Toast toast = null;
//    private static boolean isShowToast = true;
//
//    public static void showToast(Context context, int resID) {
//        if (isShowToast) {
//            if (toast == null) {
//                toast = Toast.makeText(context, resID, Toast.LENGTH_SHORT);
//            } else {
//                toast.setText(resID);
//            }
//            toast.show();
//        }
//    }
//
//    public static void showToast(Context context, String text) {
//        if (isShowToast) {
//            if (toast == null) {
//                toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
//            } else {
//                toast.setText(text);
//            }
//            toast.show();
//        }
//    }
}
