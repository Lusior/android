package com.longdian.fragment.base;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;
import com.longdian.R;

public class GalleryAdapter extends PagerAdapter {

    private Activity activity;
    private String[] urls;

    GalleryAdapter(Activity activity, String[] urls) {
        this.activity = activity;
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        PhotoView photoView = (PhotoView) LayoutInflater.from(activity).inflate(R.layout.pager_item, container, false);
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        Glide.with(activity)
                .load(urls[position])
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .priority(Priority.IMMEDIATE)
                .into(photoView);

        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
