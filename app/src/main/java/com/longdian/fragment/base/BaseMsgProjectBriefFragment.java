package com.longdian.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.longdian.DynamicAppUtil;
import com.longdian.R;

public class BaseMsgProjectBriefFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_msg_project_brief, container, false);
        TextView textView = (TextView) view.findViewById(R.id.tv_project_brief);
        textView.setText(DynamicAppUtil.getProjectBrief());
        return view;
    }
}
