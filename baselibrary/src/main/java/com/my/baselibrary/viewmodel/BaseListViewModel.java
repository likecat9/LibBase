package com.my.baselibrary.viewmodel;


import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;

/**
 * @author 徐康
 * 2019/4/25
 * 描述：
 */
public abstract class BaseListViewModel<T> extends ViewModel {
    protected int currentPage = 1;
    protected Map<String, Object> filterParam = new HashMap<>();
    protected int pageSize = 10;
    protected String TAG = this.getClass().getSimpleName();


    /**
     * 参数相关
     ************************************************************************************************/
    public Map<String, Object> getFilterParam() {
        return filterParam;
    }

    public void setFilterParam(Map<String, Object> filterParam) {
        this.filterParam = filterParam;
    }

    public void addFilterParam(String filterName, Object value) {
        this.filterParam.put(filterName, value);
    }

    public void addFilterParam(Map<String, Object> param) {
        this.filterParam.putAll(param);
    }

    public void setQueryName(String value) {
        this.filterParam.put("queryName", value);
    }

    public void resetFilterParam() {
        this.filterParam = new HashMap<>();
    }

    /**
     * 分页相关
     ************************************************************************************************/

    public Flowable<T> requestCurrentPage() {
        return null;
    }

    public Flowable<T> requestNextPage() {
        return null;
    }

    public Flowable<T> requestPreviousPage() {
        return null;
    }

    public void resetPage() {
        currentPage = 1;
    }

    public void addPage() {
        currentPage++;
    }
}
