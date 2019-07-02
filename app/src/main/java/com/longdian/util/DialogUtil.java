package com.longdian.util;

import android.app.Activity;
import com.longdian.view.MyDialog;

public class DialogUtil {

    public static void showDialog(Activity activity, String msg) {
        MyDialog myDialog = new MyDialog();
        myDialog.show(activity.getFragmentManager(), myDialog.getClass().getName());
    }

    public static void showDialog(Activity activity, int resId, Object... formatArgs) {
        showDialog(activity, activity.getString(resId, formatArgs));
    }
}
