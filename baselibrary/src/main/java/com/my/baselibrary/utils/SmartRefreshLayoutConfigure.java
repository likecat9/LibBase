package com.my.baselibrary.utils;

import android.app.Application;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

public class SmartRefreshLayoutConfigure {
    public static void init(Application application) {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) ->
                new ClassicsHeader(application.getApplicationContext()));

        SmartRefreshLayout.setDefaultRefreshFooterCreator((context2, layout) ->
                new ClassicsFooter(application.getApplicationContext()));
    }
}
