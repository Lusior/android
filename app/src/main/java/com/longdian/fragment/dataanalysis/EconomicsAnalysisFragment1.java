package com.longdian.fragment.dataanalysis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.longdian.R;
import com.longdian.activity.MainActivity;
import com.longdian.fragment.runningstate.TestPanelAdapter;
import com.longdian.util.ToastUtils;

import java.text.NumberFormat;
import java.util.*;

public class EconomicsAnalysisFragment1 extends Fragment {

    private ScrollablePanel scrollablePanel;
    private List<Integer> vs;
    public Boolean isAsc = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View baseView = inflater.inflate(R.layout.activity_scrollable_panel_test1, container, false);
        scrollablePanel = (ScrollablePanel) baseView.findViewById(R.id.id_scrollable_panel);

        List<Map<String, String>> list = EconomicsAnalysisFragment.list;
        vs = Arrays.asList(80, 80, 100, 80, 100, 80, 80, 100);
        TestPanelAdapter testPanelAdapter = new TestPanelAdapter(readyData(list), TestPanelAdapter.width_type_dp, vs, this);
        scrollablePanel.setPanelAdapter(testPanelAdapter);

        Toast.makeText(getActivity(), "点击绿色指标进行排序", Toast.LENGTH_LONG).show();
        return baseView;
    }

    public void resetAdapter(List<List<String>> list){
        isAsc = !isAsc;
        TestPanelAdapter testPanelAdapter = new TestPanelAdapter(list, TestPanelAdapter.width_type_dp, vs, this);
        scrollablePanel.setPanelAdapter(testPanelAdapter);
    }

    private List<List<String>> readyData(List<Map<String, String>> list) {
        List<List<String>> datas = new ArrayList<>();
        datas.add(Arrays.asList("换热站名称", "总额(元)", "累计热量(GJ)", "热费(元)", "累计电量(KWh)", "电费(元)", "累计水量(T)", "水费(元)"));
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> m = list.get(i);
            List<String> row = Arrays.asList(m.get("station_name"),
                    double2Str(Double.parseDouble(m.get("price_total"))),
                    double2Str(Double.parseDouble(m.get("sum_qqi"))),
                    double2Str(Double.parseDouble(m.get("sum_qqi_price"))),
                    double2Str(Double.parseDouble(m.get("sum_jqi"))),
                    double2Str(Double.parseDouble(m.get("sum_jqi_price"))),
                    double2Str(Double.parseDouble(m.get("sum_ft3q"))),
                    double2Str(Double.parseDouble(m.get("sum_ft3q_price")))
            );
            datas.add(row);
        }
        return datas;
    }

    /**
     * Double 转string 去除科学记数法显示
     */
    private String double2Str(Double d) {
        if (d == null) {
            return "";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        return (nf.format(d));
    }
}
