package com.my.baselibrary.activity;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.my.baselibrary.R;
import com.my.baselibrary.arouter.BaseARouterURI;

@Route(path = BaseARouterURI.FRAGMENT_CONTAINER)
public final class FragmentContainerActivity extends BaseActivity {
    private String TAG = this.getClass().getName();


    @Override
    public int setContentViewId() {
        return R.layout.activity_fragment_container;
    }

    @Override
    protected void initView(View view) {
        String fragment_uri = getIntent().getStringExtra(FRAGMENT_KEY);

        Fragment fragment = (Fragment) ARouter.getInstance().build(fragment_uri).navigation();
        fragment.setArguments(getIntent().getBundleExtra(BUNDLE_PARAM));
        getSupportFragmentManager().beginTransaction().add(R.id.container_layout, fragment).commit();
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void widgetClick(View view, int id) {

    }

    @Override
    public void onBackPressed() {
        super.superBackPressed();
    }
}
