package com.my.baselibrary.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.provider.Settings;

import java.util.List;

public class SystemPageRouter {
    /**
     * 数据漫游设置
     *
     * @param activity
     */
    public static void toDataRoamingPage(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        List<ResolveInfo> infos = activity.getPackageManager().queryIntentActivities(intent, 0);
        if (infos != null && infos.size() > 0) {
            activity.startActivity(intent);
        }
    }

    /**
     * 显示设置以允许配置无线控制，如Wi-Fi，蓝牙和移动网络。
     *
     * @param activity
     */
    public static void  toWirelessPage(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        List<ResolveInfo> infos = activity.getPackageManager().queryIntentActivities(intent, 0);
        if (infos != null && infos.size() > 0) {
            activity.startActivity(intent);
        }
    }

    /**
     * 启用或禁用飞行模式
     *
     * @param activity
     */
    public static void toAirPlanePage(Activity activity, boolean b) {
        Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
        List<ResolveInfo> infos = activity.getPackageManager().queryIntentActivities(intent, 0);
        if (infos != null && infos.size() > 0) {
            activity.startActivity(intent);
        } else
            toWirelessPage(activity);
    }


    /**
     * 定位功能
     *
     * @param activity
     */
    public static void toLocationSourcePage(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        List<ResolveInfo> infos = activity.getPackageManager().queryIntentActivities(intent, 0);
        if (infos != null && infos.size() > 0) {
            activity.startActivity(intent);
        }
    }
}
