package com.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import com.base.annotation.ContentView;
import com.base.mvp.R;

/**
 * Created by KXF on 2018/5/19.
 */
@ContentView(R.layout.fragment_activity)
public class FragmentActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setActionBar(toolbar);

        //  setSupportActionBar(toolbar);
    }


}
