package com.shopping.myapplication;

import android.view.View;

import com.my.baselibrary.activity.BaseActivity;
import com.shopping.myapplication.fragment.Test2Fragment;
import com.shopping.myapplication.fragment.TestFragment;

public class MainActivity extends BaseActivity {


    @Override
    protected int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(View view) {
        TestFragment t1 = new TestFragment();
        Test2Fragment t2 = new Test2Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, t1).addToBackStack("t1").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, t2).addToBackStack("t2").commit();
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void widgetClick(View view, int id) {

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        getSupportFragmentManager().popBackStack();
    }
}
