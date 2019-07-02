package com.longdian.fragment.weather.model;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class WeatherYAxisValueFormatter implements IAxisValueFormatter {

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return value + "℃";
    }
}
