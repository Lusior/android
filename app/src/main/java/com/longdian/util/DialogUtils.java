package com.longdian.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtils {

    public static void closeDialog(Dialog paramDialog) {
        if ((paramDialog != null) && (paramDialog.isShowing())) {
            paramDialog.dismiss();
        }
    }

    public static Dialog createLoadingDialog(Context paramContext) {
        ProgressDialog localProgressDialog = new ProgressDialog(paramContext);
        localProgressDialog.setProgressStyle(0);
        localProgressDialog.setCancelable(true);
        localProgressDialog.setCanceledOnTouchOutside(false);
        localProgressDialog.show();
        return localProgressDialog;
    }
}