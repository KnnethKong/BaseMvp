package com.base.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by KXF on 2018/5/23.
 */

public class TestLinstener extends RecyclerView.OnScrollListener {
    private static final int THRESHOLD = 20;
    private int distance = 0;
    private HideScrollListener hideListener;
    private boolean visible = true;//是否可见
    private LinearLayoutManager linearLayoutManager;

    public TestLinstener(HideScrollListener hideScrollListener, LinearLayoutManager linearLayoutManager) {
        this.hideListener = hideScrollListener;
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        /**
         * dy:Y轴方向的增量
         * 有正和负
         * 当正在执行动画的时候，就不要再执行了
         */
        int position = linearLayoutManager.findFirstVisibleItemPosition();

        if (distance > THRESHOLD && visible) {
            //隐藏动画
            visible = false;
            hideListener.onHide();
            distance = 0;
        } else if (distance < -20 && !visible && position == 0) {
            //显示动画
            visible = true;
            hideListener.onShow();
            distance = 0;
        }
        if (visible && dy > 0 || (!visible && dy < 0)) {
            distance += dy;
        }
       // Log.e("kxflog", "onScrolled  position : " + position);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
