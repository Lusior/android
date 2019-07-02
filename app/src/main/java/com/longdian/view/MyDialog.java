package com.longdian.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;
import com.longdian.R;
import com.longdian.util.SystemUtils;

public class MyDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.getDialog().setCanceledOnTouchOutside(false);
        return inflater.inflate(R.layout.jh_msg_dialog, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setWindow();
    }

    public void setWindow() {
        Display localDisplay = getActivity().getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams localLayoutParams = getDialog().getWindow().getAttributes();
        setDialogSize(localDisplay, localLayoutParams);
        getDialog().getWindow().setAttributes(localLayoutParams);
    }

    protected void setDialogSize(Display paramDisplay, WindowManager.LayoutParams paramLayoutParams) {
        paramLayoutParams.gravity = 17;
        if (SystemUtils.isLandscape(getActivity())) {
            paramLayoutParams.width = ((int) (0.6D * paramDisplay.getWidth()));
            return;
        }
        paramLayoutParams.width = ((int) (0.9D * paramDisplay.getWidth()));
    }
}
