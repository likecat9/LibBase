package com.my.baselibrary.utils;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考博文：
 * https://blog.csdn.net/scau_zhangpeng/article/details/79098613
 */
public class MarketUtil {
    private static final String TAG = "MarketUtil";
    public static final String MEI_ZU = "com.meizu.mstore";
    public static final String XIAO_MI = "com.xiaomi.market";
    public static final String WAN_DOU_JIA = "com.wandoujia.phoenix2";
    public static final String _360 = "com.qihoo.appstore";
    public static final String QQ = "com.tencent.android.qqdownloader";
    public static final String BAI_DU = "com.baidu.appsearch";
    public static final String GOOGLE_PLAY = "com.android.vending";
    public static final String HUA_WEI = "com.huawei.appmarket";
    public static final String _91 = "com.dragon.android.pandaspace";
    public static final String PP = "com.pp.assistant";
    public static final String OPPO = "com.oppo.market";
    public static final String VIVO = "com.bbk.appstore";
    public static final String SOU_GOU = "com.sogou.androidtool";
    public static final String SAMSUNG = "com.sec.android.app.samsungapps";
    public static final String LENOVO = "com.lenovo.leos.appstore";
    public static final String ZTE = "zte.com.market";
    public static final String AN_ZHI = "com.hiapk.marketpho";
    public static final String ying_yong_hui = "com.yingyonghui.market";
    public static final String JI_FENG = "com.mappn.gfan";
    public static final String AN_ZHUO = "com.hiapk.marketpho";
    public static final String GO = "cn.goapk.market";
    public static final String KU_PAI = "com.yulong.android.coolmart";
    public static final String KU = "com.coolapk.market";
    public static final String JING_LI = "com.gionee.aora.market";

    @StringDef({MEI_ZU, XIAO_MI, WAN_DOU_JIA, _360, QQ, BAI_DU, GOOGLE_PLAY, HUA_WEI, _91, PP, OPPO,
            VIVO, SOU_GOU, SAMSUNG, LENOVO, ZTE, AN_ZHI, ying_yong_hui, JI_FENG, AN_ZHUO, GO, KU_PAI,
            KU, JING_LI})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Market {
    }

    private static Application application;

    public static void init(Application application) {
        MarketUtil.application = application;
    }

    public static boolean toMarket(@Nullable @Market String market) {
        Uri uri = Uri.parse("market://details?id=" + application.getPackageName());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (market != null) {// 如果没给市场的包名，则系统会弹出市场的列表让你进行选择。
            intent.setPackage(market);
        }
        try {
            application.startActivity(intent);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean toSamsungMarket() {
        Uri uri = Uri.parse("http://www.samsungapps.com/appquery/appDetail.as?appId=" + application.getPackageName());
//        Uri uri = Uri.parse("http://apps.samsung.com/appquery/appDetail.as?appId=" + packageName);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.sec.android.app.samsungapps");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            application.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean toSonyMarket(String appId) {
        Uri uri = Uri.parse("http://m.sonyselect.cn/" + appId);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        Intent intent = new Intent();
//        intent.setAction("com.sonymobile.playnowchina.android.action.NOTIFICATION_APP_DETAIL_PAGE");
//        intent.setAction("com.sonymobile.playnowchina.android.action.APP_DETAIL_PAGE");
//        intent.putExtra("app_id", 9115);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            application.startActivity(intent);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<String> checkMarket() {
        List<String> packageNames = new ArrayList<>();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        PackageManager pm = application.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        int size = infos.size();
        for (int i = 0; i < size; i++) {
            ActivityInfo activityInfo = infos.get(i).activityInfo;
            String packageName = activityInfo.packageName;
            Log.d(TAG, "packageName : " + packageName);
            packageNames.add(packageName);
        }
        return packageNames;
    }
}
