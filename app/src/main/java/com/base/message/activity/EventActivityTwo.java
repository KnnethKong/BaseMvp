package com.base.message.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.annotation.Event;
import com.base.message.EventMes;
import com.base.tools.Arith;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * kxf 
 */
public class EventActivityTwo extends AppCompatActivity implements View.OnClickListener {
    private int txt1Id = 0x273749;
    TextView txt1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        txt1 = new TextView(this);
        LinearLayout.LayoutParams txt1Layout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txt1Layout.gravity = LinearLayout.TEXT_ALIGNMENT_GRAVITY;
        txt1.setLayoutParams(txt1Layout);
        txt1.setText("  EventActivityTwo    click    ");
        txt1.setId(txt1Id);
        txt1.setTextSize(20);
        txt1.setOnClickListener(this);
        linearLayout.addView(txt1);
        TextInputLayout passwordLayout = new TextInputLayout(this);
        passwordLayout.setPasswordVisibilityToggleEnabled(true);
        passwordLayout.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        EditText editText = new EditText(this);
        editText.setSingleLine();
        editText.setMaxLines(1);
        editText.setMaxWidth(12);
        editText.setMaxHeight(1);
        editText.setTextSize(16);
        editText.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        passwordLayout.addView(editText);
        linearLayout.addView(passwordLayout);
        setContentView(linearLayout);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case 0x273749:
                EventMes mes = new EventMes();
                mes.setExt("sdrjjjsue938465302389");
                mes.setSize(234);
                Double div = Arith.div(123.273473918, 183);
                Double mul = Arith.mul(1.3849238, 0.048493713);
                Double add = Arith.add(div, mul);
                Double su = Arith.sub(34.749361394, add);
                mes.setMsg(div + "/n mul:" + mul + "/n add:" + add + "/n su:" + su);
                EventBus.getDefault().postSticky(mes);
                Log.e("kxflog", "onClick: " + mes.toString());
                startActivity(new Intent(EventActivityTwo.this, EventActivityOne.class));
                break;
        }

    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void test(EventMes e) {
//        Log.e("kxflog", "test: " + e.toString());
//
//    }

    @Override
    protected void onPause() {
        //  EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
