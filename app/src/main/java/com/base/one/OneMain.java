package com.base.one;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextClock;

import com.base.annotation.ContentView;
import com.base.annotation.OnClick;
import com.base.annotation.ViewInject;
import com.base.mvp.R;
import com.base.tools.InjectUtils;

@ContentView(R.layout.activity_one_main)
public class OneMain extends AppCompatActivity {
    @ViewInject(R.id.one_txtclock)
    private TextClock clock;
    private String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtils.inject(this);

        if (clock != null) {
            Log.e(TAG, "onCreate: dad");
            clock.setText("sdassadsas");
        }
    }

    @OnClick(R.id.one_txtclock)
    public void test(View view) {

        Log.e(TAG, " OnClick: ");
    }
}
