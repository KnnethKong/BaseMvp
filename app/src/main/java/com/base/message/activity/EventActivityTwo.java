package com.base.message.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.annotation.Event;
import com.base.message.EventMes;
import com.base.tools.Arith;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * ®π
 * kxf 
 */
public class EventActivityTwo extends AppCompatActivity implements View.OnClickListener {
    private int txt1Id = 0x273749;
    TextView txt1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);

        txt1 = new TextView(this);
        LinearLayout.LayoutParams txt1Layout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txt1Layout.gravity = LinearLayout.TEXT_ALIGNMENT_GRAVITY;
        txt1.setLayoutParams(txt1Layout);
        txt1.setText("  EventActivityTwo   click\uF8FF");
        txt1.setId(txt1Id);
        txt1.setTextSize(20);
        txt1.setOnClickListener(this);
        linearLayout.addView(txt1);
        setContentView(linearLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case 0x273749:
                EventMes mes = new EventMes();
                mes.setExt("sdrjjjsue938465302389");
                mes.setSize(234);
                Double div = Arith.div(0.00000000035, 183);
                mes.setDiv(div);
                mes.setMsg("eed73829EWTWWYWUWWJDUWBQUIOW&&%'77836");
                EventBus.getDefault().postSticky(mes);
                Log.e("kxflog", "onClick: " + mes.toString());
                startActivity(new Intent(EventActivityTwo.this, EventActivityOne.class));
                break;
        }

    }

   @Subscribe(threadMode = ThreadMode.MAIN)
    public void test(EventMes e) {
        Log.e("kxflog", "test: " + e.toString());

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
}
