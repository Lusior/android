package com.longdian.fragment.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.longdian.R;
import com.longdian.fragment.weather.model.WeatherData;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherDetailFragment extends Fragment {

    private View baseView;
    private List<String> titles = Arrays.asList("今天", "明天", "后天", "大后天");
    private List<Fragment> fragments = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        getData();
        return baseView;
    }

    private void initView(List<WeatherData> weatherDataList) {
        TabLayout tabLayout = (TabLayout) baseView.findViewById(R.id.id_find_tabLayout);
        int days = weatherDataList.size();
        if (days > titles.size()) {
            days = titles.size();
        }
        for (int i = 0; i < days; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
            fragments.add(WeatherDetailTabFragment.getInstance(weatherDataList.get(i)));
        }
        ViewPager viewPager = (ViewPager) baseView.findViewById(R.id.id_find_viewPager);
        viewPager.setAdapter(new TabLayoutFragmentPagerAdapter(getActivity().getSupportFragmentManager(), fragments, titles));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void getData() {
        HoolaiHttpMethods.getInstance().weatherDetail(getActivity(), new ObserverOnNextAndErrorListener<List<WeatherData>>() {
            @Override
            public void onNext(List<WeatherData> weatherDataList) {
                initView(weatherDataList);
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getActivity(), e.getMessage());
            }
        });
    }

}
