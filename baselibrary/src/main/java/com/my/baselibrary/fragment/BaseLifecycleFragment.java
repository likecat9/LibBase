package com.my.baselibrary.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * @author : xk
 * @date : 2019/9/24
 * @description :
 */
public abstract class BaseLifecycleFragment extends BaseFragment {
    private Observable<Lifecycle> lifecycleObservable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleObservable = Observable.create(emitter -> emitter.onNext(getLifecycle()));
    }

    public <T> ObservableTransformer<T, T> bindToLifecycle() {
        return upstream ->
                upstream.takeUntil(lifecycleObservable.
                        filter(current -> {
                            Log.d(TAG, "bindToLifecycle: " + current.getCurrentState().toString());
                            return current.getCurrentState() == Lifecycle.State.DESTROYED;
                        }));
    }
}
