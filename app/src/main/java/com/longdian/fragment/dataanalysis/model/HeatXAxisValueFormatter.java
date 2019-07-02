package com.longdian.fragment.dataanalysis.model;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeatXAxisValueFormatter implements IAxisValueFormatter {

    private List<String> names = new ArrayList<>();

    public HeatXAxisValueFormatter(List<Map<String, String>> mapList) {
        for (Map<String, String> m : mapList) {
            names.add(m.get("station_name"));
        }
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int index = (int) value;
        return names.get(index).trim();
    }
}
