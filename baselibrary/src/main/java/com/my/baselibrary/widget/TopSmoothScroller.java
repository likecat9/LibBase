package com.my.baselibrary.widget;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import androidx.recyclerview.widget.LinearSmoothScroller;


/**
 * @author 徐康
 * @date 2019/6/13
 * @描述：
 */
public class TopSmoothScroller extends LinearSmoothScroller {
    @SuppressLint("StaticFieldLeak")
    private static TopSmoothScroller topSmoothScroller;
    private static Application application;

    public static void init(Application application) {
        if (TopSmoothScroller.application == null)
            TopSmoothScroller.application = application;

    }

    private TopSmoothScroller(Context context) {
        super(context);
    }

    public static TopSmoothScroller getInstance() {
        if (application==null){
            throw new RuntimeException("TopSmoothScroller >>> application==null !");
        }
        if (topSmoothScroller == null) {
            topSmoothScroller = new TopSmoothScroller(application.getApplicationContext());
        }
        return topSmoothScroller;
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;
    }

    @Override
    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return (25.0F / (float) displayMetrics.densityDpi) * 3.5F;
    }
}
