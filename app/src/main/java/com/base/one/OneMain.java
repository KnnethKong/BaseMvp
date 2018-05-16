package com.base.one;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.Toast;

import com.base.annotation.ContentView;
import com.base.annotation.Event;

import com.base.annotation.ViewInject;
import com.base.mvp.R;
import com.base.tools.InjectUtils;

@ContentView(R.layout.activity_one_main)
public class OneMain extends BaseActivity<LoginView, Presenter> implements LoginView {
    @ViewInject(R.id.one_txtclock)
    private TextClock clock;
    @ViewInject(R.id.one_btn)
    private Button btn;


    private String TAG = this.getClass().getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "OneMain onCreate: ");
        InjectUtils.inject(this);
    }

    @Event(
            value = {R.id.one_btn},
            type = View.OnClickListener.class,
            setter = "setOnClickListener",
            method = "onClick")
    public void test(View view) {
        Log.e(TAG, " OnClick: ");
        getPresenter().login("http://app.landingbook.net/index.php/api/user/login", "ubanwe", "dadassa");
    }


    @Override
    public LoginView createView() {
        return this;
    }

    @Override
    public Presenter createPresenter() {
        return new Presenter();
    }

    @Override
    public void onLogin(String result) {
        Toast.makeText(this, "result :  " + result, Toast.LENGTH_SHORT).show();
    }
}
