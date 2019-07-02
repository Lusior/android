package com.longdian.fragment.runningstate;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kelin.scrollablepanel.library.PanelAdapter;
import com.longdian.R;
import com.longdian.fragment.dataanalysis.EconomicsAnalysisFragment1;
import com.longdian.util.DensityUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class TestPanelAdapter extends PanelAdapter implements View.OnClickListener {

    private static final int TITLE_TYPE = 4;
    private static final int first_column_TYPE = 0;
    private static final int first_row_TYPE = 1;
    private static final int Data_TYPE = 2;

    public static final int width_type_def = 0;
    public static final int width_type_avg = 1;
    public static final int width_type_dp = 2;
    public static final int width_type_weight = 3;
    private int width_type = width_type_def;
    private List<Integer> widthValuesList;
    private int sumWidthValues;
    private ViewGroup parent;

    private List<List<String>> data;
    private Fragment parentFragment;
    private Boolean isAsc;

    public TestPanelAdapter(List<List<String>> data, int widthType, List<Integer> widthValuesList, Fragment fragment) {
        this.data = data;
        parentFragment = fragment;
        width_type = widthType;
        if (widthType == width_type_dp || widthType == width_type_weight) {
            if (widthValuesList == null || widthValuesList.size() != data.get(0).size()) {
                throw new RuntimeException("参数传入错误");
            }
        }
        this.widthValuesList = widthValuesList;
        if (widthType == width_type_weight) {
            for (int i : widthValuesList) {

                sumWidthValues += i;
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return data.get(0).size();
    }

    @Override
    public int getItemViewType(int row, int column) {
        if (column == 0 && row == 0) {
            return TITLE_TYPE;
        }
        if (column == 0) {
            return first_column_TYPE;
        }
        if (row == 0) {
            return first_row_TYPE;
        }
        return Data_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        changeWidth(holder.itemView.getContext(), lp, column);
        holder.itemView.setLayoutParams(lp);

        if (row % 2 == 0) {
            holder.itemView.setBackgroundResource(R.drawable.bg_item2);
        }
        Log.d("TestPanelAdapter", "onBindViewHolder: " + data.size() + ", " + row + ", " + column);
        String title = data.get(row).get(column);
        TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
        titleViewHolder.titleTextView.setText(title);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View view;
        switch (viewType) {
            case Data_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_order_info, parent, false);
                break;
            case first_column_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_room_info, parent, false);
                break;
            case first_row_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_date_info, parent, false);
                view.findViewById(R.id.title).setOnClickListener(this);
                ((TextView) view.findViewById(R.id.title)).setTextColor(Color.rgb(188,214,78));
                break;
            case TITLE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_title, parent, false);
                break;
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_order_info, parent, false);
                break;
        }
        return new TitleViewHolder(view);
    }

    private void changeWidth(Context context, ViewGroup.LayoutParams lp, int columnIndex) {
        switch (width_type) {
            case width_type_avg:
                lp.width = getImageItemWidth(context, getColumnCount());
                break;
            case width_type_dp:
                lp.width = DensityUtil.dp2px(context, widthValuesList.get(columnIndex));
                break;
            case width_type_weight:
                int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
                lp.width = screenWidth * widthValuesList.get(columnIndex) / sumWidthValues;
                break;
        }
    }

    private int getImageItemWidth(Context context, int columnCount) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        return screenWidth / columnCount;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            switch (((TextView) v).getText().toString().trim()) {
                case "总额(元)":
                    orderAndRefreshTable(data, "price_total");
                    break;
                case "累计热量(GJ)":
                    orderAndRefreshTable(data, "sum_qqi");
                    break;
                case "热费(元)":
                    orderAndRefreshTable(data, "sum_qqi_price");
                    break;
                case "累计电量(KWh)":
                    orderAndRefreshTable(data, "sum_jqi");
                    break;
                case "电费(元)":
                    orderAndRefreshTable(data, "sum_jqi_price");
                    break;
                case "累计水量(T)":
                    orderAndRefreshTable(data, "sum_ft3q");
                    break;
                case "水费(元)":
                    orderAndRefreshTable(data, "sum_ft3q_price");
                    break;
            }
        }
    }

    private void orderAndRefreshTable(final List<List<String>> data, final String orderAccording) {
        if (parentFragment instanceof EconomicsAnalysisFragment1) {
            this.isAsc = ((EconomicsAnalysisFragment1) parentFragment).isAsc;
            List<String> d = data.get(0);
            data.remove(0);
            Collections.sort(data, new Comparator<List>() {
                @Override
                public int compare(List o1, List o2) {
                    switch (orderAccording) {
                        case "price_total":
                            if (isAsc) {
                                return Float.compare(Float.parseFloat(o1.get(1).toString()), Float.parseFloat(o2.get(1).toString()));
                            }
                            return Float.compare(Float.parseFloat(o2.get(1).toString()), Float.parseFloat(o1.get(1).toString()));
                        case "sum_qqi":
                            if (isAsc) {
                                return Float.compare(Float.parseFloat(o1.get(2).toString()), Float.parseFloat(o2.get(2).toString()));
                            }
                            return Float.compare(Float.parseFloat(o2.get(2).toString()), Float.parseFloat(o1.get(2).toString()));
                        case "sum_qqi_price":
                            if (isAsc) {
                                return Float.compare(Float.parseFloat(o1.get(3).toString()), Float.parseFloat(o2.get(3).toString()));
                            }
                            return Float.compare(Float.parseFloat(o2.get(3).toString()), Float.parseFloat(o1.get(3).toString()));
                        case "sum_jqi":
                            if (isAsc) {
                                return Float.compare(Float.parseFloat(o1.get(4).toString()), Float.parseFloat(o2.get(4).toString()));
                            }
                            return Float.compare(Float.parseFloat(o2.get(4).toString()), Float.parseFloat(o1.get(4).toString()));
                        case "sum_jqi_price":
                            if (isAsc) {
                                return Float.compare(Float.parseFloat(o1.get(5).toString()), Float.parseFloat(o2.get(5).toString()));
                            }
                            return Float.compare(Float.parseFloat(o2.get(5).toString()), Float.parseFloat(o1.get(5).toString()));
                        case "sum_ft3q":
                            if (isAsc) {
                                return Float.compare(Float.parseFloat(o1.get(6).toString()), Float.parseFloat(o2.get(6).toString()));
                            }
                            return Float.compare(Float.parseFloat(o2.get(6).toString()), Float.parseFloat(o1.get(6).toString()));
                        case "sum_ft3q_price":
                            if (isAsc) {
                                return Float.compare(Float.parseFloat(o1.get(7).toString()), Float.parseFloat(o2.get(7).toString()));
                            }
                            return Float.compare(Float.parseFloat(o2.get(7).toString()), Float.parseFloat(o1.get(7).toString()));
                    }
                    return 0;
                }
            });
            data.add(0, d);
            ((EconomicsAnalysisFragment1) parentFragment).resetAdapter(data);
        }
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TitleViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
        }
    }
}
