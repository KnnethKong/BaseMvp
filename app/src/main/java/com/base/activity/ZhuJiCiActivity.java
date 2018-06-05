package com.base.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.base.db.BaseDaoFactory;
import com.base.db.IBaseDao;
import com.base.db.WalletDao;
import com.base.db.WalletEntity;
import com.base.db.WalletItemEntity;
import com.base.draw.JZCLayout;
import com.base.draw.LineeLayout;
import com.base.keyboard.LoginKeyBoard;
import com.base.mvp.R;
import com.base.tools.ArrayUtils;
import com.base.wallet.WalletCoinDetailEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KXF on 2018/5/24.
 */

public class ZhuJiCiActivity extends AppCompatActivity implements View.OnClickListener, OnLoadMoreListener, OnRefreshListener {

    private LineeLayout topRelative, bottomRelative;

    private String TAG = this.getClass().getName();
    private ArrayList<String> localDatas = new ArrayList<>();
    private List<String> tempList = new ArrayList<>();
    private SwipeToLoadLayout swipeToLoadLayout;
    private IBaseDao<WalletEntity> walletEntityIBaseDao;
    private IBaseDao<WalletItemEntity> walletItemEntityIBaseDao;
    private EditText zjcedit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhujici_layout);
        zjcedit = (EditText) findViewById(R.id.zjc_edit);
        topRelative = (LineeLayout) findViewById(R.id.zjc_top);
        bottomRelative = (LineeLayout) findViewById(R.id.zjc_bottom);
        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipelayout);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        //rrayList<String> datas =
        initData();
        initView(localDatas);
//        final LoginKeyBoard loginKeyBoard = new LoginKeyBoard(this);
//        zjcedit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    loginKeyBoard.attachTo(zjcedit);
//                }
//            }
//        });
//
//        zjcedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (loginKeyBoard.isAttached) loginKeyBoard.showKeyboard();
//                else loginKeyBoard.attachTo(zjcedit);
//            }
//        });

    }

    private ArrayList<String> initData() {

        localDatas.add("layout");
        localDatas.add("list");
        localDatas.add("my");
        localDatas.add("group");
        localDatas.add("help");
        localDatas.add("code");
        localDatas.add("file");
        localDatas.add("center");
        localDatas.add("listener");
        localDatas.add("content");
        localDatas.add("build");
        localDatas.add("edit");
        new Thread(){
            @Override
            public void run() {
                insert();
            }
        }.start();
        return localDatas;
    }


    private void insert() {
        walletEntityIBaseDao = BaseDaoFactory.getInstance().getDataHelper(WalletDao.class, WalletEntity.class);


//        WalletEntity walletEntity = new WalletEntity(System.currentTimeMillis(), "ht", "miyao", "pwd", "pwdnotice", "keystore");
//
//        Long i = walletEntityIBaseDao.insert(walletEntity);
//        Log.e(TAG, "insert: " + i);
        WalletEntity queryEntity = new WalletEntity();
        //queryEntity.setName("ht");
        List<WalletEntity> list1 = walletEntityIBaseDao.query(
                queryEntity
        );
        List<WalletEntity> list2 = walletEntityIBaseDao.queryAll(WalletEntity.class);
        for (int i=0;i<list1.size();i++){
            WalletEntity walletEntity=list1.get(i);
            WalletEntity walletEntity2=list2.get(i);

            Log.e(TAG, "walletEntity: " + walletEntity.toString()+ "  ");
            Log.e(TAG, "walletEntity2: " + walletEntity2.toString()+ "  ");

        }


       // Log.e(TAG, "list: " + list1.size() + "  ");
//        Log.e(TAG, "list2  : " + list2.size() + "  ");

//        WalletEntity updateEntity = new WalletEntity();
//        updateEntity.setName("ht1");
//        int count = walletEntityIBaseDao.update(updateEntity, queryEntity);
//        Log.e(TAG, "update: " + count);
//
//        List<WalletEntity> list = walletEntityIBaseDao.query(updateEntity);
//        Log.e(TAG, "list : " + list.size());


    }

    private void initView(ArrayList<String> datas) {
        for (int i = 0; i < datas.size(); i++) {
            TextView textView = new TextView(this);
            String data = datas.get(i);
            if (tempList.size() > 0) {
                if (tempList.size() >= i) {
                    String temp = tempList.get(i);
                    if (temp != null && temp.equals(data)) {
                        textView.setBackgroundColor(getColor(R.color.qian_lan));
                        textView.setTextColor(Color.WHITE);
                    }
                }
            }
            textView.setText(datas.get(i));
            textView.setPadding(10, 8, 10, 8);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(16);
            textView.setOnClickListener(this);
            textView.setId(100 + i);
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(marginLayoutParams);
            marginLayoutParams.rightMargin = 20;
            marginLayoutParams.leftMargin = 20;
            marginLayoutParams.topMargin = 20;
            marginLayoutParams.bottomMargin = 20;
            bottomRelative.addView(textView);
        }


    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        if (view.getId() >= 100 && view.getId() < 114) {
            TextView textView = (TextView) view;
            String str = textView.getText().toString();
            if (!tempList.contains(str)) {
                ArrayList<String> datas = randomDatas();
                removeAll();
                initView(datas);
                tempList.add(str);
                addView(str);
            }
        }
    }

    private void removeAll() {
        int childCount = bottomRelative.getChildCount();
        bottomRelative.removeAllViewsInLayout();
        int twocount = bottomRelative.getChildCount();
        bottomRelative.removeAllViews();
        int three = bottomRelative.getChildCount();
    }


    private void addView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(10, 10, 10, 10);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(getColor(R.color.white));
        textView.setTextSize(16);
        textView.setOnClickListener(this);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(marginLayoutParams);
        topRelative.addView(textView);
    }

    private ArrayList<String> randomDatas() {
        return localDatas = ArrayUtils.randomList(localDatas);
    }

    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setLoadingMore(false);
            }
        }, 600);
    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 600);
    }
}
