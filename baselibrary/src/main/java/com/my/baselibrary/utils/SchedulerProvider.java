package com.my.baselibrary.utils;


import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider {

    public static <T> FlowableTransformer<T, T> io_main_flowAble() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> FlowableTransformer<T, T> computation_main_flowAble() {
        return upstream ->
                upstream.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> io_main_Observable() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> MaybeTransformer<T, T> io_main_maybe() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> MaybeTransformer<T, T> computation_main_maybe() {
        return upstream ->
                upstream.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> SingleTransformer<T, T> io_main_single() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public static <T> SingleTransformer<T, T> computation_main_single() {
        return upstream ->
                upstream.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }
}
