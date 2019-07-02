package com.longdian.fragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.longdian.LoginActivity;
import com.longdian.R;
import com.longdian.bean.GlobalInfo;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;

public class BaseMsgUpdatePasswordFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View baseView = inflater.inflate(R.layout.fragment_base_msg_update_pwd, container, false);
        baseView.findViewById(R.id.id_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = GlobalInfo.oprInfo.getLoginId();
                String oldPwd = ((EditText) baseView.findViewById(R.id.id_old_pwd)).getText().toString();
                final String pwd = ((EditText) baseView.findViewById(R.id.id_new_pwd1)).getText().toString();
                String pwd2 = ((EditText) baseView.findViewById(R.id.id_new_pwd2)).getText().toString();
                if (!pwd.equals(pwd2)) {
                    ToastUtils.showToast(getActivity(), "两次输入密码不一致");
                    return;
                }
                HoolaiHttpMethods.getInstance().changePwd(getActivity(), account, oldPwd, pwd, new ObserverOnNextAndErrorListener<String>() {
                    @Override
                    public void onNext(String s) {
                        ToastUtils.showToast(getActivity(), "修改密码成功");
                        LoginActivity.updatePwd(getActivity(), pwd);
                    }

                    @Override
                    public void onError(HoolaiException e) {

                    }
                });
            }
        });
        return baseView;
    }
}
