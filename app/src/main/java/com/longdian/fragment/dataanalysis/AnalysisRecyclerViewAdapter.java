package com.longdian.fragment.dataanalysis;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.longdian.R;
import com.longdian.util.LogUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalysisRecyclerViewAdapter extends RecyclerView.Adapter<AnalysisRecyclerViewAdapter.ViewHolder> {

    private List<List<Map<String, String>>> mDataList = new ArrayList<>();
    private List<Boolean> openList = new ArrayList<>();// 存储打开状态

    public AnalysisRecyclerViewAdapter(List<Map<String, String>> dataList) {

        Map<String, Integer> map = new TreeMap<>();// 记录每个换热站的位置
        int index = 0;
        for (Map<String, String> stringStringMap : dataList) {
            String stationName = stringStringMap.get("key");
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
            subList.add(stringStringMap);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_analysis, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        LogUtil.e("position=" + position + holder.toString());
        final Map s = mDataList.get(position).get(0);
        holder.stationName.setText(s.get("key") + "");
        holder.mContentView1.setText(s.get("key1") + "");
        holder.mContentView1.setTextColor(Color.rgb(249,190,8));
        holder.mContentView2.setText(s.get("key2") + "");
        holder.mContentView2.setTextColor(Color.rgb(150, 75, 0));
        holder.mContentView3.setText(s.get("key3") + "");
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stationName;
        TextView mContentView1;
        TextView mContentView2;
        TextView mContentView3;

        public ViewHolder(View view) {
            super(view);
            stationName = (TextView) view.findViewById(R.id.id_station_name);
            mContentView1 = (TextView) view.findViewById(R.id.content);
            mContentView2 = (TextView) view.findViewById(R.id.content2);
            mContentView3 = (TextView) view.findViewById(R.id.content3);
        }
    }
}
