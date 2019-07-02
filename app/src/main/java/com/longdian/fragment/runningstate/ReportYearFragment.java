package com.longdian.fragment.runningstate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.*;
import android.widget.TextView;
import com.longdian.R;
import com.longdian.activity.TopBarBaseActivity;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;
import com.longdian.view.mydatepicker.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class ReportYearFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private FragmentManager fragmentManager;
    private boolean isChart = false;
    private int chartRes = R.drawable.nav_btn_3copy2;
    private int tableRes = R.drawable.nav_btn_4copy2;
    public static List<Map<String, String>> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.fragment_report_year, container, false);
        initDatePicker(baseView);

        final TopBarBaseActivity activity = (TopBarBaseActivity) getActivity();
        activity.setTopRightButton("bbb", chartRes, new TopBarBaseActivity.OnClickListener() {
            @Override
            public void onClick() {
                if (isChart) {
                    replaceFragment(new ReportYMDFragment1());
                } else {
                    replaceFragment(new ReportYMDFragment2());
                }
                isChart = !isChart;
                ((TopBarBaseActivity) getActivity()).updateMenuItemIcon(isChart ? tableRes : chartRes);
            }
        });
        fragmentManager = activity.getSupportFragmentManager();

        Calendar c = Calendar.getInstance();
        String year = c.get(Calendar.YEAR) + "";
        textViewStart.setText(year);
        getData(year);
        return baseView;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_fragment, fragment, this.getClass().getSimpleName());
        transaction.commit();
    }

    private void getData(String date) {
        HoolaiHttpMethods.getInstance().reportYear(getActivity(), date, textViewName.getText().toString(), new ObserverOnNextAndErrorListener<List<Map<String, String>>>() {
            @Override
            public void onNext(List<Map<String, String>> dataList) {
                list = dataList;
                if (!isChart) {
                    replaceFragment(new ReportYMDFragment1());
                } else {
                    replaceFragment(new ReportYMDFragment2());
                }
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getActivity(), e.getMessage());
            }
        });
    }

    // ------ **** ----------
    protected TextView textViewStart;
    protected TextView textViewName;

    protected void initDatePicker(View baseView) {
        textViewStart = (TextView) baseView.findViewById(R.id.id_date_start);
        textViewStart.setOnTouchListener(this);
        textViewName = (TextView) baseView.findViewById(R.id.id_station_name);
        baseView.findViewById(R.id.id_search).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_search:
                getData(textViewStart.getText().toString());
                break;
        }
    }

    private void showDatePickerDialog(final TextView textView) {
        String dateStr = textView.getText().toString();

        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        if (dateStr.length() == 4) {
            year = Integer.parseInt(dateStr);
        }

        DatePicker picker = new DatePicker(getActivity(), DatePicker.YEAR);
        picker.setGravity(Gravity.CENTER);
        picker.setWidth((int) (picker.getScreenWidthPixels() * 0.6));
        picker.setRangeStart(2016, 1, 1);
        picker.setRangeEnd(2020, 12, 31);
        picker.setSelectedItem(year, 1);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                textView.setText(year);
            }
        });
        picker.show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.id_date_start:
                    showDatePickerDialog(textViewStart);
                    break;
            }
        }
        return false;
    }
}
