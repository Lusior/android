package com.longdian.fragment.weather;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.longdian.R;
import com.longdian.fragment.runningstate.TestPanelAdapter;
import com.longdian.fragment.weather.model.WeatherData;
import com.longdian.fragment.weather.model.WeatherXAxisValueFormatter;
import com.longdian.fragment.weather.model.WeatherYAxisValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherSituationQueryFragmentChart extends Fragment {

    private LineChart mChart;
    private List<WeatherData> weatherDataList = WeatherSituationQueryFragment.weatherDataList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.fragment_weather_situation_query_chart, container, false);
        initView(baseView);
        return baseView;
    }

    private void initView(View baseView) {
        mChart = (LineChart) baseView.findViewById(R.id.chart1);
//        mChart.setOnChartValueSelectedListener(this);

        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        mChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.LTGRAY);

        // add data
        setData();

        mChart.animateX(2500);

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);
//        IAxisValueFormatter xAxisFormatter = new WeatherXAxisValueFormatter(list);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
//        xAxis.setValueFormatter(xAxisFormatter);

        WeatherYAxisValueFormatter yf = new WeatherYAxisValueFormatter();

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.RED);
        leftAxis.setAxisMaximum(40f);
        leftAxis.setAxisMinimum(-40f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setValueFormatter(yf);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setTextColor(Color.RED);
        rightAxis.setAxisMaximum(40f);
        rightAxis.setAxisMinimum(-40f);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
        rightAxis.setValueFormatter(yf);
    }

    private void setData() {
        ArrayList<Entry> yVals1 = new ArrayList<>();
        ArrayList<Entry> yVals2 = new ArrayList<>();
        ArrayList<Entry> yVals3 = new ArrayList<>();

        for (int i = 0; i < weatherDataList.size(); i++) {
            WeatherData wd = weatherDataList.get(i);
            yVals1.add(new Entry(i, wd.getTeH()));
            yVals2.add(new Entry(i, wd.getTeA()));
            yVals3.add(new Entry(i, wd.getTeL()));
        }

        LineDataSet set1, set2, set3;

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            set3.setValues(yVals3);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, "最高温度");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(Color.RED);
            set1.setCircleColor(Color.WHITE);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);
            set2 = new LineDataSet(yVals2, "平均温度");
            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
            set2.setColor(Color.YELLOW);
            set2.setCircleColor(Color.WHITE);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(65);
            set2.setFillColor(ColorTemplate.getHoloBlue());
            set2.setHighLightColor(Color.rgb(244, 117, 117));
            set2.setDrawCircleHole(false);

            set3 = new LineDataSet(yVals3, "最低温度");
            set3.setAxisDependency(YAxis.AxisDependency.LEFT);
            set3.setColor(Color.BLUE);
            set3.setCircleColor(Color.WHITE);
            set3.setLineWidth(2f);
            set3.setCircleRadius(3f);
            set3.setFillAlpha(65);
            set3.setFillColor(ColorTemplate.getHoloBlue());
            set3.setHighLightColor(Color.rgb(244, 117, 117));
            set3.setDrawCircleHole(false);

            // create a data object with the datasets
            LineData data = new LineData(set1, set2, set3);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9f);

            // set data
            mChart.setData(data);
        }
    }
}
