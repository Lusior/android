package com.longdian.fragment.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.longdian.R;

public class GalleryActivity extends AppCompatActivity {

    static final String PHOTO_SOURCE_ID = "PHOTO_SOURCE_ID";
    static final String PHOTO_SELECT_POSITION = "PHOTO_SELECT_POSITION";

    private int position;
    private String[] urls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle b = intent.getExtras();
            assert b != null;
            urls = b.getStringArray(PHOTO_SOURCE_ID);

            position = intent.getIntExtra(PHOTO_SELECT_POSITION, 0);
        }

        GalleryAdapter galleryAdapter = new GalleryAdapter(this, urls);
        viewPager.setAdapter(galleryAdapter);
        viewPager.setCurrentItem(position);
    }
}
