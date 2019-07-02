package com.longdian.fragment.weather;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.longdian.R;
import com.longdian.fragment.weather.model.PredictData;
import com.longdian.fragment.weather.model.PredictDataAll;
import com.longdian.fragment.weather.model.PredictHistoryData;
import com.longdian.fragment.weather.model.PredictStation;
import com.longdian.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class PredictDataRecyclerViewAdapter extends RecyclerView.Adapter<PredictDataRecyclerViewAdapter.ViewHolder> {

    private List<PredictStation> energyDataList = new ArrayList<>();
    private List<PredictStation> energyDataListLast;
    private List<Boolean> openList = new ArrayList<>();// 存储打开状态
    private PredictData baseData;
    private String yesQ3;

    public PredictDataRecyclerViewAdapter(Activity activity, PredictDataAll predictDataAll) {
        energyDataListLast = predictDataAll.getPredictStationListLast();
        baseData = predictDataAll.getBaseData();
        yesQ3 = predictDataAll.getYesQ3();

        PredictStation e = new PredictStation();
        e.setStationName("总负荷预测");
        PredictHistoryData t = predictDataAll.getTargetData();
        e.setK(t.getK());
        e.setK1(t.getK1());
        e.setK2(t.getK2());
        e.setTpj2(t.getTpj2());
        e.setTpj3(t.getTpj3());
        e.setTpj4(t.getTpj4());
        e.setTpj5(t.getTpj5());
        e.setQ2(t.getQ2());
        e.setQ3(t.getQ3());
        e.setQ4(t.getQ4());
        e.setQ5(t.getQ5());
        e.setQ6(t.getQ6());

        this.energyDataList.add(null);// 添加一个占位，第一个数据特殊，下面特殊处理了
        this.energyDataList.add(e);
        this.energyDataList.addAll(predictDataAll.getPredictStationList());

        openList.add(true);
        for (int i = 0; i < energyDataList.size() - 1; i++) {
            openList.add(false);
        }
        LogUtil.d("openListSize=" + openList.size() + ", dataSize=" + (energyDataList.size()) + ", predictDataAll=" + (predictDataAll.getPredictStationList().size() + 2));
    }

    private static final int FIRST = 1;
    private static final int OTHERS = 2;

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? FIRST : OTHERS;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int res = viewType == FIRST ? R.layout.listitem_predict_data1 : R.layout.listitem_predict_data;
        View view = LayoutInflater.from(parent.getContext()).inflate(res, parent, false);
        return new ViewHolder(view, viewType == FIRST);
    }

    private String getYesQ3(PredictStation s) {
        for (PredictStation predictStation : energyDataListLast) {
            if (predictStation.getStationName().equalsIgnoreCase(s.getStationName())) {
                return predictStation.getQ3() + "";
            }
        }
        return "";
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position == 0) {
            ((TextView) holder.itemView.findViewById(R.id.id_city_name)).setText(baseData.getCityName());
            ((TextView) holder.itemView.findViewById(R.id.id_hot_const)).setText(baseData.getL() + "");
            ((TextView) holder.itemView.findViewById(R.id.id_days)).setText(baseData.getN() + "（d）");
            ((TextView) holder.itemView.findViewById(R.id.id_in_tem)).setText(baseData.getTn() + "（℃）");
            ((TextView) holder.itemView.findViewById(R.id.id_out_tem)).setText(baseData.getTw1() + "（℃）");
            ((TextView) holder.itemView.findViewById(R.id.id_avg_tem)).setText(baseData.getTpj1() + "（℃）");
            ((TextView) holder.itemView.findViewById(R.id.id_hot_num)).setText(baseData.getQ() + "（W/平米）");
            ((TextView) holder.itemView.findViewById(R.id.id_area)).setText(baseData.getF() + "（万平米）");
            ((TextView) holder.itemView.findViewById(R.id.id_avg_hot)).setText(baseData.getQ1() + "（GJ）");
        } else {
            final PredictStation s = energyDataList.get(position);
            holder.stationName.setText(s.getStationName());
            holder.stationName1.setText(s.getStationName());
            holder.tpj2.setText(s.getTpj2() + "");
            holder.tpj3.setText(s.getTpj3() + "");
            holder.tpj4.setText(s.getTpj4() + "");
            holder.tpj5.setText(s.getTpj5() + "");
            holder.q2.setText(s.getQ2() + "");
            holder.q3.setText(s.getQ3() + "");
            holder.q4.setText(s.getQ4() + "");
            holder.q5.setText(s.getQ5() + "");
            holder.q6.setText(s.getQ6() + "");
            if (position == 1) {
                holder.yes.setText(yesQ3);
            } else {
                holder.yes.setText(getYesQ3(s));
            }
        }

        if (openList.get(position)) {
            onShow(holder);
        } else {
            onHide(holder);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.table.getVisibility() == View.GONE) {
                    openList.set(position, true);
                    onShow(holder);
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
    }

    private void onShow(ViewHolder holder) {
        holder.imageView.setImageResource(R.drawable.ic_triangle_down);
        holder.table.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return energyDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View table;
        ImageView imageView;
        TextView stationName;
        TextView stationName1;
        TextView tpj2;
        TextView tpj3;
        TextView tpj4;
        TextView tpj5;
        TextView q2;
        TextView q3;
        TextView q4;
        TextView q5;
        TextView q6;
        TextView yes;

        ViewHolder(View view, boolean first) {
            super(view);
            table = view.findViewById(R.id.id_table);
            imageView = (ImageView) view.findViewById(R.id.id_station_arraw);

            stationName = (TextView) view.findViewById(R.id.id_station_name);
            if (!first) {
                stationName1 = (TextView) view.findViewById(R.id.id_station_name1);
                tpj2 = (TextView) view.findViewById(R.id.id_tpj2);
                tpj3 = (TextView) view.findViewById(R.id.id_tpj3);
                tpj4 = (TextView) view.findViewById(R.id.id_tpj4);
                tpj5 = (TextView) view.findViewById(R.id.id_tpj5);
                q2 = (TextView) view.findViewById(R.id.id_q2);
                q3 = (TextView) view.findViewById(R.id.id_q3);
                q4 = (TextView) view.findViewById(R.id.id_q4);
                q5 = (TextView) view.findViewById(R.id.id_q5);
                q6 = (TextView) view.findViewById(R.id.id_q6);
                yes = (TextView) view.findViewById(R.id.id_yes);
            }
        }
    }

}
