package com.my.baselibrary.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.my.baselibrary.R;
import com.my.baselibrary.utils.DPPXUtil;


public class BaseToolBar extends Toolbar {
    public BaseToolBar(Context context) {
        this(context, null);
    }

    public BaseToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // android 4.4以上将Toolbar添加状态栏高度的上边距，沉浸到状态栏下方
        int compatPadingTop = getStatusBarHeight();
        this.setPadding(getPaddingLeft(), getPaddingTop() + compatPadingTop, getPaddingRight(), getPaddingBottom());
    }
    private int getStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        Log.d("CompatToolbar", "状态栏高度：" + DPPXUtil.px2dp(statusBarHeight) + "dp");
        return statusBarHeight;
    }
}
