package com.longdian.fragment.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.longdian.R;
import com.longdian.fragment.weather.model.EnergyDataList;
import com.longdian.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MyPieStationRecyclerViewAdapter extends RecyclerView.Adapter<MyPieStationRecyclerViewAdapter.ViewHolder> {

    private List<EnergyDataList> energyDataList;
    private List<Boolean> openList = new ArrayList<>();// 存储打开状态

    public MyPieStationRecyclerViewAdapter(List<EnergyDataList> energyDataList) {
        this.energyDataList = energyDataList;
        for (int i = 0; i < energyDataList.size(); i++) {
            openList.add(false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_piestation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final EnergyDataList s = energyDataList.get(position);
        holder.stationName.setText(s.getStationName());

        if (openList.get(position)) {
            onShow(holder, s);
        } else {
            onHide(holder);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((LinearLayout) holder.itemView).getChildCount() == 1) {
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

        LinearLayout ll = (LinearLayout) holder.itemView;
        for (int i = ll.getChildCount() - 1; i >= 1; i--) {
            ll.removeViewAt(i);
        }
    }

    private void onShow(ViewHolder holder, EnergyDataList data) {
        holder.imageView.setImageResource(R.drawable.ic_triangle_down);
        LinearLayout ll = (LinearLayout) holder.itemView;
        LogUtil.e("添加");

        View vv = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.fragment_piestation_2, ll, false);
        ((TextView) vv.findViewById(R.id.id_hot_name)).setText("热耗(GJ)\n" + data.getQqi());
        ((TextView) vv.findViewById(R.id.id_e_name)).setText("电耗(kWh)\n" + data.getJqi());
        ((TextView) vv.findViewById(R.id.id_water_name)).setText("水耗(t)\n" + data.getFt3q());
        ll.addView(vv);

    }

    @Override
    public int getItemCount() {
        return energyDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stationName;
        TextView persion;
        TextView phone;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            stationName = (TextView) view.findViewById(R.id.id_station_name);
            persion = (TextView) view.findViewById(R.id.id_persion);
            phone = (TextView) view.findViewById(R.id.id_phone);
            imageView = (ImageView) view.findViewById(R.id.id_station_arraw);
        }
    }
}
