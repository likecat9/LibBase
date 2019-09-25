package com.my.baselibrary;

import android.app.Application;

import com.hjq.toast.ToastUtils;
import com.my.baselibrary.utils.BaseToastStyle;
import com.my.baselibrary.utils.DeBugUtils;

/**
 * @author : xk
 * @date : 2019/9/24
 * @description :
 */
public class BaseApplication extends Application {
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this, new BaseToastStyle(this));
        DeBugUtils.init(this);
    }
}
