package com.longdian.fragment.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.longdian.R;
import com.longdian.fragment.base.model.StandBasicData;
import com.longdian.fragment.base.model.StationData;
import com.longdian.fragment.base.model.StationList;
import com.longdian.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MyStationRecyclerViewAdapter extends RecyclerView.Adapter<MyStationRecyclerViewAdapter.ViewHolder> {

    private StationList stationList;
    private List<Boolean> openList = new ArrayList<>();// 存储打开状态

    public MyStationRecyclerViewAdapter(StationList stationList) {
        this.stationList = stationList;
        for (int i = 0; i < stationList.getStationDataList().size(); i++) {
            openList.add(false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_station, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final StationData s = stationList.getStationDataList().get(position);
        StandBasicData standBasicData = stationList.getById(s.getStationId()).get(0);
        holder.stationName.setText(s.getStationName());
        holder.persion.setText("供热面积（万㎡）\n" + standBasicData.getTotalArea());
        holder.phone.setText("循环泵流量（t/h）\n" + standBasicData.getXhbLl());
        holder.stationHeadtemp3.setText("补水泵扬程（m）\n" + standBasicData.getBsbLl());

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

    private void onShow(ViewHolder holder, StationData s) {
        holder.imageView.setImageResource(R.drawable.ic_triangle_down);
        LinearLayout ll = (LinearLayout) holder.itemView;
        LogUtil.e("添加");
        List<StandBasicData> list = stationList.getById(s.getStationId());
        for (StandBasicData data : list) {
            View vv = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.fragment_split_station, ll, false);
            ((TextView) vv.findViewById(R.id.id_stand_name)).setText(data.getStandName());
            ((TextView) vv.findViewById(R.id.id_standFh)).setText("换热负荷(MW)：" + data.getStandFh() + "");
            ((TextView) vv.findViewById(R.id.id_standArea)).setText("换热面积(㎡)：" + data.getStandArea() + "");
            ((TextView) vv.findViewById(R.id.id_standBfNum)).setText("板换数量(台)：" + data.getStandBfNum() + "");

            ((TextView) vv.findViewById(R.id.id_xhbLl)).setText("循环泵流量(t/h)：" + data.getXhbLl() + "");
            ((TextView) vv.findViewById(R.id.id_xhbYc)).setText("循环泵扬程(m)：" + data.getXhbYc() + "");
            ((TextView) vv.findViewById(R.id.id_xhbGl)).setText("循环泵功率(KW)：" + data.getXhbGl() + "");
            ((TextView) vv.findViewById(R.id.id_xhbNum)).setText("循环泵数量(台)：" + data.getXhbNum() + "");

            ((TextView) vv.findViewById(R.id.id_bsbLl)).setText("补水泵流量(t/h)：" + data.getBsbLl() + "");
            ((TextView) vv.findViewById(R.id.id_bsbYc)).setText("补水泵扬程(m)：" + data.getBsbYc() + "");
            ((TextView) vv.findViewById(R.id.id_bsbGl)).setText("补水泵功率(KW)：" + data.getBsbGl() + "");
            ((TextView) vv.findViewById(R.id.id_bsbNum)).setText("补水泵数量(台)：" + data.getBsbNum() + "");
            ll.addView(vv);
        }
    }

    @Override
    public int getItemCount() {
        return stationList.getStationDataList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stationName;
        TextView persion;
        TextView phone;
        TextView stationHeadtemp3;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            stationName = (TextView) view.findViewById(R.id.id_station_name);
            persion = (TextView) view.findViewById(R.id.id_persion);
            phone = (TextView) view.findViewById(R.id.id_phone);
            stationHeadtemp3 = (TextView) view.findViewById(R.id.id_station_temp3);
            imageView = (ImageView) view.findViewById(R.id.id_station_arraw);
        }
    }
}
