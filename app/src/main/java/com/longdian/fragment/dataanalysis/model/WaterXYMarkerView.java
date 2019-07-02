
package com.longdian.fragment.dataanalysis.model;

import android.content.Context;
import android.widget.TextView;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.longdian.R;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class WaterXYMarkerView extends MarkerView {

    private TextView tvContent;
    private List<Map<String, String>> lists;

    private DecimalFormat format;

    public WaterXYMarkerView(Context context, List<Map<String, String>> lists) {
        super(context, R.layout.custom_marker_view);

        this.lists = lists;
        tvContent = (TextView) findViewById(R.id.tvContent);
        format = new DecimalFormat("###.0");
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String name = lists.get((int) e.getX()).get("stand_name");
        tvContent.setText(name + " " + format.format(e.getY()));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
