package com.longdian;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.TouchDelegate;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

import butterknife.ButterKnife;
import butterknife.OnClick;
import com.longdian.activity.MainActivity;
import com.longdian.bean.GlobalInfo;
import com.longdian.bean.OprInfo;
import com.longdian.bean.OprInfoDto;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.PreferencesUtils;
import com.longdian.util.SystemBarTintManager;
import com.longdian.util.ToastUtils;

public class LoginActivity extends AppCompatActivity {

    public static final String SAVE_PWD = "savePwd";
    public static final String AUTO_LOGIN = "autoLogin";
    public static final String SAVED_ACCOUNT = "savedAccount";
    public static final String SAVED_PWD = "savedPwd";
    private CheckBox savePwd;
    private CheckBox autoLogin;
    private EditText tvAccount;
    private EditText tvPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_v2);
//        ApplicationInfo applicationInfo = getApplicationInfo();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String app_tag = applicationInfo.metaData.getString("app_tag");
            DynamicAppUtil.setCurrentAppTag(app_tag);
            if (app_tag != null) {
                setImg(app_tag);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (2 > 3) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return;
        }
//        getSupportActionBar().hide();
        ButterKnife.bind(this);
        setStatusBarTranslucent(this, -1);
        // 初始化
        savePwd = (CheckBox) findViewById(R.id.id_save_pwd);
        autoLogin = (CheckBox) findViewById(R.id.id_auto_login);
        tvAccount = (EditText) findViewById(R.id.id_phone);
        tvPwd = (EditText) findViewById(R.id.id_pwd);

        boolean isSavePwd = PreferencesUtils.getBoolean(this, SAVE_PWD);
        boolean isAutoLogin = PreferencesUtils.getBoolean(this, AUTO_LOGIN);
        String savedAccount = PreferencesUtils.getString(this, SAVED_ACCOUNT);
        String savedPwd = PreferencesUtils.getString(this, SAVED_PWD);

        final CheckBox viewPwd = (CheckBox) findViewById(R.id.cb_view_pwd);
        viewPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    tvPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                tvPwd.setSelection(tvPwd.getText().length());
            }
        });
        findViewById(R.id.id_auto_login_parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoLogin.setChecked(!autoLogin.isChecked());
            }
        });
        findViewById(R.id.id_save_pwd_parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePwd.setChecked(!savePwd.isChecked());
            }
        });
        addDefaultScreenArea(viewPwd, 50, 50, 50, 50);
        savePwd.setChecked(isSavePwd);
        autoLogin.setChecked(isAutoLogin);
        tvAccount.setText(savedAccount);
        if (isSavePwd) {
            tvPwd.setText(savedPwd);
        }
        if (isAutoLogin) {
            Login();
        }
    }

    // 增大checkBox的可点击范围
    public void addDefaultScreenArea(final View view, final int top, final int bottom, final int left, final int right) {
        final View parent = (View) view.getParent();
        parent.post(new Runnable() {
            public void run() {

                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);

                bounds.top -= top;
                bounds.bottom += bottom;
                bounds.left -= left;
                bounds.right += right;

                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);

                if (view.getParent() instanceof View) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });

    }


    @OnClick(R.id.id_submit)
    public void Login() {
        String account = tvAccount.getText().toString();
        String pwd = tvPwd.getText().toString();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd)) {
            ToastUtils.showToast(this, "账号或密码错误");
            return;
        }
        doLogin(account, pwd);
    }

    public void doLogin(final String account, final String pwd) {
        HoolaiHttpMethods.getInstance().login(this, account, pwd, new ObserverOnNextAndErrorListener<OprInfoDto>() {
            @Override
            public void onNext(OprInfoDto oprInfo) {
                GlobalInfo.oprInfo = oprInfo;

                PreferencesUtils.putBoolean(LoginActivity.this, SAVE_PWD, savePwd.isChecked());
                PreferencesUtils.putBoolean(LoginActivity.this, AUTO_LOGIN, autoLogin.isChecked());
                PreferencesUtils.putString(LoginActivity.this, SAVED_ACCOUNT, account);
                PreferencesUtils.putString(LoginActivity.this, SAVED_PWD, pwd);

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onError(HoolaiException e) {
                ToastUtils.showToast(LoginActivity.this, e.getMessage());
            }
        });
    }

    public static void setStatusBarTranslucent(Activity activity, int colorRes) {
        // 如果版本在4.4以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (colorRes > 0) {
                SystemBarTintManager tintManager = new SystemBarTintManager(activity);
                tintManager.setStatusBarTintEnabled(true);
                tintManager.setStatusBarTintResource(colorRes);
            }
        }
    }

    public static void clearAutoLogin(Activity activity) {
        PreferencesUtils.putBoolean(activity, AUTO_LOGIN, false);
    }

    public static void updatePwd(Activity activity, String pwd) {
        PreferencesUtils.putString(activity, SAVED_PWD, pwd);
    }

    private void setImg(String app_tag) {
        if (app_tag.equals("app_name2")) {
            ((ImageView)findViewById(R.id.iv_login_icon)).setImageResource(R.mipmap.icon_logo2);

        }
    }
}
