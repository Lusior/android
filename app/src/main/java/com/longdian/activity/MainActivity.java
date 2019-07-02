package com.longdian.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.longdian.LoginActivity;
import com.longdian.R;
import com.longdian.fragment.*;
import com.longdian.util.DensityUtil;
import com.longdian.util.ToastUtils;

public class MainActivity extends TopBarBaseActivity implements RadioGroup.OnCheckedChangeListener {

    private FragmentManager fragmentManager;
    private RadioGroup radioGroup;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        LoginActivity.setStatusBarTranslucent(this, R.color.colorPrimary);
        initView();
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.id_main_rg);
        radioGroup.setOnCheckedChangeListener(this);

        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
    }

    /**
     * 解决不能控制RadioButton图片大小的问题
     *
     * @param id
     * @param drawableId
     */
    private void setRadioButtonTopDrawableSize(int id, int drawableId) {
        int size = DensityUtil.dp2px(this, 25);
        RadioButton radioButton = (RadioButton) findViewById(id);
        Drawable drawable = getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, size, size);
        radioButton.setCompoundDrawables(null, drawable, null, null);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    private boolean isGrid = false;// 界面切换标志

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.id_base_msg:
                setTitle(getString(R.string.main_navigation_base_msg));
                initMenu();
                hideRight();
                replaceFragment(new BaseMsgFragment());
                break;
            case R.id.id_running_state:
                setTitle(getString(R.string.main_navigation_running_state));
                initMenu();
                replaceFragment(RunningStateFragment.newInstance(1));
                break;
            case R.id.id_data_analysis:
                setTitle(getString(R.string.main_navigation_data_analysis));
                hideRight();
                replaceFragment(new DataAnalysisFragment());
                break;
            case R.id.id_weather:
                setTitle(getString(R.string.main_navigation_base_load_prediction));
                hideRight();
                replaceFragment(new WeatherFragment());
                break;
            case R.id.id_base_hotinfo:
                setTitle(getString(R.string.main_navigation_base_hotinfo));
                hideRight();
                replaceFragment(new StationFragment());
                break;
        }
    }

    private void initMenu() {
        isGrid = false;
        final int gridRes = R.drawable.ic_more_select;
        final int listRes = R.drawable.btn_timer_quick;
        setTopRightButton("button", gridRes, new OnClickListener() {
            @Override
            public void onClick() {
                replaceFragment(RunningStateFragment.newInstance(isGrid ? 1 : 3));
                isGrid = !isGrid;
                updateMenuItemIcon(isGrid ? listRes : gridRes);
            }
        });
        updateMenuItemIcon(isGrid ? listRes : gridRes);
    }

    private long mExitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showToast(this, "再按一次退出");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
