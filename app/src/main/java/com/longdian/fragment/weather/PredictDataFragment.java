package com.longdian.fragment.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longdian.R;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.fragment.weather.model.PredictDataAll;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.DateUtils;
import com.longdian.util.ToastUtils;

/**
 * Created by phoenix on 2017/6/3.
 */

public class PredictDataFragment extends BaseDatePickerFragment {

    private View baseView;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.activity_predict_data, container, false);
        initDatePicker(baseView);
        textViewStart.setText(DateUtils.getYesterdayDay());

        recyclerView = (RecyclerView) baseView.findViewById(R.id.id_recycler_view);
        doSearch();
        return baseView;
    }

    @Override
    protected void doSearch() {
        getData(textViewStart.getText().toString());
    }

    private void getData(String date) {
        HoolaiHttpMethods.getInstance().predictData3(getActivity(), date, new ObserverOnNextAndErrorListener<PredictDataAll>() {
            @Override
            public void onNext(PredictDataAll predictDataAll) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new PredictDataRecyclerViewAdapter(getActivity(), predictDataAll));
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getContext(), e.getMessage());
            }
        });
    }
}
