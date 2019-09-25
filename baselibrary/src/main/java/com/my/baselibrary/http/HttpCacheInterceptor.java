package com.my.baselibrary.http;

import androidx.annotation.StringDef;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpCacheInterceptor implements Interceptor {
    private int time;
    private @TimeType
    String type;

    public static final String HOURS = "HOURS";
    public static final String MINUTES = "MINUTES";
    public static final String SECONDS = "SECONDS";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({HOURS, MINUTES, SECONDS})
    public @interface TimeType {
    }

    /**
     * 设置缓存时间
     *
     * @param time 小时
     */
    public void setTime(int time) {
        this.time = time;
    }


    public HttpCacheInterceptor() {
    }

    /**
     * @param time 时间
     * @param type {@link TimeType}
     */
    public HttpCacheInterceptor(int time, @TimeType String type) {
        this.time = time;
        this.type = type;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        CacheControl cacheControl = null;
        Request request = chain.request();

        switch (type) {
            case HOURS:
                cacheControl = new CacheControl.Builder()
                        .maxAge(time, TimeUnit.HOURS)
                        .maxStale(time, TimeUnit.HOURS)
                        .build();
                request = request.newBuilder().cacheControl(cacheControl).build();

                break;
            case MINUTES:
                cacheControl = new CacheControl.Builder()
                        .maxAge(time, TimeUnit.MINUTES)
                        .maxStale(time, TimeUnit.MINUTES)
                        .build();
                request = request.newBuilder().cacheControl(cacheControl).build();

                break;
            case SECONDS:
                cacheControl = new CacheControl.Builder()
                        .maxAge(time, TimeUnit.SECONDS)
                        .maxStale(time, TimeUnit.SECONDS)
                        .build();
                request = request.newBuilder().cacheControl(cacheControl).build();

                break;
            default:
        }

        return chain.proceed(request);
    }
}
