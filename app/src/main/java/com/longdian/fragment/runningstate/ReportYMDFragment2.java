package com.longdian.fragment.runningstate;

import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.longdian.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportYMDFragment2 extends Fragment implements OnChartValueSelectedListener {

    private BarChart mChart;
    private List<Map<String, String>> lists;
    private RectF mOnValueSelectedRectF = new RectF();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_report_ymd2, container, false);
        lists = null;
        switch (getTag()) {
            case "ReportDayFragment":
                lists = ReportDayFragment.list;
                break;
            case "ReportMonthFragment":
                lists = ReportMonthFragment.list;
                break;
            case "ReportYearFragment":
                lists = ReportYearFragment.list;
                break;
        }

        init(baseView);
        setData(lists);
        return baseView;
    }

    private void setData(List<Map<String, String>> lists) {
        ArrayList<BarEntry> yVals0 = new ArrayList<>();
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        ArrayList<BarEntry> yVals2 = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            float val = Float.parseFloat(lists.get(i).get("day_qqi_total"));
            float val1 = Float.parseFloat(lists.get(i).get("day_jqi_total"));
            float val2 = Float.parseFloat(lists.get(i).get("day_ft3q_total"));
            yVals0.add(new BarEntry(i, (int) val));
            yVals1.add(new BarEntry(i, (int) val1));
            yVals2.add(new BarEntry(i, (int) val2));
        }

        BarDataSet set0, set1, set2;
        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set0 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(1);
            set2 = (BarDataSet) mChart.getData().getDataSetByIndex(2);
            set0.setValues(yVals0);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set0 = new BarDataSet(yVals0, "供热量");
            set0.setColor(Color.rgb(221, 80, 67));

            set1 = new BarDataSet(yVals1, "用电量");
            set1.setColor(Color.rgb(255, 205, 68));

            set2 = new BarDataSet(yVals2, "补水量");
            set2.setColor(Color.rgb(75, 139, 244));

            BarData data = new BarData(set0, set1, set2);
            data.setValueTextSize(10f);
//            data.setBarWidth(0.9f);

            mChart.setData(data);
        }

        float groupSpace = 0.1f;
        float barSpace = 0.03f; // x3 DataSet
        float barWidth = 0.27f; // x3 DataSet
        // (0.27 + 0.03) * 3 + 0.1 = 1.00 -> interval per "group"

        // specify the width each bar should have
        mChart.getBarData().setBarWidth(barWidth);

        // restrict the x-axis range
        mChart.getXAxis().setAxisMinimum(-0.5f);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        mChart.getXAxis().setAxisMaximum(-0.01f + mChart.getBarData().getGroupWidth(groupSpace, barSpace) * lists.size());
        mChart.groupBars(-0.5f, groupSpace, barSpace);
        mChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        RectF bounds = mOnValueSelectedRectF;
        mChart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = mChart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());
        Log.i("x-index", "low: " + mChart.getLowestVisibleX() + ", high: " + mChart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() {

    }

    private void init(View baseView) {
        mChart = (BarChart) baseView.findViewById(R.id.chart1);
        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        IAxisValueFormatter xAxisFormatter = new ReportYMDXAxisValueFormatter(lists);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(-30);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(lists.size());
        xAxis.setValueFormatter(xAxisFormatter);

        YAxis leftAxis = mChart.getAxisLeft();
//        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(10, false);
//        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
//        rightAxis.setTypeface(mTfLight);
        rightAxis.setLabelCount(8, false);
//        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        MarkerView mv = new ReportYMDXYMarkerView(getActivity(), lists);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart
    }
}
