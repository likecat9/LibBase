package com.my.baselibrary.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.my.baselibrary.R;
import com.my.baselibrary.activity.BaseActivity;
import com.my.baselibrary.arouter.BaseARouterURI;
import com.my.baselibrary.widget.LinearItemDecoration;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected View contentView;
    protected String TAG = this.getClass().getSimpleName();
    protected @Nullable
    TextView baseTitle_tv;
    protected @Nullable
    ImageView baseBack_iv;
    private @Nullable
    Toolbar title_bar;
    protected @Nullable
    TextView right_text;
    protected int arouter_navigation_request_code = 666_6;
    private List<Disposable> disposables = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(setContentViewId(), container, false);

        baseTitle_tv = contentView.findViewById(R.id.title_tv);
        baseBack_iv = contentView.findViewById(R.id.back_iv);
        if (baseBack_iv != null) {
            baseBack_iv.setOnClickListener(this);
        }
        title_bar = contentView.findViewById(R.id.title_bar);
        right_text = findViewById(R.id.right_text);

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        if (title_bar != null) {
            if (activity != null) {
                activity.setSupportActionBar(title_bar);
            }
        }

        initView(contentView);
        return contentView;
    }


    protected abstract @LayoutRes
    int setContentViewId();

    protected abstract void initView(View view);

    protected abstract void doBusiness();

    @Override
    public void onClick(View view) {
        widgetClick(view, view.getId());
    }

    protected abstract void widgetClick(View view, @IdRes int id);

    protected <T extends View> T findViewById(@IdRes int id) {
        return contentView.findViewById(id);
    }

    protected void startFragment(String fragmentURI) {
        startFragment(fragmentURI, null);
    }

    protected void startFragment(String fragmentURI, Bundle bundle) {
        if (requireActivity() instanceof BaseActivity) {

                BaseActivity baseActivity = (BaseActivity) requireActivity();

                Postcard postcard = ARouter.getInstance()
                        .build(BaseARouterURI.FRAGMENT_CONTAINER);

                LogisticsCenter.completion(postcard);

                Class<?> destination = postcard.getDestination();

                Intent intent = new Intent(requireContext(), destination);
                intent.putExtra(baseActivity.BUNDLE_PARAM, bundle);
                intent.putExtra(baseActivity.FRAGMENT_KEY, fragmentURI);

                startActivityForResult(intent, arouter_navigation_request_code);
            }
    }

    protected void startFragment(String fragmentURI,Bundle bundle,int requestCode){
        if (requireActivity() instanceof BaseActivity) {

            BaseActivity baseActivity = (BaseActivity) requireActivity();

            Postcard postcard = ARouter.getInstance()
                    .build(BaseARouterURI.FRAGMENT_CONTAINER);

            LogisticsCenter.completion(postcard);

            Class<?> destination = postcard.getDestination();

            Intent intent = new Intent(requireContext(), destination);
            intent.putExtra(baseActivity.BUNDLE_PARAM, bundle);
            intent.putExtra(baseActivity.FRAGMENT_KEY, fragmentURI);

            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doBusiness();
    }

    /*titlebar 相关设置*/

    protected void setRightText(@StringRes Integer id) {
        if (id != null) {
            setRightText(getResources().getString(id));
        }
    }

    protected void setRightText(String text) {
        if (right_text != null) {
            right_text.setText(text);
        }
    }

    protected void setRightTextAndColor(@StringRes Integer textId, @ColorRes Integer color) {
        setRightTextAndColor(getResources().getString(textId), getResources().getColor(color));
    }

    protected void setRightTextAndColor(String text, String color) {
        setRightTextAndColor(text, Color.parseColor(color));
    }

    protected void setRightTextAndColor(String text, int color) {
        if (right_text != null) {
            right_text.setText(text);
            right_text.setTextColor(color);
        }
    }

    protected void setTitleTextAndTextColor(@StringRes Integer titleText, String color) {
        if (baseTitle_tv != null) {
            baseTitle_tv.setText(getResources().getString(titleText));
            baseTitle_tv.setTextColor(Color.parseColor(color));
        }
    }

    protected void setTitleTextAndTextColor(String titleText, @ColorRes Integer color) {
        if (baseTitle_tv != null) {
            baseTitle_tv.setText(titleText);
            baseTitle_tv.setTextColor(getResources().getColor(color));
        }
    }

    protected void setTitleTextAndTextColor(@StringRes Integer titleText, @ColorRes Integer color) {
        if (baseTitle_tv != null) {
            baseTitle_tv.setText(getResources().getString(titleText));
            baseTitle_tv.setTextColor(getResources().getColor(color));
        }
    }

    protected void setTitleTextAndTextColor(String titleText, String color) {
        if (baseTitle_tv != null) {
            baseTitle_tv.setText(titleText);
            baseTitle_tv.setTextColor(Color.parseColor(color));
        }
    }

    protected void setTitleText(String titleText) {
        if (baseTitle_tv != null) {
            baseTitle_tv.setText(titleText);
        }
    }

    protected void setTitleText(@StringRes Integer titleText) {
        if (baseTitle_tv != null) {
            baseTitle_tv.setText(getResources().getString(titleText));
        }
    }

    protected void setTitleBackColor(String color) {
        if (baseBack_iv != null) {
            baseBack_iv.getDrawable().setTint(Color.parseColor(color));
        }
    }

    protected void setTitleBackColor(@ColorRes Integer color) {
        if (baseBack_iv != null) {
            baseBack_iv.getDrawable().setTint(getResources().getColor(color));
        }
    }

    protected void setTitleTextAndBackColor(String titleText, String color) {
        if (baseTitle_tv != null) {
            baseTitle_tv.setText(titleText);
        }
        if (baseBack_iv != null) {
            baseBack_iv.getDrawable().setTint(Color.parseColor(color));
        }

    }

    protected void setTitleTextAndBackColor(@StringRes Integer titleText, @ColorRes Integer color) {
        if (baseTitle_tv != null) {
            baseTitle_tv.setText(getResources().getString(titleText));
        }
        if (baseBack_iv != null) {
            baseBack_iv.getDrawable().setTint(getResources().getColor(color));
        }
    }

    protected void setTitleBarColor(String color) {
        if (title_bar != null) {
            title_bar.setBackgroundColor(Color.parseColor(color));
        }
    }

    protected void setTitleBarColor(@ColorRes Integer color) {
        if (title_bar != null) {
            title_bar.setBackgroundColor(getResources().getColor(color));
        }
    }

    protected void setTitleTextColorTextAndBarkbgColorAndBgColor(@ColorRes Integer textColor,
                                                                 @StringRes Integer textText,
                                                                 @ColorRes Integer backColor,
                                                                 @ColorRes Integer bgColor) {
        if (bgColor != null && title_bar != null)
            title_bar.setBackgroundColor(getResources().getColor(bgColor));
        if (textText != null && baseTitle_tv != null)
            baseTitle_tv.setText(getResources().getString(textText));
        if (textColor != null && baseTitle_tv != null)
            baseTitle_tv.setTextColor(getResources().getColor(textColor));
        if (backColor != null && baseBack_iv != null) {
            baseBack_iv.setBackgroundColor(getResources().getColor(backColor));
        }
    }

    protected void setTitleTextColorTextAndBarkbgColorAndBgColor(String textColor,
                                                                 String textText,
                                                                 String backColor,
                                                                 String bgColor) {
        if (bgColor != null && title_bar != null)
            title_bar.setBackgroundColor(Color.parseColor(bgColor));
        if (textText != null && baseTitle_tv != null)
            baseTitle_tv.setText(textText);
        if (textColor != null && baseTitle_tv != null)
            baseTitle_tv.setTextColor(Color.parseColor(textColor));
        if (backColor != null && baseBack_iv != null)
            baseBack_iv.setBackgroundColor(Color.parseColor(backColor));
    }

    public void setDark(boolean dark) {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            activity.setDark(dark);
            activity.statusBarSetting();
        }
    }

    protected RecyclerView setBaseRecyclerView(@IdRes int view) {
        RecyclerView recycler_view = findViewById(view);
        recycler_view.setLayoutManager(new LinearLayoutManager(requireContext()));
        recycler_view.addItemDecoration(new LinearItemDecoration(requireContext(), LinearLayout.VERTICAL));
        return recycler_view;
    }

    protected void setBaseBackColor(@ColorRes int color) {
        if (baseBack_iv != null) {
            Drawable drawable = baseBack_iv.getDrawable();
            Drawable wrap = DrawableCompat.wrap(drawable);
            DrawableCompat.setTintList(wrap, getResources().getColorStateList(color));
        }
    }

    public void onBackPressed() {
    }

    protected void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (Disposable disposable : disposables) {
            disposable.dispose();
        }
    }
}
