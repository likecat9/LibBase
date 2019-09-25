package com.my.baselibrary.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.my.baselibrary.utils.DPPXUtil;

public class StatusBarPaddingView extends View {
    public StatusBarPaddingView(Context context) {
        this(context, null);
    }

    public StatusBarPaddingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusBarPaddingView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public StatusBarPaddingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMeasuredWidth(), getStatusBarHeight());
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
