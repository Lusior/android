package com.longdian.fragment.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longdian.R;
import com.longdian.bean.RunningConclusion;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;

public class FhyxYxjlFragment extends Fragment {

    private View baseView;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.activity_run_conclusion, container, false);
        recyclerView = (RecyclerView) baseView.findViewById(R.id.id_recycler_view);
        httpApi();
        return baseView;
    }

    public void httpApi() {
        HoolaiHttpMethods.getInstance().runningConclusion(getActivity(), new ObserverOnNextAndErrorListener<RunningConclusion>() {
            @Override
            public void onNext(RunningConclusion runningConclusion) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new FhyxYxjlRecyclerViewAdapter(getActivity(), runningConclusion));
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getContext(), e.getMessage());
            }
        });
    }
}
