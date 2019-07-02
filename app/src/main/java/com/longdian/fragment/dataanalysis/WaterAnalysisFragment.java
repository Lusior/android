package com.longdian.fragment.dataanalysis;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.RestrictionEntry;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.longdian.R;
import com.longdian.activity.TopBarBaseActivity;
import com.longdian.fragment.BaseDatePickerFragment;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.DateUtils;

import java.util.*;

import static com.longdian.util.NumberUtil.extractFloat;

public class WaterAnalysisFragment extends BaseDatePickerFragment implements DialogInterface.OnMultiChoiceClickListener {

    private FragmentManager fragmentManager;
    private boolean isChart = false;
    private int chartRes = R.drawable.nav_btn_3copy2;
    private int tableRes = R.drawable.nav_btn_4copy2;
    //list用来存储图表展示的数据，不会被排序，但在点击选择热站后会被更新
    public static List<Map<String, String>> list = new ArrayList<>();
    //sortList用来存储排序后的数据，但只会在列表布局中起作用
    static List<Map<String, String>> sortList = new ArrayList<>();
    //用来存储从服务端获取的数据，任何时候都不会被修改
    private List<Map<String, String>> list2 = new ArrayList<>();
    private boolean[] selected;
    private String[] items;
    private AlertDialog alertDialog;
    private boolean isAsc;
    private View itemTitle;
    private ImageView contentSortable2;
    private ImageView contentSortable;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_data_anaysis_water, container, false);
        initDatePicker(baseView);
        initView(baseView);

        final TopBarBaseActivity activity = (TopBarBaseActivity) getActivity();
        activity.setTopRightButton("bbb", chartRes, new TopBarBaseActivity.OnClickListener() {
            @Override
            public void onClick() {
                if (isChart) {
                    replaceFragment(new WaterAnalysisFragment1());
                } else {
                    replaceFragment(new WaterAnalysisFragment2());
                }
                isChart = !isChart;
                ((TopBarBaseActivity) getActivity()).updateMenuItemIcon(isChart ? tableRes : chartRes);
                if (isChart) {
                    itemTitle.setVisibility(View.GONE);
                } else {
                    itemTitle.setVisibility(View.VISIBLE);
                }
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
        transaction.replace(R.id.content_fragment, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.id_search) {
            doSearch();
        } else if (v.getId() == R.id.id_choose_station) {
            showMultiChoiceDialog();
        } else if (v.equals(alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE))) {
            for (int i = 0; i < selected.length; i++) {
                selected[i] = true;
            }
            alertDialog.dismiss();
            alertDialog.show();
        }
    }

    private void showMultiChoiceDialog() {
        alertDialog = new AlertDialog
                .Builder(getActivity())
                .setTitle("选择热站或机组")
                .setMultiChoiceItems(items, selected, this)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.clear();
                        sortList.clear();
                        list.addAll(list2);
                        sortList.addAll(list2);
                        for (int i = 0; i < selected.length; i++) {
                            if (!selected[i]) {
                                list.remove(i - (selected.length - list.size()));
                                sortList.remove(i - (selected.length - sortList.size()));
                            }
                        }
                        if (!isChart) {
                            replaceFragment(new WaterAnalysisFragment1());
                        } else {
                            replaceFragment(new WaterAnalysisFragment2());
                        }
                        resetSortable();
                    }
                })
                .setNegativeButton("全选", null)
                .setCancelable(true)
                .create();
        alertDialog.show();
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(this);
    }

    private void resetSortable() {
        contentSortable.setImageResource(R.drawable.ic_dark_sortable);
        contentSortable2.setImageResource(R.drawable.ic_dark_sortable);
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        selected[which] = isChecked;
    }

    @Override
    protected void doSearch() {
        String start = textViewStart.getText().toString();
        String end = textViewEnd.getText().toString();

        HoolaiHttpMethods.getInstance().analysisWater(getActivity(), start, end, "", new ObserverOnNextAndErrorListener<List<Map<String, String>>>() {
            @Override
            public void onNext(List<Map<String, String>> maps) {
                list.clear();
                list2.clear();
                sortList.clear();
                list.addAll(maps);
                list2.addAll(maps);
                sortList.addAll(maps);
                items = new String[list.size()];
                selected = new boolean[list.size()];
                for (int i = 0; i < items.length; i++) {
                    items[i] = list.get(i).get("stand_name");
                }
                if (!isChart) {
                    replaceFragment(new WaterAnalysisFragment1());
                } else {
                    replaceFragment(new WaterAnalysisFragment2());
                }
                resetSortable();
            }

            @Override
            public void onError(HoolaiException e) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void initView(View baseView) {
        baseView.findViewById(R.id.id_choose_station).setOnClickListener(this);
        ((TextView) baseView.findViewById(R.id.id_station_name)).setText("机组名称");
        TextView content = (TextView) baseView.findViewById(R.id.content);
        TextView content2 = (TextView) baseView.findViewById(R.id.content2);
        content.setText("补水量");
        content2.setText("运行单耗");
        ((TextView) baseView.findViewById(R.id.content3)).setText("统计时间");
        itemTitle = baseView.findViewById(R.id.item_title);
        contentSortable = (ImageView) baseView.findViewById(R.id.content_sortable);
        contentSortable2 = (ImageView) baseView.findViewById(R.id.content2_sortable);

        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortDatalist("ft3q_total");
                if (isAsc) {
                    contentSortable.setImageResource(R.drawable.ic_dark_sorted_asc);
                } else {
                    contentSortable.setImageResource(R.drawable.ic_dark_sorted_desc);
                }
                contentSortable2.setImageResource(R.drawable.ic_dark_sortable);
            }
        });

        content2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortDatalist("yxdhstr");
                if (isAsc) {
                    contentSortable2.setImageResource(R.drawable.ic_dark_sorted_asc);
                } else {
                    contentSortable2.setImageResource(R.drawable.ic_dark_sorted_desc);
                }
                contentSortable.setImageResource(R.drawable.ic_dark_sortable);
            }
        });
    }

    private void sortDatalist(final String key) {
        Collections.sort(sortList, new Comparator<Map<String, String>>() {

            private String str2;
            private String str1;

            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                if (isAsc) {
                    str1 = o1.get(key);
                    str2 = o2.get(key);
                } else {
                    str2 = o1.get(key);
                    str1 = o2.get(key);
                }
                return Float.compare(
                        Float.parseFloat(extractFloat(str2)),
                        Float.parseFloat(extractFloat(str1))
                );
            }
        });
        replaceFragment(new WaterAnalysisFragment1());
        isAsc = !isAsc;
    }
}
