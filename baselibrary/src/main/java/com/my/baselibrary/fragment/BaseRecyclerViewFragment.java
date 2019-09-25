package com.my.baselibrary.fragment;

import android.widget.LinearLayout;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.my.baselibrary.widget.LinearItemDecoration;

/**
 * @author: xk
 * @data: 2019/8/12
 * @description:
 */
public abstract class BaseRecyclerViewFragment extends BaseFragment {
    protected RecyclerView baseRecyclerView;

    protected RecyclerView initRecyclerView(@IdRes int recyclerView){
        return initRecyclerView(findViewById(recyclerView));
    }

    protected RecyclerView initRecyclerView(RecyclerView recyclerView){
        recyclerView.addItemDecoration(new LinearItemDecoration(requireContext(), LinearLayout.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return recyclerView;
    }
}
