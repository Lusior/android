package com.longdian.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.longdian.R;

import java.util.ArrayList;
import java.util.List;

public class BaseMsgRelatedDrawingInfoFragment extends Fragment {

    public static List<String> str;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_base_msg_related_drawing_info, container, false);
        RecyclerView mRVlist = (RecyclerView) v.findViewById(R.id.rv_imagelist);
        mRVlist.setLayoutManager(new LinearLayoutManager(getContext()));

        ImageListAdapter mAdapter;
        mRVlist.setAdapter(mAdapter = new ImageListAdapter(getActivity()));

        str = new ArrayList<>();
        str.add("热力系统流程图");
        str.add("低压柜电气图");
        str.add("循环泵变频柜电气图");
        str.add("补水变频柜电气图1");
        str.add("补水变频柜电气图2");

        List<Integer> imglist = new ArrayList<>();
        //热力系统流程图
        imglist.add(R.drawable.info3_1);
        //低压柜电气图
        imglist.add(R.drawable.info2_1);
        //循环泵变频柜电气图
        imglist.add(R.drawable.info4_1);
        //补水变频柜电气图1
        imglist.add(R.drawable.info0_1);
        //补水变频柜图2
        imglist.add(R.drawable.info1_1);

        mAdapter.setImgList(imglist);
        mAdapter.notifyDataSetChanged();

        return v;
    }
}
