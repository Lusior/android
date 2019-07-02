package com.longdian.fragment.runningstate;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.longdian.R;
import com.longdian.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReportRecyclerViewAdapter extends RecyclerView.Adapter<ReportRecyclerViewAdapter.ViewHolder> {

    private List<List<Map<String, String>>> mDataList = new ArrayList<>();
    private List<Boolean> openList = new ArrayList<>();// 存储打开状态

    public ReportRecyclerViewAdapter(List<Map<String, String>> dataList) {

        Map<String, Integer> map = new TreeMap<>();// 记录每个换热站的位置
        int index = 0;
        for (int i = 0; i < dataList.size(); i++) {
            String stationName = dataList.get(i).get("station_name");
            Integer mi = map.get(stationName);
            List<Map<String, String>> subList;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        LogUtil.e("position=" + position + holder.toString());
        final Map s = mDataList.get(position).get(0);
        holder.stationName.setText(s.get("station_name")+"");
        holder.mContentView.setText(s.get("day_qqi_total") + "");
        holder.mContentView2.setText(s.get("day_jqi_total") + "");
        holder.mContentView3.setText(s.get("day_ft3q_total") + "");
        System.out.println(s.get("station_name")+"");
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

        public ViewHolder(View view) {
            super(view);
            stationName = (TextView) view.findViewById(R.id.id_station_name);
            mContentView = (TextView) view.findViewById(R.id.content);
            mContentView2 = (TextView) view.findViewById(R.id.content2);
            mContentView3 = (TextView) view.findViewById(R.id.content3);
        }
    }
}
