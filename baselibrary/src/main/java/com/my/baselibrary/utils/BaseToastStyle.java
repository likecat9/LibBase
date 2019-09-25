package com.my.baselibrary.utils;

import android.content.Context;
import android.view.Gravity;

import com.hjq.toast.style.ToastQQStyle;

/**
 * @author : xk
 * @date : 2019/9/25
 * @description :
 */
public class BaseToastStyle extends ToastQQStyle {
    public BaseToastStyle(Context context) {
        super(context);
    }

    @Override
    public int getGravity() {
        return Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
    }

    @Override
    public int getYOffset() {
        return 100;
    }
}
