package com.longdian.fragment.runningstate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.longdian.R;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.fragment.base.MyPieStationRecyclerViewAdapter2;
import com.longdian.fragment.weather.model.PieDataAll;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.DateUtils;
import com.longdian.util.ToastUtils;

/**
 * Created by phoenix on 2017/6/3.
 */

public class PieChartFragment2 extends BaseDatePickerFragment {

    private View baseView;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.activity_pie_chart2, container, false);
        initDatePicker(baseView);
        textViewStart.setText(DateUtils.getYesterdayDay());
        textViewEnd.setText(DateUtils.getYesterdayDay());

        recyclerView = (RecyclerView) baseView.findViewById(R.id.id_recycler_view);
        httpApi();
        return baseView;
    }

    @Override
    protected void doSearch() {
        httpApi();
    }

    public void httpApi() {
        HoolaiHttpMethods.getInstance().pieChart(getActivity(), textViewStart.getText().toString(), textViewEnd.getText().toString(), new ObserverOnNextAndErrorListener<PieDataAll>() {
            @Override
            public void onNext(PieDataAll pieDataAll) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new MyPieStationRecyclerViewAdapter2(getActivity(), pieDataAll));
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getContext(), e.getMessage());
            }
        });
    }
}
