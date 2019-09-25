package com.shopping.myapplication.fragment;

import android.view.View;

import com.hjq.toast.ToastUtils;
import com.my.baselibrary.fragment.BaseFragment;
import com.shopping.myapplication.R;

/**
 * @author : xk
 * @date : 2019/9/24
 * @description :
 */
public class Test2Fragment extends BaseFragment {
    @Override
    protected int setContentViewId() {
        return R.layout.fragment_test2;
    }

    @Override
    protected void initView(View view) {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void widgetClick(View view, int id) {
        ToastUtils.show("asfdasdf");
    }
}
