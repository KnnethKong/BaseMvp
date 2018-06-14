package com.base.message.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.message.EventMes;
import com.base.mvp.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventActivityOne extends AppCompatActivity implements View.OnClickListener {
    private TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        txt1 = new TextView(this);
//        LinearLayout.LayoutParams txt1Layout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        txt1Layout.gravity = LinearLayout.TEXT_ALIGNMENT_GRAVITY;
//        txt1.setLayoutParams(txt1Layout);
//        txt1.setText("one  ----click   ");
//        txt1.setId((int) 28394);
//        txt1.setOnClickListener(this);
//        txt1.setTextSize(20);
//        linearLayout.addView(txt1);
        setContentView(R.layout.test);
        txt1= (TextView) findViewById(R.id.txt);


    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case 28394:
                break;
        }

    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void show(EventMes mes) {
        Log.e("kxflog", "show: " + mes.toString());
        txt1.setText(mes.toString());
    }

}
