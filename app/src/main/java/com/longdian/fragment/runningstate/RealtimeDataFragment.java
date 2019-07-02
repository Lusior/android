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
import com.longdian.fragment.dataanalysis.model.CollectExtendData;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;

import java.util.List;

public class RealtimeDataFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_realtime_list, container, false);

        HoolaiHttpMethods.getInstance().realtimeData(getActivity(), new ObserverOnNextAndErrorListener<List<CollectExtendData>>() {
            @Override
            public void onNext(List<CollectExtendData> dataList) {
                // Set the adapter
                if (view instanceof RecyclerView) {
                    Context context = view.getContext();
                    RecyclerView recyclerView = (RecyclerView) view;
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(new RealtimeDataRecyclerViewAdapter(dataList));
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
