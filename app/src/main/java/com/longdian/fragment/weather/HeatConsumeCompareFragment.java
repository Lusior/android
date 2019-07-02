package com.longdian.fragment.weather;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.longdian.R;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.DateUtils;
import com.longdian.util.ToastUtils;

import java.util.List;
import java.util.Map;

public class HeatConsumeCompareFragment extends BaseDatePickerFragment {

    private TextView cityName;
    private TextView l;
    private TextView tw1;
    private TextView tpj1;
    private TextView n;
    private TextView f;
    private TextView k;
    private TextView tn;
    private TextView q1;
    private TextView tpj2;
    private TextView tpj3;
    private TextView tpj4;
    private TextView tpj5;
    private TextView q3;
    private TextView q4;
    private TextView q5;
    private TextView q6;
    private TextView k1;
    private TextView k2;
    private TextView q2;
    private TextView yesQ3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_heat_consume_compare, container, false);

        initDatePicker(baseView);
        textViewStart.setText(DateUtils.getDateTime("yyyy-MM-dd"));
        initTextView(baseView);
        doSearch();
        return baseView;
    }

    private void getData(String date) {
        HoolaiHttpMethods.getInstance().predictData(getActivity(), date, new ObserverOnNextAndErrorListener<Map<String, String>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onNext(Map<String, String> dataMap) {
            cityName.setText("               城市名称"+" : "+dataMap.get("cityName"));
                   l.setText("               供热常数"+" : "+dataMap.get("L"));
                 tw1.setText("               供暖室外计算温度"+" : "+dataMap.get("Tw1") + "（℃）");
                tpj1.setText("               供暖期平均温度"+" : "+dataMap.get("Tpj1") + "（℃）");
                   n.setText("               供暖天数"+" : "+dataMap.get("N") + "（d）");
                   f.setText("               供热面积"+" : "+dataMap.get("F") + "（万平米）");
                   k.setText("               设计热指标"+" : "+dataMap.get("k") + "（W/平米）");
                  tn.setText("               室内计算温度"+" : "+dataMap.get("Tn") + "（℃）");
                  q1.setText("               全年日平均耗热量"+" : "+dataMap.get("Q1") + "（GJ）");
                tpj2.setText("               今天平均温度"+" : "+dataMap.get("Tpj2") + "（℃）");
                tpj3.setText("               明天平均温度"+" : "+dataMap.get("Tpj3") + "（℃）");
                tpj4.setText("               后天平均温度"+" : "+dataMap.get("Tpj4") + "（℃）");
                tpj5.setText("               大后天平均温度"+" : "+dataMap.get("Tpj5") + "（℃）");
                  q3.setText("               今天预测耗热量"+" : "+dataMap.get("Q3") + "（GJ）");
                  q4.setText("               明天预测耗热量"+" : "+dataMap.get("Q4") + "（GJ）");
                  q5.setText("               后天预测耗热量"+" : "+dataMap.get("Q5") + "（GJ）");
                  q6.setText("               大后天预测耗热量"+" : "+dataMap.get("Q6") + "（GJ）");
                  k1.setText("               修正系数"+" : "+dataMap.get("k1"));
                  k2.setText("               计算热指标"+" : "+dataMap.get("k2") + "（W/平米）");
                  q2.setText("               昨天实际耗热量"+" : "+dataMap.get("Q2") + "（GJ）");
               yesQ3.setText("               昨天预测耗热量"+" : "+dataMap.get("yesQ3") + "（GJ）");
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(getActivity(), e.getMessage());
            }
        });
    }

    private void initTextView(View view) {
        cityName = (TextView) view.findViewById(R.id.id_city_name);
        l = (TextView) view.findViewById(R.id.id_L);
        tw1 = (TextView) view.findViewById(R.id.id_Tw1);
        tpj1 = (TextView) view.findViewById(R.id.id_Tpj1);
        n = (TextView) view.findViewById(R.id.id_N);
        f = (TextView) view.findViewById(R.id.id_F);
        k = (TextView) view.findViewById(R.id.id_k);
        tn = (TextView) view.findViewById(R.id.id_Tn);
        q1 = (TextView) view.findViewById(R.id.id_Q1);
        tpj2 = (TextView) view.findViewById(R.id.id_Tpj2);
        tpj3 = (TextView) view.findViewById(R.id.id_Tpj3);
        tpj4 = (TextView) view.findViewById(R.id.id_Tpj4);
        tpj5 = (TextView) view.findViewById(R.id.id_Tpj5);
        q3 = (TextView) view.findViewById(R.id.id_Q3);
        q4 = (TextView) view.findViewById(R.id.id_Q4);
        q5 = (TextView) view.findViewById(R.id.id_Q5);
        q6 = (TextView) view.findViewById(R.id.id_Q6);
        k1 = (TextView) view.findViewById(R.id.id_k1);
        k2 = (TextView) view.findViewById(R.id.id_k2);
        q2 = (TextView) view.findViewById(R.id.id_Q2);
        yesQ3 = (TextView) view.findViewById(R.id.id_yesQ3);
    }

    @Override
    protected void doSearch() {
        getData(textViewStart.getText().toString());
    }
}
