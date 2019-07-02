package com.longdian.fragment.runningstate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.longdian.R;

public class ReportYMDFragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.fragment_report_ymd1, container, false);
        RecyclerView recyclerView = (RecyclerView) baseView.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        switch (getTag()){
            case "ReportDayFragment":
                recyclerView.setAdapter(new ReportRecyclerViewAdapter(ReportDayFragment.list));
                break;
            case "ReportMonthFragment":
                recyclerView.setAdapter(new ReportRecyclerViewAdapter(ReportMonthFragment.list));
                break;
            case "ReportYearFragment":
                recyclerView.setAdapter(new ReportRecyclerViewAdapter(ReportYearFragment.list));
                break;
        }
        return baseView;
    }
}
