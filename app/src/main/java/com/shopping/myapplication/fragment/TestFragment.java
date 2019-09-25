package com.shopping.myapplication.fragment;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.my.baselibrary.fragment.BaseLifecycleFragment;
import com.my.baselibrary.utils.SchedulerProvider;
import com.my.baselibrary.widget.LinearItemDecoration;
import com.shopping.myapplication.R;
import com.shopping.myapplication.fragment.adapter.TestAdapter;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle3.LifecycleProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * @author : xk
 * @date : 2019/9/24
 * @description :
 */
public class TestFragment extends BaseLifecycleFragment {
    private TestAdapter testAdapter = new TestAdapter(R.layout.item_test);
    private final LifecycleProvider<Lifecycle.Event> provider
            = AndroidLifecycle.createLifecycleProvider(this);
    @Override
    protected int setContentViewId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView(View view) {
        RecyclerView recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setAdapter(testAdapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(requireContext()));
        recycler_view.addItemDecoration(new LinearItemDecoration(requireContext(), LinearLayout.VERTICAL));

    }

    @Override
    protected void doBusiness() {

        getNum()
                .flatMap(types -> Observable.fromIterable(types)
                                .map(type -> {
//                            Log.d(TAG, "type: " + type);
                                    switch (type) {
                                        case 1:
                                            Log.d(TAG, "getA().startWith(new ArrayList<String>()): ");
                                            return getA().startWith(new ArrayList<String>());
                                        case 2:
                                            Log.d(TAG, "getB().startWith(new ArrayList<String>()): ");
                                            return getB().startWith(new ArrayList<String>());
                                        case 3:
                                            Log.d(TAG, "getC().startWith(new ArrayList<String>()): ");
                                            return getC().startWith(new ArrayList<String>());
                                        default:
                                            throw new IllegalArgumentException();
                                    }
                                })
                                .<List<Observable<? extends List<? extends String>>>>collectInto(new ArrayList<>(), List::add)
                                .toObservable()
                )
                .flatMap(requestObservables -> {
//                    Log.d(TAG, "requestObservables.size(): " + requestObservables.size());
                    return Observable.combineLatest(requestObservables, objects -> objects);
                })
                .flatMap(objects -> Observable.fromArray(objects)
                        .<List<String>>collectInto(new ArrayList<>(), (arrylist, o) -> arrylist.addAll((List<String>) o))
                        .toObservable()
                )
                .compose(SchedulerProvider.io_main_Observable())
                .compose(provider.bindToLifecycle())
                .subscribe(data -> {
                    Log.d(TAG, "data.size(): " + data.size());
                    testAdapter.setNewData(data);
                });
    }

    @Override
    protected void widgetClick(View view, int id) {

    }

    private Observable<List<String>> getA() {
        List<String> strings = new ArrayList<>();
        strings.add("A");
        strings.add("A");
        strings.add("A");
        strings.add("A");
        strings.add("A");
        return Observable
                .timer(15, TimeUnit.SECONDS)
                .flatMap(l -> {
//                    Log.d(TAG, "getA: " + l);
                    return Observable.just(strings);
                });
    }

    private Observable<List<String>> getB() {
        List<String> strings = new ArrayList<>();
        strings.add("B");
        strings.add("B");
        strings.add("B");
        strings.add("B");
        strings.add("B");
        return Observable
                .timer(10, TimeUnit.SECONDS)
                .flatMap(l -> {
//                    Log.d(TAG, "getB: " + l);
                    return Observable.just(strings);
                });
    }

    private Observable<List<String>> getC() {
        List<String> strings = new ArrayList<>();
        strings.add("C");
        strings.add("C");
        strings.add("C");
        strings.add("C");
        strings.add("C");
        return Observable
                .timer(25, TimeUnit.SECONDS)
                .flatMap(l -> {
//                    Log.d(TAG, "getC: " + l);
                    return Observable.just(strings);
                });
    }

    private Observable<List<Integer>> getNum() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        return Observable
                .timer(1, TimeUnit.SECONDS)
                .flatMap(l -> {
//                    Log.d(TAG, "getNum: " + l);
                    return Observable.just(integers);
                });
    }
}
