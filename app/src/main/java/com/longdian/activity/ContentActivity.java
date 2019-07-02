package com.longdian.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import com.longdian.LoginActivity;
import com.longdian.R;
import com.longdian.util.LogUtil;

public class ContentActivity extends TopBarBaseActivity {

    public static final String FragmentClassName = "FragmentClassName";
    public static final String Title = "title";
    private FragmentManager fragmentManager;

    public static void start(Activity activity, Class clazz) {
        start(activity, clazz, null);
    }

    public static void start(Activity activity, Class clazz, String title) {
        Intent intent = new Intent(activity, ContentActivity.class);
        intent.putExtra(FragmentClassName, clazz);
        intent.putExtra(Title, title);
        activity.startActivity(intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_content;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        LoginActivity.setStatusBarTranslucent(this, R.color.colorPrimary);
        fragmentManager = getSupportFragmentManager();
        Class clazz = (Class) getIntent().getSerializableExtra(FragmentClassName);
        String title = getIntent().getStringExtra(Title);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
            setTopLeftButton(R.drawable.ic_return_white_24dp, new OnClickListener() {
                @Override
                public void onClick() {
                    finish();
                }
            });
        } else {
            toolbar.setVisibility(View.GONE);
            LoginActivity.setStatusBarTranslucent(this, -1);
        }
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
