package com.longdian.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longdian.R;
import com.longdian.activity.ContentActivity;
import com.longdian.activity.ContentActivityNoToolbar;
import com.longdian.fragment.weather.DayAverageTemperatureFragment;
import com.longdian.fragment.weather.FhyxYxjlFragment;
import com.longdian.fragment.weather.PredictDataFragment;
import com.longdian.fragment.weather.WeatherDetailFragment;
import com.longdian.fragment.weather.WeatherOverviewFragment;
import com.longdian.fragment.weather.WeatherSituationQueryFragment;

public class WeatherFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        view.findViewById(R.id.id_weather_now).setOnClickListener(this);
        view.findViewById(R.id.id_weather_detail).setOnClickListener(this);
        view.findViewById(R.id.id_weather_table).setOnClickListener(this);
        view.findViewById(R.id.id_weather_day_temperature).setOnClickListener(this);
        view.findViewById(R.id.id_heat_consume_compare).setOnClickListener(this);
        view.findViewById(R.id.id_fhyx_yxjl).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_weather_now:// 天气概览
                ContentActivityNoToolbar.start(getActivity(), WeatherOverviewFragment.class);
                break;
            case R.id.id_weather_detail:
                ContentActivity.start(getActivity(), WeatherDetailFragment.class, "天气详情");
                break;
            case R.id.id_weather_table:
                ContentActivity.start(getActivity(), WeatherSituationQueryFragment.class, "天气情况查询");
                break;
            case R.id.id_weather_day_temperature:
                ContentActivity.start(getActivity(), DayAverageTemperatureFragment.class, "日平均温度");
                break;
            case R.id.id_heat_consume_compare:
                ContentActivity.start(getActivity(), PredictDataFragment.class, "预测数据");
                break;
            case R.id.id_root_temperature:
                //ContentActivity.start(getActivity(),FhyxSnwdcjFragment.class,title:"室内温度采集");
                //break;
            case R.id.id_fhyx_yxjl:
                ContentActivity.start(getActivity(), FhyxYxjlFragment.class, "运行结论");
                break;
        }
    }
}
