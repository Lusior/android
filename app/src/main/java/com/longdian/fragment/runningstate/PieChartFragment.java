package com.longdian.fragment.runningstate;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.longdian.R;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.fragment.weather.model.PieDataAll;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.DateUtils;
import com.longdian.util.ToastUtils;

import java.util.ArrayList;

/**
 * Created by phoenix on 2017/6/3.
 */

public class PieChartFragment extends BaseDatePickerFragment {

    private FragmentManager fragmentManager;
    private PieChart mChart;
    private String jqi;// 电
    private String qqi;// 热
    private String ft3q;// 水
    public static PieDataAll pie;
    private View baseView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.activity_pie_chart, container, false);
        initDatePicker(baseView);
        textViewStart.setText(DateUtils.getYesterdayDay());
        textViewEnd.setText(DateUtils.getYesterdayDay());
        fragmentManager = getActivity().getSupportFragmentManager();

        httpApi(baseView);
        return baseView;
    }

    private void init(View baseView) {
        mChart = (PieChart) baseView.findViewById(R.id.id_pie_chart);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
//        mChart.setExtraOffsets(0, 10, 5, 0);

        mChart.setDragDecelerationFrictionCoef(0.95f);

//        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

//        mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
//        mChart.setCenterText(generateCenterSpannableText());

        mChart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(90);

        mChart.setHoleRadius(48f);
        mChart.setTransparentCircleRadius(51f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//        mChart.setOnChartValueSelectedListener(this);

        setData(4, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setEnabled(false);//禁用小方块
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setEnabled(true);
//        l.setFormSize(20);
    }

    private void setData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        String[] lables = new String[]{"水 " + ft3q, "电" + jqi, "热" + qqi};
        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        entries.add(new PieEntry(Float.valueOf(ft3q), /*mParties[i % mParties.length]*/lables[0]));
        entries.add(new PieEntry(Float.valueOf(jqi), /*mParties[i % mParties.length]*/lables[1]));
        entries.add(new PieEntry(Float.valueOf(qqi), /*mParties[i % mParties.length]*/lables[2]));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getActivity().getResources().getColor(android.R.color.holo_blue_dark));
        colors.add(getActivity().getResources().getColor(android.R.color.holo_green_light));
        colors.add(getActivity().getResources().getColor(android.R.color.holo_red_dark));
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
//        data.setValueTypeface(tf);
        mChart.setData(data);
        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.5f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.65f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    @Override
    protected void doSearch() {
        httpApi(baseView);

    }

    private void replaceFragment(Fragment fragment,int id) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(id, fragment, fragment.getClass().getName());
        transaction.commit();
    }


    public void httpApi(final View baseView){
        HoolaiHttpMethods.getInstance().pieChart(getActivity(), textViewStart.getText().toString(), textViewEnd.getText().toString(), new ObserverOnNextAndErrorListener<PieDataAll>() {
            @Override
            public void onNext(PieDataAll pieDataAll) {
                try {
                    jqi =  pieDataAll.getJqi_a();
                    qqi = pieDataAll.getQqi_a();
                    ft3q = pieDataAll.getFt3q_a();

                    pie = pieDataAll;
                    replaceFragment(new PieHotFragment(),R.id.content_fragment);
                    replaceFragment(new PieStationFragment(),R.id.content_list);
                } catch (Exception e) {
                    jqi = 1+"";
                    qqi = 1+"";
                    ft3q = 1+"";
                    e.printStackTrace();
                }
                init(baseView);
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getContext(), e.getMessage());
            }
        });
    }
}
