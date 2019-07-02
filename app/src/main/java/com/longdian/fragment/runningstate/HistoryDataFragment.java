package com.longdian.fragment.runningstate;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import com.longdian.R;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.fragment.dataanalysis.model.CollectExtendData;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.DateUtils;
import com.longdian.util.ToastUtils;

import java.util.Calendar;
import java.util.List;

public class HistoryDataFragment extends BaseDatePickerFragment {

    private RecyclerView recyclerView;
    private TextView tvTime;
    Calendar c = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.fragment_history_data, container, false);
        init(baseView);
        textViewStart.setText(DateUtils.getYmdDate2());
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        tvTime.setText(hourOfDay + ":" + minute);
        getData(textViewStart.getText().toString() + " " + tvTime.getText().toString());
        return baseView;
    }

    private void getData(String date) {
        HoolaiHttpMethods.getInstance().historyData(getActivity(), date, new ObserverOnNextAndErrorListener<List<CollectExtendData>>() {
            @Override
            public void onNext(List<CollectExtendData> dataList) {
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                recyclerView.setAdapter(new RealtimeDataRecyclerViewAdapter(dataList));
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getActivity(), e.getMessage());
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        initDatePicker(view);

        tvTime = (TextView) view.findViewById(R.id.id_date_start_time);
        String dateStr = tvTime.getText().toString();
        String[] array = dateStr.split(":");

        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        if (array.length == 2) {
            hourOfDay = Integer.parseInt(array[0]);
            minute = Integer.parseInt(array[1]);
        }
        final int finalMinute = minute;
        final int finalHourOfDay = hourOfDay;
        tvTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            tvTime.setText(hourOfDay + ":" + minute);
                        }
                    }, finalHourOfDay, finalMinute, true);
                    timePickerDialog.show();
                }
                return false;
            }
        });
    }

    @Override
    protected void doSearch() {
        getData(textViewStart.getText().toString() + " " + tvTime.getText().toString());
    }

}
