package com.my.baselibrary.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

import java.util.List;
import java.util.Objects;

public abstract class BaseSmartRefreshFragment<T> extends BaseFragment {
    protected SmartRefreshLayout mSmartRefresh;
    protected View no_data_iv;
    protected TextView total_view;
    private BaseQuickAdapter adapter;

    protected void setBaseAdapter(BaseQuickAdapter adapter) {
        this.adapter = adapter;
    }

    protected void setNoDataIv(View no_data_iv) {
        this.no_data_iv = no_data_iv;
    }

    protected void setTotalView(TextView total_view) {
        this.total_view = total_view;
    }


    /**
     * 如果你使用了 setBaseAdapter
     * 直接调用此方法即可
     ************************************************************************************************/
    protected void setViewListData(List<T> data) {
        if (this.adapter == null) {
            throw new RuntimeException("adapter 为空！");
        }
        setViewListData(data, adapter);
    }

    /**
     * 如果需要设置多adapter数据 使用此方法
     ************************************************************************************************/
    protected void setViewListData(List<T> data, BaseQuickAdapter adapter) {
        if (mSmartRefresh == null) {
            if (data.size() > 0) {
                if (no_data_iv != null) {
                    no_data_iv.setVisibility(View.GONE);
                }
            } else {
                if (no_data_iv != null) {
                    no_data_iv.setVisibility(View.VISIBLE);
                }
            }
            adapter.setNewData(data);
            return;
        }

        mSmartRefresh.finishLoadMore();
        mSmartRefresh.finishRefresh();
        //判断是刷新完成还是加载下一页
        if (mSmartRefresh.getState() == RefreshState.Refreshing
                || mSmartRefresh.getState() == RefreshState.RefreshFinish) {
            if (data.size() > 0) {
                mSmartRefresh.setNoMoreData(false);
                if (no_data_iv != null) {
                    no_data_iv.setVisibility(View.GONE);
                }
            } else {
                if (no_data_iv != null) {
                    no_data_iv.setVisibility(View.VISIBLE);
                }
                mSmartRefresh.setNoMoreData(true);
            }

            adapter.setNewData(data);
        } else if (mSmartRefresh.getState() == RefreshState.Loading || mSmartRefresh.getState() == RefreshState.LoadFinish) {
            if (data == null || data.size() == 0) {
                mSmartRefresh.finishLoadMoreWithNoMoreData();
            } else {
                adapter.addData(data);
            }
        } else {
            if (data.size() > 0) {
                mSmartRefresh.setNoMoreData(false);
                if (no_data_iv != null) {
                    no_data_iv.setVisibility(View.GONE);
                }
            } else {
                mSmartRefresh.setNoMoreData(true);
                if (no_data_iv != null) {
                    no_data_iv.setVisibility(View.VISIBLE);
                }
            }
            adapter.setNewData(data);
        }
    }


    protected void onRefreshOrLoadMoreError() {
        if (mSmartRefresh != null) {
            mSmartRefresh.finishLoadMore();
            mSmartRefresh.finishRefresh();
        }
    }

    protected void onRefreshOrLoadMoreError(Throwable throwable) {
        if (mSmartRefresh != null) {
            mSmartRefresh.finishLoadMore();
            mSmartRefresh.finishRefresh();
        }
        Log.e(TAG, Objects.requireNonNull(throwable.getMessage()));
    }
}
