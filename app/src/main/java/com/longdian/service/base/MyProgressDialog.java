package com.longdian.service.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;
import com.longdian.R;


public class MyProgressDialog extends Dialog {

    public MyProgressDialog(@NonNull Context context) {
        super(context);
//        super(context, R.style.progress_dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_my_progress_dialog);
        setCancelable(true);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
}
