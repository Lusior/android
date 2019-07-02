package com.longdian.fragment.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.longdian.R;
import com.longdian.activity.TopBarBaseActivity;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.fragment.weather.model.WeatherData;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.DateUtils;
import com.longdian.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class DayAverageTemperatureFragment extends BaseDatePickerFragment {

    public static final int MAX_DAYS = 10;

    private FragmentManager fragmentManager;
    private boolean isCurrentChart = false;
    private int chartRes = R.drawable.nav_btn_3copy2;
    private int tableRes = R.drawable.nav_btn_4copy2;
    static List<WeatherData> weatherDataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.fragment_weather_situation_query_main, container, false);
        initDatePicker(baseView);

        final TopBarBaseActivity activity = (TopBarBaseActivity) getActivity();
        activity.setTopRightButton("bbb", chartRes, new TopBarBaseActivity.OnClickListener() {
            @Override
            public void onClick() {
                if (isCurrentChart) {
                    replaceFragment(new DayAverageTemperatureFragmentTable());
                } else {
                    replaceFragment(new DayAverageTemperatureFragmentChart());
                }
                isCurrentChart = !isCurrentChart;
                ((TopBarBaseActivity) getActivity()).updateMenuItemIcon(isCurrentChart ? tableRes : chartRes);
            }
        });
        fragmentManager = activity.getSupportFragmentManager();

        textViewStart.setText(DateUtils.getYesterdayDay());
        textViewEnd.setText(DateUtils.getYesterdayDay());
        doSearch();
        return baseView;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_fragment, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    private void getData(String start, String end) {
        HoolaiHttpMethods.getInstance().weatherList(getActivity(), start, end, new ObserverOnNextAndErrorListener<List<WeatherData>>() {
            @Override
            public void onNext(List<WeatherData> weatherDataList) {
                DayAverageTemperatureFragment.weatherDataList = weatherDataList;
                if (!isCurrentChart) {
                    replaceFragment(new DayAverageTemperatureFragmentTable());
                } else {
                    replaceFragment(new DayAverageTemperatureFragmentChart());
                }
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getActivity(), e.getMessage());
            }
        });
    }

    @Override
    protected void doSearch() {
        String start = textViewStart.getText().toString();
        String end = textViewEnd.getText().toString();
        long l = DateUtils.toDate(end).getTime() - DateUtils.toDate(start).getTime();
        int days = (int) (l / (24 * 3600 * 1000));
        if (days < 0 || days >= MAX_DAYS) {
            ToastUtils.showToast(getContext(), "日期跨度最多" + MAX_DAYS + "天!");
            return;
        }
        getData(start, end);
    }

}
