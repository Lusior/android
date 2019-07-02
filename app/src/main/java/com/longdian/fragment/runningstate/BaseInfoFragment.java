package com.longdian.fragment.runningstate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.longdian.DynamicAppUtil;
import com.longdian.R;

public class BaseInfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_base_info, container, false);
        TextView textView = (TextView) view.findViewById(R.id.tv_company_overview);
        textView.setText(DynamicAppUtil.getCompanyOverview());
        return view;
    }
}
