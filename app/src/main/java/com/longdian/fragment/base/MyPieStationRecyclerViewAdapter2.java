package com.longdian.fragment.base;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.longdian.R;
import com.longdian.fragment.weather.model.EnergyDataList;
import com.longdian.fragment.weather.model.PieDataAll;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyPieStationRecyclerViewAdapter2 extends RecyclerView.Adapter<MyPieStationRecyclerViewAdapter2.ViewHolder> {

    private List<EnergyDataList> energyDataList = new ArrayList<>();
    private List<Boolean> openList = new ArrayList<>();// 存储打开状态
    private ArrayList<Integer> colors = new ArrayList<>();
    private Double qqi_unit_price;
    private Double jqi_unit_price;
    private Double ft3q_unit_price;

    public MyPieStationRecyclerViewAdapter2(Activity activity, PieDataAll pieDataAll) {
        initUnitPrice(pieDataAll);
        EnergyDataList e = new EnergyDataList();
        e.setStationName("运行总成本");
        e.setFt3q(pieDataAll.getFt3q_a());
        e.setJqi(pieDataAll.getJqi_a());
        e.setQqi(pieDataAll.getQqi_a());
        e.setMoney(pieDataAll.getTotal_price());
        this.energyDataList.add(e);
        this.energyDataList.addAll(appendSuffixAndGetTotal(pieDataAll.getEnergyDataList()));
        for (int i = 0; i < energyDataList.size(); i++) {
            if (i == 0) {
                openList.add(true);
            } else {
                openList.add(false);
            }
        }
        colors.add(activity.getResources().getColor(android.R.color.holo_blue_dark));
        colors.add(activity.getResources().getColor(android.R.color.holo_green_light));
        colors.add(activity.getResources().getColor(android.R.color.holo_red_dark));
    }

    private void initUnitPrice(PieDataAll pieDataAll){
        qqi_unit_price = Double.parseDouble(pieDataAll.getQqi_price());
        jqi_unit_price = Double.parseDouble(pieDataAll.getJqi_price());
        ft3q_unit_price = Double.parseDouble(pieDataAll.getFt3q_price());
    }

    //为小区名添加字符串为“运营成本”的后缀，并计算总成本
    private Collection<? extends EnergyDataList> appendSuffixAndGetTotal(List<EnergyDataList> energyDataList) {
        for (EnergyDataList dataList : energyDataList) {
            dataList.setStationName(dataList.getStationName()+"运行成本");
            dataList.setFt3q(formatNum(Double.parseDouble(dataList.getFt3q()) * ft3q_unit_price));
            dataList.setJqi(formatNum(Double.parseDouble(dataList.getJqi()) * jqi_unit_price));
            dataList.setQqi(formatNum(Double.parseDouble(dataList.getQqi()) * qqi_unit_price));
            dataList.setMoney(formatNum(Double.parseDouble(dataList.getFt3q())
                    + Double.parseDouble(dataList.getJqi())
                    + Double.parseDouble(dataList.getQqi()))
            );
        }
        return energyDataList;
    }

    //取两位小数
    private String formatNum(Double d) {
        DecimalFormat fnum = new DecimalFormat("##0.00");
        return fnum.format(d);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_pie_station, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final EnergyDataList s = energyDataList.get(position);
        holder.stationName.setText(s.getStationName());
        holder.stationName1.setText(s.getStationName());

        if (s.getMoney() != null) {
            holder.money.setVisibility(View.VISIBLE);
        }
        if (openList.get(position)) {
            onShow(holder, s);
        } else {
            onHide(holder);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.table.getVisibility() == View.GONE) {
                    openList.set(position, true);
                    onShow(holder, s);
                } else {
                    openList.set(position, false);
                    onHide(holder);
                }
            }
        });
    }

    private void onHide(ViewHolder holder) {
        holder.imageView.setImageResource(R.drawable.ic_more_pressed);
        holder.table.setVisibility(View.GONE);
        holder.pieChart.setVisibility(View.GONE);
    }

    private void onShow(ViewHolder holder, EnergyDataList data) {
        holder.imageView.setImageResource(R.drawable.ic_triangle_down);
        holder.table.setVisibility(View.VISIBLE);
        holder.pieChart.setVisibility(View.VISIBLE);

        holder.e_num.setText(data.getJqi());
        holder.hot_num.setText(data.getQqi());
        holder.water_num.setText(data.getFt3q());
        holder.total_price.setText(data.getMoney());
        init(holder.pieChart, data);
    }

    @Override
    public int getItemCount() {
        return energyDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView stationName;
        TextView stationName1;
        TextView hot_num;
        TextView e_num;
        TextView water_num;
        TextView total_price;
        PieChart pieChart;
        View table;
        View money;
        ImageView imageView;

        ViewHolder(View view) {
            super(view);
            stationName = (TextView) view.findViewById(R.id.id_station_name);
            stationName1 = (TextView) view.findViewById(R.id.id_station_name1);
            hot_num = (TextView) view.findViewById(R.id.hot_num);
            e_num = (TextView) view.findViewById(R.id.e_num);
            water_num = (TextView) view.findViewById(R.id.water_num);
            total_price = (TextView) view.findViewById(R.id.total_price);
            pieChart = (PieChart) view.findViewById(R.id.id_pie_chart);
            table = view.findViewById(R.id.id_table);
            imageView = (ImageView) view.findViewById(R.id.id_station_arraw);
            money = view.findViewById(R.id.id_money);
        }
    }


    private void init(PieChart mChart, EnergyDataList data) {
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setDragDecelerationFrictionCoef(0.95f);

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

        setData(mChart, 4, 100, data);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setEnabled(false);
    }


    private void setData(PieChart mChart, int count, float range, EnergyDataList d) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        String jqi = d.getJqi();// 电
        String qqi = d.getQqi();// 热
        String ft3q = d.getFt3q();// 水

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
}
