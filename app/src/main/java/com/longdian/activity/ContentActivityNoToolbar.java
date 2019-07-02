package com.longdian.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.longdian.LoginActivity;
import com.longdian.R;
import com.longdian.util.LogUtil;

public class ContentActivityNoToolbar extends AppCompatActivity {

    public static final String FragmentClassName = "FragmentClassName";
    private FragmentManager fragmentManager;

    public static void start(Activity activity, Class clazz) {
        start(activity, clazz, null);
    }

    public static void start(Activity activity, Class clazz, String title) {
        Intent intent = new Intent(activity, ContentActivityNoToolbar.class);
        intent.putExtra(FragmentClassName, clazz);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        fragmentManager = getSupportFragmentManager();
        Class clazz = (Class) getIntent().getSerializableExtra(FragmentClassName);
        LoginActivity.setStatusBarTranslucent(this, android.R.color.transparent);
        try {
            replaceFragment((Fragment) clazz.newInstance());
        } catch (Exception e) {
            LogUtil.e(e);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }
}
