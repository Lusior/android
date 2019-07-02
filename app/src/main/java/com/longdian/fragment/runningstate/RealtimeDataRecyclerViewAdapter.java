package com.longdian.fragment.runningstate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.longdian.R;
import com.longdian.fragment.dataanalysis.model.CollectExtendData;
import com.longdian.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RealtimeDataRecyclerViewAdapter extends RecyclerView.Adapter<RealtimeDataRecyclerViewAdapter.ViewHolder> {

    private List<List<CollectExtendData>> mDataList = new ArrayList<>();
    private List<Boolean> openList = new ArrayList<>();// 存储打开状态

    public RealtimeDataRecyclerViewAdapter(List<CollectExtendData> dataList) {
        Map<String, Integer> map = new TreeMap<>();// 记录每个换热站的位置
        int index = 0;
        for (int i = 0; i < dataList.size(); i++) {
            String stationName = dataList.get(i).getStationName();
            Integer mi = map.get(stationName);
            List<CollectExtendData> subList;
            if (mi == null) {
                map.put(stationName, index++);
                openList.add(false);
                subList = new ArrayList<>();
                mDataList.add(subList);
            } else {
                subList = mDataList.get(mi);
            }
            subList.add(dataList.get(i));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_realtime, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        LogUtil.e("position=" + position + holder.toString());
        final CollectExtendData s = mDataList.get(position).get(0);
        holder.stationName.setText(s.getStationName());
        holder.mContentView.setText("一网瞬热\n" + s.getQi() + "(GJ/h)");
        holder.mContentView2.setText("一网瞬流\n" + s.getFt1() + "(t/h)");
        holder.mContentView3.setText("循环泵频率\n" + s.getFc1v1() + "(Hz)");

        if (openList.get(position)) {
            onShow(holder, mDataList.get(position));
        } else {
            onHide(holder);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((LinearLayout) holder.itemView).getChildCount() == 1) {
                    openList.set(position, true);
                    onShow(holder, mDataList.get(position));
                } else {
                    openList.set(position, false);
                    onHide(holder);
                }
            }
        });
    }

    private void onHide(ViewHolder holder) {
        holder.imageView.setImageResource(R.drawable.ic_more_pressed);
        if (((LinearLayout) holder.itemView).getChildCount() > 1) {
            ((LinearLayout) holder.itemView).removeViewAt(1);
        }
    }

    private void onShow(ViewHolder holder, List<CollectExtendData> dataList) {
        LinearLayout ll = (LinearLayout) holder.itemView;
        holder.imageView.setImageResource(R.drawable.ic_triangle_down);

        if (ll.getChildCount() <= 1) {// 防止重复添加
            LogUtil.e("添加");
            View vv1 = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.fragment_realtime_2, ll, false);
            setData(dataList, vv1);
            ll.addView(vv1);
        } else {
            LogUtil.e("更新");
            View vv1 = ll.getChildAt(1);
            setData(dataList, vv1);
        }
    }

    private void setData(List<CollectExtendData> dataList, View vv1) {
        CollectExtendData s = dataList.get(0);
        ((TextView) vv1.findViewById(R.id.id_pt12)).setText("供压/回压(Mpa): " + s.getPt1() + "/" + s.getPt2() + "");
        ((TextView) vv1.findViewById(R.id.id_te12)).setText("供温/回温(℃): " + s.getTe1() + "/" + s.getTe2() + "");
        ((TextView) vv1.findViewById(R.id.id_qi)).setText("瞬时热量(GJ/h): " + s.getQi() + "");
        ((TextView) vv1.findViewById(R.id.id_qqi)).setText("累积热量(GJ/h): " + s.getQqi() + "");
        ((TextView) vv1.findViewById(R.id.id_ft1)).setText("瞬时流量(t/h): " + s.getFt1() + "");
        ((TextView) vv1.findViewById(R.id.id_ft1q)).setText("累积流量(t/h): " + s.getFt1q() + "");
        String str = "调节阀开度(%): ";
        LinearLayout ll = (LinearLayout) vv1.findViewById(R.id.id_realtime_ll);
        for (CollectExtendData d : dataList) {
            str += d.getStandName() + "：" + d.getCvi1() + "，";
            View dv = LayoutInflater.from(vv1.getContext()).inflate(R.layout.fragment_realtime_3, ll, false);
            ((TextView) dv.findViewById(R.id.id_stand_name)).setText(d.getStandName());
            ((TextView) dv.findViewById(R.id.id_r2_1)).setText(d.getPt3() + "/" + d.getPt4() + "/" + d.getPt3_pt4());
            ((TextView) dv.findViewById(R.id.id_r2_2)).setText(d.getTe3() + "/" + d.getTe4());
            ((TextView) dv.findViewById(R.id.id_r2_3)).setText(d.getFt2() + "");
            ((TextView) dv.findViewById(R.id.id_r2_4)).setText(d.getFt3() + "");
            ((TextView) dv.findViewById(R.id.id_r2_6)).setText(d.getFc1v1() + "");
            ll.addView(dv);
        }
        ((TextView) vv1.findViewById(R.id.id_cvi1)).setText(str.substring(0, str.length() - 1));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stationName;
        TextView mContentView;
        TextView mContentView2;
        TextView mContentView3;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            stationName = (TextView) view.findViewById(R.id.id_station_name);
            mContentView = (TextView) view.findViewById(R.id.content);
            mContentView2 = (TextView) view.findViewById(R.id.content2);
            mContentView3 = (TextView) view.findViewById(R.id.content3);
            imageView = (ImageView) view.findViewById(R.id.id_station_arraw);
        }
    }
}
