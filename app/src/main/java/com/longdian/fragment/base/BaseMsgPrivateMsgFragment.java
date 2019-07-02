package com.longdian.fragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.longdian.R;
import com.longdian.bean.GlobalInfo;

public class BaseMsgPrivateMsgFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base_msg_private_msg, container, false);
        ((TextView) view.findViewById(R.id.id_name)).setText(getString(R.string.tv_name, GlobalInfo.oprInfo.getOprName()));
        ((TextView) view.findViewById(R.id.id_department)).setText(getString(R.string.tv_depName, GlobalInfo.oprInfo.getDepName()));
        ((TextView) view.findViewById(R.id.id_email)).setText(getString(R.string.tv_email, GlobalInfo.oprInfo.getEmail()));
        ((TextView) view.findViewById(R.id.id_phone)).setText(getString(R.string.tv_telephone, GlobalInfo.oprInfo.getPhone()));
        return view;
    }
}
