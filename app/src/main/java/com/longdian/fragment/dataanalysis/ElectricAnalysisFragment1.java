package com.longdian.fragment.dataanalysis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.longdian.R;
import com.longdian.util.NumberUtil;
import com.longdian.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectricAnalysisFragment1 extends Fragment implements AnalysisFragment {

    private RecyclerView recyclerView;
    private Boolean isAsc = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_data_anaysis_water1, container, false);
        recyclerView = (RecyclerView) baseView.findViewById(R.id.list);

        List<Map<String, String>> lists = ElectricAnalysisFragment.sortList;

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new AnalysisRecyclerViewAdapter(readyData(lists)));
        return baseView;
    }

    private List<Map<String, String>> readyData(List<Map<String, String>> lists) {
        List<Map<String, String>> datas = new ArrayList<>();
        for (Map<String, String> s : lists) {
            Map<String, String> row = new HashMap<>();
            row.put("key", s.get("station_name"));
            row.put("key1", s.get("jqi_total") + "kWh");
            row.put("key2", NumberUtil.formatNum(s.get("yxdhstr"), 4) + "kWh/m²");
            row.put("key3", s.get("hours") + "h");
            datas.add(row);
        }
        return datas;
    }

    @Override
    public void resetAdapter(List<Map<String, String>> list) {
        isAsc = !isAsc;
        recyclerView.setAdapter(new AnalysisRecyclerViewAdapter(list));
    }
}