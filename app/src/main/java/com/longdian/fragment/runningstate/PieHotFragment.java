package com.longdian.fragment.runningstate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.longdian.R;
import com.longdian.fragment.weather.model.PieDataAll;

public class PieHotFragment extends Fragment {

    private TextView hot_num;
    private TextView e_num;
    private TextView water_num;
    private TextView total_price;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_pie_hot, container, false);
        init(baseView);
        PieDataAll pieDataAll = PieChartFragment.pie;
        createData(pieDataAll);
        return baseView;
    }

    private void init(View view) {
        hot_num = (TextView) view.findViewById(R.id.hot_num);
        e_num = (TextView) view.findViewById(R.id.e_num);
        water_num = (TextView) view.findViewById(R.id.water_num);
        total_price = (TextView) view.findViewById(R.id.total_price);
    }

    private void createData(PieDataAll pieDataAll) {

        String qqi_a = pieDataAll.getQqi_a();//热
        String jqi_a = pieDataAll.getJqi_a();//电
        String ft3q_a = pieDataAll.getFt3q_a();//水
        String total = pieDataAll.getTotal_price();
        hot_num.setText(qqi_a + "");
        e_num.setText(jqi_a + "");
        water_num.setText(ft3q_a + "");
        total_price.setText(total + "");

    }

}
