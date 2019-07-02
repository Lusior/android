package com.longdian.fragment.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.longdian.R;

import java.util.ArrayList;
import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImgViewHolder> {

    private List<Integer> mImgList;
    private Activity activity;

    ImageListAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public ImgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_image, parent, false);

        return new ImgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImgViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.tv_position.setText(BaseMsgRelatedDrawingInfoFragment.str.get(position));
        Glide.with(activity).load(mImgList.get(position)).into(holder.iv_pic);
        holder.iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Rect frame = new Rect();
                    activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                    int statusBarHeight = frame.top;
                    v.getLocationOnScreen(location);
                    location[1] += statusBarHeight;
                } else {
                    v.getLocationOnScreen(location);
                }
                v.invalidate();

                Intent intent = new Intent(activity, GalleryActivity.class);
                Bundle b = new Bundle();
                b.putStringArray(GalleryActivity.PHOTO_SOURCE_ID, getUrl(mImgList));
                String[] strings = getUrl(mImgList);
                for (String string : strings) {
                    System.out.println(string);
                }
                intent.putExtras(b);
                intent.putExtra(GalleryActivity.PHOTO_SELECT_POSITION, position);
                activity.startActivity(intent);
                activity.overridePendingTransition(0, 0);
            }

        });
    }

    private String[] getUrl(List<Integer> mImgList) {
        List<String> stringList = new ArrayList<>();
        for (Integer resId : mImgList) {
            Resources r = activity.getResources();
            Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                    + r.getResourcePackageName(resId) + "/"
                    + r.getResourceTypeName(resId) + "/"
                    + r.getResourceEntryName(resId));
            stringList.add(uri.toString());
        }
        return stringList.toArray(new String[]{});
    }

    @Override
    public int getItemCount() {
        return mImgList == null ? 0 : mImgList.size();
    }

    void setImgList(List<Integer> imgList) {
        mImgList = imgList;
        notifyDataSetChanged();
    }

    public static class ImgViewHolder extends RecyclerView.ViewHolder {
        public final ImageView iv_pic;
        public final TextView tv_position;

        ImgViewHolder(View itemView) {
            super(itemView);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
            tv_position = (TextView) itemView.findViewById(R.id.tv_position);
        }
    }
}