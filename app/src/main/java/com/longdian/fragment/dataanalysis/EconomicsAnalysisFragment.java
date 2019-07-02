package com.longdian.fragment.dataanalysis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.longdian.R;
import com.longdian.activity.TopBarBaseActivity;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.fragment.runningstate.TestPanelAdapter;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.DateUtils;
import com.longdian.util.ToastUtils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EconomicsAnalysisFragment extends BaseDatePickerFragment {

    private FragmentManager fragmentManager;
    private boolean isChart = false;
    private int chartRes = R.drawable.nav_btn_3copy2;
    private int tableRes = R.drawable.nav_btn_4copy2;
    public static List<Map<String, String>> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.activity_scrollable_panel_test, container, false);
        initDatePicker(baseView);

        final TopBarBaseActivity activity = (TopBarBaseActivity) getActivity();
        activity.setTopRightButton("bbb", chartRes, new TopBarBaseActivity.OnClickListener() {
            @Override
            public void onClick() {
                if (isChart) {
                    replaceFragment(new EconomicsAnalysisFragment1());
                } else {
                    replaceFragment(new EconomicsAnalysisFragment2());
                }
                isChart = !isChart;
                ((TopBarBaseActivity) getActivity()).updateMenuItemIcon(isChart ? tableRes : chartRes);
            }
        });
        fragmentManager = activity.getSupportFragmentManager();

        textViewStart.setText(DateUtils.getDaysAgo(1));
        textViewEnd.setText(DateUtils.getDaysAgo(1));
        doSearch();
        return baseView;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_fragment, fragment, this.getClass().getSimpleName());
        transaction.commit();
    }

    @Override
    protected void doSearch() {
        String start = textViewStart.getText().toString();
        String end = textViewEnd.getText().toString();
        HoolaiHttpMethods.getInstance().reportEconomics(getActivity(), start, end, "", new ObserverOnNextAndErrorListener<List<Map<String, String>>>() {
            @Override
            public void onNext(List<Map<String, String>> dataList) {
                list = dataList;
                if (!isChart) {
                    replaceFragment(new EconomicsAnalysisFragment1());
                } else {
                    replaceFragment(new EconomicsAnalysisFragment2());
                }
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getActivity(), e.getMessage());
            }
        });
    }
}
