package com.my.baselibrary.utils;

import android.app.Application;

import androidx.fragment.app.Fragment;

import com.my.baselibrary.listener.SelectedSameMenuListener;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: xk
 * @data: 2019/8/23
 * @description:
 */
public class BottomNavSelectUtil {
    private BottomNavSelectUtil(){}
    private static BottomNavSelectUtil instance;
    private Set<SelectedSameMenuListener> listeners;
    private Application application;

    public static BottomNavSelectUtil getInstance(){
        if (instance==null){
            instance=new BottomNavSelectUtil();
        }
        return instance;
    }

    public void init(Application application){
        instance.application=application;
        listeners=new HashSet<>();
    }

    public void add(SelectedSameMenuListener listener){
        listeners.add(listener);
    }

    public void selectSameMenu(){
        for (SelectedSameMenuListener listener : listeners) {
            if (listener instanceof Fragment){
                if (!((Fragment)listener).isVisible()) {
                    return;
                }
            }
            listener.onSelected();
        }
    }
}
