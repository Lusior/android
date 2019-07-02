package com.longdian.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.longdian.R;
import com.longdian.fragment.base.MyStationRecyclerViewAdapter;
import com.longdian.fragment.base.model.StationList;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;

public class StationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_station_list, container, false);

        HoolaiHttpMethods.getInstance().stationList(getActivity(), new ObserverOnNextAndErrorListener<StationList>() {
            @Override
            public void onNext(StationList stationList) {
                Log.d("fw_demo", stationList.getPriceData().toString());
                Log.d("fw_demo", stationList.getStandBasicDataList().toString());
                Log.d("fw_demo", stationList.getStationDataList().toString());

                StationList stationList2 = new StationList();
                stationList2.setPriceData(stationList.getPriceData());
                stationList2.setStationDataList(stationList.getStationDataList());


//                List<StandData> standDataList = stationList.getStandDataList();
//                if (standDataList != null && standDataList.size() > 0) {
//                    for (int i = 0; i < standDataList.size(); i++) {
//                        StandData standData = standDataList.get(i);
//                        if (standData != null) {
//                            StandDataExtra standDataExtra =
//                                    DataSourceHelper.getStandDataExtra(standData.getStandName());
//                            standData.setDataFromExtra(standDataExtra);
//                        }
//                    }
//                }
//
//                for(int i= 0;i<standDataList.size();i++){
//                    Log.d("fw_demo", "测试："+standDataList.get(i).toString());
//                }


                // Set the adapter
                if (view instanceof RecyclerView) {
                    Context context = view.getContext();
                    RecyclerView recyclerView = (RecyclerView) view;
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(new MyStationRecyclerViewAdapter(stationList));
                }
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getActivity(), e.getMessage());
            }
        });
        return view;
    }

}
