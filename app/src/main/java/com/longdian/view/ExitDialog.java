package com.longdian.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;
import com.longdian.LoginActivity;
import com.longdian.R;
import com.longdian.util.SystemUtils;
import com.longdian.util.ToastUtils;

/**
 * Created by phoenix on 2017/6/11.
 */

public class ExitDialog extends DialogFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View baseView = inflater.inflate(R.layout.jh_exit_dialog, container, false);
        baseView.findViewById(R.id.id_exit).setOnClickListener(this);
        baseView.findViewById(R.id.id_cancel).setOnClickListener(this);
        return baseView;
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
        paramLayoutParams.width = ((int) (0.8D * paramDisplay.getWidth()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_exit:
                LoginActivity.clearAutoLogin(getActivity());
                ToastUtils.showToast(getActivity(), "exit");
                getActivity().finish();
                getDialog().cancel();
                break;
            case R.id.id_cancel:
                getDialog().cancel();
                break;
        }
    }
}
