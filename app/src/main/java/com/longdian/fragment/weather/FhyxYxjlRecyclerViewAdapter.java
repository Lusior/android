package com.longdian.fragment.weather;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.longdian.R;
import com.longdian.bean.RunningConclusion;
import com.longdian.bean.TecolCurveCulData;
import com.longdian.bean.TecolCurveData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FhyxYxjlRecyclerViewAdapter extends RecyclerView.Adapter<FhyxYxjlRecyclerViewAdapter.ViewHolder> {

    private List<TecolCurveCulData> tecolCurveCulDataList = new ArrayList<>();
    private List<Boolean> openList = new ArrayList<>();// 存储打开状态
    private TecolCurveData tecolCurveData;
    private Float curTem;

    public FhyxYxjlRecyclerViewAdapter(Activity activity, RunningConclusion runningConclusion) {
        tecolCurveData = runningConclusion.getTecolCurveData();
        curTem = runningConclusion.getCurTem();
        TecolCurveCulData e = new TecolCurveCulData();
        e.setStandName("温控曲线");
        e.setTe1Cul(0f);
        e.setTe2Cul(0f);
        e.setTe3Cul(0f);
        e.setTe4Cul(0f);
        this.tecolCurveCulDataList.add(e);
        this.tecolCurveCulDataList.addAll(runningConclusion.getTecolCurveCulData());
        for (int i = 0; i < tecolCurveCulDataList.size(); i++) {
            if (i == 0) {
                openList.add(true);
            } else {
                openList.add(false);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_run_conclusion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final TecolCurveCulData s = tecolCurveCulDataList.get(position);
        holder.tvStationName.setText(s.getStandName());
        holder.tvStationName1.setText(s.getStandName());
        holder.outTem.setText("室外温度    " + curTem + "℃ ");
        holder.tv11.setText(tecolCurveData.getTe1() + "");
        holder.tv21.setText(tecolCurveData.getTe2() + "");
        holder.tv31.setText(tecolCurveData.getTe3() + "");
        holder.tv41.setText(tecolCurveData.getTe4() + "");
        if (position != 0) {
            holder.tv12.setText((tecolCurveData.getTe1() - s.getTe1Cul()) + "");
            holder.tv22.setText((tecolCurveData.getTe2() - s.getTe2Cul()) + "");
            holder.tv32.setText((tecolCurveData.getTe3() - s.getTe3Cul()) + "");
            holder.tv42.setText((tecolCurveData.getTe4() - s.getTe4Cul()) + "");
            holder.tv13.setText(s.getTe1Cul() + "");
            holder.tv23.setText(s.getTe2Cul() + "");
            holder.tv33.setText(s.getTe3Cul() + "");
            holder.tv43.setText(s.getTe4Cul() + "");
            bgColor(holder.tv13);
            bgColor(holder.tv23);
            bgColor(holder.tv33);
            bgColor(holder.tv43);
        } else {
            holder.itemView.findViewById(R.id.e_02).setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.e_03).setVisibility(View.GONE);
            holder.tv12.setVisibility(View.GONE);
            holder.tv22.setVisibility(View.GONE);
            holder.tv32.setVisibility(View.GONE);
            holder.tv42.setVisibility(View.GONE);
            holder.tv13.setVisibility(View.GONE);
            holder.tv23.setVisibility(View.GONE);
            holder.tv33.setVisibility(View.GONE);
            holder.tv43.setVisibility(View.GONE);
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

    private void bgColor(TextView textView) {
        if (Float.parseFloat(textView.getText().toString()) > 0) {
            textView.setBackgroundColor(textView.getResources().getColor(android.R.color.holo_red_dark));
        } else {
            textView.setBackgroundColor(textView.getResources().getColor(android.R.color.holo_green_dark));
        }
    }

    private void onHide(ViewHolder holder) {
        holder.imageView.setImageResource(R.drawable.ic_more_pressed);
        holder.table.setVisibility(View.GONE);
    }

    private void onShow(ViewHolder holder, TecolCurveCulData data) {
        holder.imageView.setImageResource(R.drawable.ic_triangle_down);
        holder.table.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return tecolCurveCulDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View table;
        ImageView imageView;
        TextView tvStationName;
        TextView tvStationName1;
        TextView outTem;
        TextView tv11, tv12, tv13;
        TextView tv21, tv22, tv23;
        TextView tv31, tv32, tv33;
        TextView tv41, tv42, tv43;

        ViewHolder(View view) {
            super(view);
            table = view.findViewById(R.id.id_table);
            imageView = (ImageView) view.findViewById(R.id.id_station_arraw);

            tvStationName = (TextView) view.findViewById(R.id.id_station_name);
            tvStationName1 = (TextView) view.findViewById(R.id.id_station_name1);
            outTem = (TextView) view.findViewById(R.id.id_out_tem);
            tv11 = (TextView) view.findViewById(R.id.e_11);
            tv12 = (TextView) view.findViewById(R.id.e_12);
            tv13 = (TextView) view.findViewById(R.id.e_13);
            tv21 = (TextView) view.findViewById(R.id.e_21);
            tv22 = (TextView) view.findViewById(R.id.e_22);
            tv23 = (TextView) view.findViewById(R.id.e_23);
            tv31 = (TextView) view.findViewById(R.id.e_31);
            tv32 = (TextView) view.findViewById(R.id.e_32);
            tv33 = (TextView) view.findViewById(R.id.e_33);
            tv41 = (TextView) view.findViewById(R.id.e_41);
            tv42 = (TextView) view.findViewById(R.id.e_42);
            tv43 = (TextView) view.findViewById(R.id.e_43);
        }
    }

}
