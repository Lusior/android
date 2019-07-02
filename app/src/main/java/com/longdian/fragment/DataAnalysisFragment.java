package com.longdian.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.longdian.R;
import com.longdian.activity.ContentActivity;
import com.longdian.fragment.dataanalysis.*;

public class DataAnalysisFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_anaysis, container, false);

        view.findViewById(R.id.id_water_consum).setOnClickListener(this);
        view.findViewById(R.id.id_heat_consum).setOnClickListener(this);
        view.findViewById(R.id.id_electric_consum).setOnClickListener(this);
        view.findViewById(R.id.id_economics_analysis).setOnClickListener(this);
        view.findViewById(R.id.id_basic_year_heating_data).setOnClickListener(this);
        view.findViewById(R.id.id_control_indicators).setOnClickListener(this);
        view.findViewById(R.id.id_indicators_system).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_water_consum:
                ContentActivity.start(getActivity(), WaterAnalysisFragment.class, "水耗分析");
                break;
            case R.id.id_heat_consum:
                ContentActivity.start(getActivity(), HeatAnalysisFragment.class, "热耗分析");
                break;
            case R.id.id_electric_consum:
                ContentActivity.start(getActivity(), ElectricAnalysisFragment.class, "电耗分析");
                break;
            case R.id.id_economics_analysis:
                ContentActivity.start(getActivity(), EconomicsAnalysisFragment.class, "经济分析");
                break;
            case R.id.id_basic_year_heating_data:
                ContentActivity.start(getActivity(), BasicYearHeatingDataFragment.class, "基础数据");
                break;
            case R.id.id_control_indicators:
                ContentActivity.start(getActivity(), ControlIndicatorsFragment.class, "管控指标");
                break;
            case R.id.id_indicators_system:
                ContentActivity.start(getActivity(), IndicatorsSystemFragment.class, "指标体系");
                break;
        }
    }
}
