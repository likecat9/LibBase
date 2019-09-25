package com.my.baselibrary.utils;

import android.app.Application;
import android.content.pm.ApplicationInfo;

public class DeBugUtils {
    private static Application application;

    public static void init(Application application) {
        DeBugUtils.application = application;
    }

    public static boolean isDebug() {
        try {
            ApplicationInfo applicationInfo = application.getApplicationInfo();
            return (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
