package com.longdian.fragment.runningstate;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.longdian.R;
import com.longdian.fragment.base.MyPieStationRecyclerViewAdapter;
import com.longdian.fragment.weather.model.PieDataAll;

public class PieStationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_piestation_list, container, false);
        PieDataAll pieDataAll = PieChartFragment.pie;
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyPieStationRecyclerViewAdapter(pieDataAll.getEnergyDataList()));
            recyclerView.setNestedScrollingEnabled(false);
        }

        return view;
    }
}
