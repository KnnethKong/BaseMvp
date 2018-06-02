package com.base.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.base.adapter.FabRecyclerAdapter;
import com.base.listener.FabScrollListener;
import com.base.listener.HideScrollListener;
import com.base.mvp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KXF on 2018/5/19.
 */


public class OneFragment extends BaseFragment implements HideScrollListener {
    private RecyclerView recyclerview;
    private ImageButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_one, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerview = (RecyclerView)view.findViewById(R.id.one_recycler);
        fab = (ImageButton)view.findViewById(R.id.one_img);
        recyclerview.addOnScrollListener(new FabScrollListener(this));
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add("Item"+i);
        }
        RecyclerView.Adapter adapter = new FabRecyclerAdapter(list );
        recyclerview.setAdapter(adapter );
    }

    @Override
    public void onHide() {
        // 隐藏动画--属性动画
       // toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(3));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();

        fab.animate().translationY(fab.getHeight()+layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));
    }

    @Override
    public void onShow() {
        // 显示动画--属性动画
       // toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();
        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

    }
}
