package com.longdian.fragment.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.longdian.R;
import com.longdian.fragment.runningstate.TestPanelAdapter;
import com.longdian.fragment.weather.model.WeatherData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayAverageTemperatureFragmentTable extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.fragment_weather_situation_query, container, false);
        ScrollablePanel scrollablePanel = (ScrollablePanel) baseView.findViewById(R.id.id_scrollable_panel);

        List<WeatherData> weatherDataList = DayAverageTemperatureFragment.weatherDataList;

        List<Integer> vs = Arrays.asList(40, 80, 80);
        TestPanelAdapter testPanelAdapter = new TestPanelAdapter(createData(weatherDataList), TestPanelAdapter.width_type_dp, vs, this);
        scrollablePanel.setPanelAdapter(testPanelAdapter);
        return baseView;
    }

    private List<List<String>> createData(List<WeatherData> list) {
        List<List<String>> datas = new ArrayList<>();
        datas.add(Arrays.asList("序号", "日期", "平均温度"));
        for (int i = 0; i < list.size(); i++) {
            WeatherData s = list.get(i);
            List<String> row = Arrays.asList(i + 1 + "", s.getDay(), s.getTeA() + "");
            datas.add(row);
        }
        return datas;
    }
}
