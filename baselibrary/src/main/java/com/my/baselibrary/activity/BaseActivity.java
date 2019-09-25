package com.my.baselibrary.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.android.arouter.launcher.ARouter;
import com.my.baselibrary.R;
import com.my.baselibrary.arouter.BaseARouterURI;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;


public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected View contentView;
    public String FRAGMENT_KEY = "FRAGMENT_KEY";
    protected String TAG = getClass().getSimpleName();
    protected TextView baseTitle_tv;
    protected ImageView baseBack_iv;
    private Toolbar title_bar;
    private boolean dark = false;
    private int exitCount;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    public final String BUNDLE_PARAM = "BUNDLE_PARAM";
    private List<Disposable> disposables = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        statusBarSetting();

        super.onCreate(savedInstanceState);
        contentView = LayoutInflater.from(this).inflate(setContentViewId(), null);
        setContentView(contentView);

        baseTitle_tv = contentView.findViewById(R.id.title_tv);
        baseBack_iv = contentView.findViewById(R.id.back_iv);
        title_bar = contentView.findViewById(R.id.title_bar);
        if (title_bar != null)
            setSupportActionBar(title_bar);

        initView(contentView);

        doBusiness();
    }


    protected abstract @LayoutRes
    int setContentViewId();

    protected abstract void initView(View view);

    protected abstract void doBusiness();

    @Override
    public void onClick(View view) {
        widgetClick(view, view.getId());
    }

    protected abstract void widgetClick(View view, @IdRes int id);

    protected void startFragment(String fragmentURI) {
        startFragment(fragmentURI, null);
    }

    protected void startFragment(String fragmentURI, Bundle bundle) {
        ARouter.getInstance()
                .build(BaseARouterURI.FRAGMENT_CONTAINER)
                .with(bundle)
                .withString(FRAGMENT_KEY, fragmentURI)
                .navigation();

    }
    /*titlebar 相关设置*/

    protected void setTitleTextAndTextColor(@StringRes Integer titleText, @ColorRes Integer color) {
        baseTitle_tv.setText(getResources().getString(titleText));
        baseTitle_tv.setTextColor(getResources().getColor(color));
    }

    protected void setTitleTextAndTextColor(String titleText, String color) {
        baseTitle_tv.setText(titleText);
        baseTitle_tv.setTextColor(Color.parseColor(color));
    }

    protected void setTitleText(String titleText) {
        baseTitle_tv.setText(titleText);
    }

    protected void setTitleText(@StringRes int titleText) {
        baseTitle_tv.setText(getResources().getString(titleText));
    }

    protected void setTitleBackColor(String color) {
        baseBack_iv.getDrawable().setTint(Color.parseColor(color));
    }

    protected void setTitleTextAndBackColor(String titleText, String color) {
        baseTitle_tv.setText(titleText);
        baseBack_iv.getDrawable().setTint(Color.parseColor(color));

    }

    protected void setTitleTextAndBackColor(@StringRes Integer titleText, @ColorRes Integer color) {
        baseTitle_tv.setText(getResources().getString(titleText));
        baseBack_iv.getDrawable().setTint(getResources().getColor(color));
    }

    protected void setTitleBarColor(String color) {
        title_bar.setBackgroundColor(Color.parseColor(color));
    }

    protected void setTitleBarColor(@ColorRes Integer color) {
        title_bar.setBackgroundColor(getResources().getColor(color));
    }

    protected void setTitleTextColorTextAndBarkColorAndBgColor(@ColorRes Integer textColor,
                                                               @StringRes Integer textText,
                                                               @ColorRes Integer backColor,
                                                               @ColorRes Integer bgColor) {
        if (bgColor != null)
            title_bar.setBackgroundColor(getResources().getColor(bgColor));
        if (textText != null)
            baseTitle_tv.setText(getResources().getString(textText));
        if (textColor != null)
            baseTitle_tv.setTextColor(getResources().getColor(textColor));
        if (backColor != null)
            baseBack_iv.setBackgroundColor(getResources().getColor(backColor));
    }

    protected void setTitleTextColorTextAndBarkColorAndBgColor(String textColor,
                                                               String textText,
                                                               String backColor,
                                                               String bgColor) {
        if (bgColor != null)
            title_bar.setBackgroundColor(Color.parseColor(bgColor));
        if (textText != null)
            baseTitle_tv.setText(textText);
        if (textColor != null)
            baseTitle_tv.setTextColor(Color.parseColor(textColor));
        if (backColor != null)
            baseBack_iv.setBackgroundColor(Color.parseColor(backColor));
    }

    public void setDark(boolean dark) {
        this.dark = dark;
    }


    public void statusBarSetting() {
        // 5.0以上系统状态栏透明
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        /// MIUI和FlymeUI提供了修改状态栏的文字和图标颜色方法
        // 小米MIUI
        try {
            Class clazz = getWindow().getClass();
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            if (dark) {    //状态栏亮色且黑色字体
                extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
            } else {       //清除黑色字体
                extraFlagField.invoke(window, 0, darkModeFlag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 魅族FlymeUI
        try {
            WindowManager.LayoutParams lp = window.getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (dark) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            window.setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // android6.0+系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (dark) {
                window.getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, getResources().getString(R.string.text_exit_app), Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }

    protected void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (Disposable disposable : disposables) {
            disposable.dispose();
        }
    }

    protected void superBackPressed() {
        super.onBackPressed();
    }
}
