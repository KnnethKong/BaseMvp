package com.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.base.adapter.FabRecyclerAdapter;
import com.base.listener.HideScrollListener;
import com.base.listener.TestLinstener;
import com.base.mvp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KXF on 2018/5/23.
 */

public class TestMain extends AppCompatActivity implements View.OnScrollChangeListener, HideScrollListener {
    private RecyclerView listRecycler;
    private ScrollView scrollView;
    private RelativeLayout relativeTitle;
    private int height = 40;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_scrolling);
        listRecycler = (RecyclerView) findViewById(R.id.test_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listRecycler.setLayoutManager(linearLayoutManager);
        linearLayout = (LinearLayout) findViewById(R.id.test_linear_con);
        scrollView = (ScrollView) findViewById(R.id.test_scroll);
        scrollView.setOnScrollChangeListener(this);
        relativeTitle = (RelativeLayout) findViewById(R.id.test_relative_title);
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 40; i++)
            datas.add("shdgdhshsdudd" + i);
        FabRecyclerAdapter adapter = new FabRecyclerAdapter(datas);
        listRecycler.setAdapter(adapter);
        listRecycler.addOnScrollListener(new TestLinstener(this, linearLayoutManager));
    }

    private int distance = 0;

    @Override
    public void onScrollChange(View view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int sub = scrollY - oldScrollY;
        if (sub > height) {
            distance = sub;
            Log.e("kxflog", " onScrollChange > : " + sub);

        } else if (sub < height) {
            Log.e("kxflog", " onScrollChange < : " + sub);
        }
    }


    @Override
    public void onHide() {
        Log.e("kxflog", " onHide > : ");
        // 隐藏动画--属性动画
        // toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(3));
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeTitle.getLayoutParams();
        relativeTitle.animate().translationY(relativeTitle.getHeight()).setInterpolator(new AccelerateInterpolator(3));
        linearLayout.animate().translationY(0).setInterpolator(new AccelerateInterpolator(3));
    }

    @Override
    public void onShow() {
        Log.e("kxflog", " onShow > : ");
        // 显示动画--属性动画
        // toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
        relativeTitle.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
        linearLayout.animate().translationY(-linearLayout.getHeight()).setInterpolator(new DecelerateInterpolator(3));
    }


    interface showTileFace {

        void showTitle();

        void hideTitle();
    }


}
