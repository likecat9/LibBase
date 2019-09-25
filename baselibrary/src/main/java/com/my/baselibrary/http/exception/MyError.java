package com.my.baselibrary.http.exception;

import android.util.Log;

import androidx.annotation.Nullable;

import io.reactivex.functions.Consumer;

public class MyError implements Consumer<Throwable> {
    private String TAG = this.getClass().getName();
    private String mMessage;

    public MyError(String mMessage) {
        this.mMessage = mMessage;
    }

    public MyError() {
    }

    @Override
    public void accept(@Nullable Throwable throwable) {
        if (throwable != null) {
            if (mMessage == null) {
                mMessage = throwable.getMessage();
            }
            throwable.printStackTrace();
        }
        if (mMessage != null) {
            Log.e(TAG, mMessage);
        }
    }
}
