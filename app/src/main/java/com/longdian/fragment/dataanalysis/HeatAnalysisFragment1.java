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
import com.longdian.util.ToastUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeatAnalysisFragment1 extends Fragment implements AnalysisFragment {

    private RecyclerView recyclerView;
    private Boolean isAsc = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_data_anaysis_water1, container, false);
        recyclerView = (RecyclerView) baseView.findViewById(R.id.list);

        List<Map<String, String>> lists = HeatAnalysisFragment.sortList;

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new AnalysisRecyclerViewAdapter(readyData(lists)));
        return baseView;
    }

    public void resetAdapter(List<Map<String, String>> list) {
        isAsc = !isAsc;
        recyclerView.setAdapter(new AnalysisRecyclerViewAdapter(list));
    }

    private List<Map<String, String>> readyData(List<Map<String, String>> lists) {
        List<Map<String, String>> datas = new ArrayList<>();
        for (Map<String, String> s : lists) {
            Map<String, String> row = new HashMap<>();
            row.put("key", s.get("station_name"));
            row.put("key1", s.get("qqi_total") + "GJ");
            row.put("key2", s.get("yxdhstr") + "GJ/m²");
            row.put("key3", s.get("hours") + "h");
            datas.add(row);
        }
        return datas;
    }

    //取2位小数，服务器返回GJ，需要转换成W，1GJ=3.6MW，1MW=10的6次方W
    private String formatNum(String s) {
        DecimalFormat fnum = new DecimalFormat("##0.00");
        return fnum.format(Double.parseDouble(s) * 3.6 * 1000 * 1000);
    }
}
