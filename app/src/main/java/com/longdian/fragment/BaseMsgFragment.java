package com.longdian.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.longdian.DynamicAppUtil;
import com.longdian.R;
import com.longdian.activity.ContentActivity;
import com.longdian.fragment.base.*;
import com.longdian.fragment.runningstate.BaseInfoFragment;

public class BaseMsgFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base_msg, container, false);
        TextView textView = (TextView) view.findViewById(R.id.tv_company_name);
        textView.setText(DynamicAppUtil.getCompanyOverviewTitle());

        view.findViewById(R.id.id_brief_project).setOnClickListener(this);
        view.findViewById(R.id.id_overview_hot_company).setOnClickListener(this);
        view.findViewById(R.id.id_instructions_software).setOnClickListener(this);
        view.findViewById(R.id.id_concepts_and_symbols).setOnClickListener(this);
        view.findViewById(R.id.id_related_drawing_information).setOnClickListener(this);
        view.findViewById(R.id.id_about_system).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_brief_project:
                ContentActivity.start(getActivity(), BaseMsgProjectBriefFragment.class, "项目简介");
                break;
            case R.id.id_overview_hot_company:
                ContentActivity.start(getActivity(), BaseInfoFragment.class, DynamicAppUtil.getCompanyOverviewTitle());
                break;
            case R.id.id_instructions_software:
                ContentActivity.start(getActivity(), BaseMsgSoftWareInstructionsFragment.class, "软件使用说明");
                break;
            case R.id.id_concepts_and_symbols:
                ContentActivity.start(getActivity(), BaseMsgConceptsAndSymbolsFragment.class, "术语及符号");
                break;
            case R.id.id_about_system:
                ContentActivity.start(getActivity(), BaseMsgAboutSystemFragment.class, "关于系统");
                break;
            case R.id.id_related_drawing_information:
                ContentActivity.start(getActivity(), BaseMsgRelatedDrawingInfoFragment.class, "相关图纸资料");
                break;
//            case R.id.id_exit:
//                new ExitDialog().show(getActivity().getFragmentManager(), ExitDialog.class.getName());
//                break;
        }
    }
}
